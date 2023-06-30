package integracion.producto;

import integracion.transacciones.TransactionManager;
import negocio.producto.TBatido;
import negocio.producto.THelado;
import negocio.producto.TProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOProductoImp implements DAOProducto {
	
	public Integer create(TProducto tProducto) {
		int id = -1;

		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
		
			String s ="INSERT INTO producto (id,nombre,sabor,tipo, activo,precio,stock) VALUES (null,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(s,java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tProducto.getNombre());
			ps.setString(2, tProducto.getSabor());
			ps.setString(3, tProducto.getTipo());
			ps.setBoolean(4, tProducto.getActivo());
			ps.setFloat(5, tProducto.getPrecio());
			ps.setInt(6, tProducto.getStock());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while(rs.next()) id = rs.getInt(1);
			
			if (tProducto.getTipo().equalsIgnoreCase("helado")) {
				ps.close();
				ps = con.prepareStatement("INSERT INTO helado (id, envase, activo) VALUES (?,?,?)",java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, id);
				ps.setString(2, ((THelado) tProducto).getEnvase());
				ps.setInt(3, 1);
				
				ps.executeUpdate();
				ps.close();
			} 
			else if (tProducto.getTipo().equalsIgnoreCase("batido")) {
				ps.close();
				ps = con.prepareStatement("INSERT INTO batido (id, tamanio, activo) VALUES (?,?,?)");
				ps.setInt(1, id);
				ps.setString(2, ((TBatido) tProducto).getTamanio());
				ps.setInt(3, 1);
				
				ps.executeUpdate();
				ps.close();
			}
			
			ps = con.prepareStatement("INSERT INTO producto_proveedor (producto_id, proveedor_id, activo_r) VALUES (?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2,tProducto.getIdProveedor());
			ps.setInt(3, 1);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			id = -1;
		}
		return id;
	}
	
	public Integer delete(Integer Id) {
		int del = -1;
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			
			String tipo = readById(Id).getTipo();

			PreparedStatement ps = con.prepareStatement("UPDATE producto SET activo = 0 WHERE id = ?");
			ps.setInt(1, Id);
			ps.executeUpdate();

			ps = con.prepareStatement("UPDATE " + tipo + " SET activo = 0 WHERE id = ?");
			ps.setInt(1, Id);
			ps.executeUpdate();

			
			ps = con.prepareStatement("UPDATE producto_proveedor SET activo_r = 0 WHERE producto_id = ?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			ps.close();
			del = 1;

		} catch (Exception e) {
			System.out.println(e); 
			del = -1;
		}

		return del;
	}
	
	public Integer update(TProducto tProducto) {
		int upd = -1;
		int Id = tProducto.getId();
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			
			String tipo = tProducto.getTipo();

			PreparedStatement ps = con.prepareStatement("UPDATE producto SET nombre = ?, sabor = ?, activo = ?, precio = ?, stock = ? WHERE id = ?");
			ps.setString(1, tProducto.getNombre());
			ps.setString(2, tProducto.getSabor());
			ps.setInt(3, tProducto.getActivo()? 1: 0);
			ps.setFloat(4, tProducto.getPrecio());
			ps.setInt(5, tProducto.getStock());
			ps.setInt(6, Id);
			ps.executeUpdate();
			ps = con.prepareStatement("UPDATE " + tipo + " SET " + (tipo == "helado"? "envase" : "tamanio") + " = ?, activo = ? WHERE id = ?");
			ps.setString(1, tipo == "helado"? ((THelado) tProducto).getEnvase() : ((TBatido) tProducto).getTamanio());
			ps.setInt(2, tProducto.getActivo()? 1:0);
			ps.setInt(3, Id);
			
			ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto_proveedor SET activo_r = ?, proveedor_id = ? WHERE producto_id = ?");
			ps.setInt(1, tProducto.getActivo()? 1:0);
			ps.setInt(2, tProducto.getIdProveedor());
			ps.setInt(3, Id);
			ps.executeUpdate();
			ps.close();
			upd = 1;

		} catch (Exception e) {
			System.out.println(e); 
			upd = -1;
		}

		return upd;
	}
	
	public List<TProducto> readAll() {
		List<TProducto> lista = new ArrayList<TProducto>();
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		try {

			String sentencia = "SELECT id FROM producto FOR UPDATE";

			PreparedStatement ps = con.prepareStatement(sentencia, java.sql.Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				TProducto tProducto = readById(id);
				lista.add(tProducto);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return lista;
	}
	
	public TProducto readById(Integer Id) {

		TProducto tproducto = null;

		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
		
            String s = "SELECT * FROM producto JOIN producto_proveedor ON id = producto_id WHERE id = ? FOR UPDATE";
			PreparedStatement ps = con.prepareStatement(s,Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString("tipo").equalsIgnoreCase("helado")) {
					ps = con.prepareStatement("SELECT * FROM helado WHERE id = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, Id);

					ResultSet rsr = ps.executeQuery();

					if (rsr.next()) {
						tproducto = new THelado(rsr.getInt("id"),rsr.getString("envase"),rs.getString("nombre"), rs.getString("sabor"), rs.getInt("activo") == 1? true : false,rs.getInt("proveedor_id"), rs.getFloat("precio"), rs.getInt("stock")) ;
					}

				}

				else if (rs.getString("tipo").equalsIgnoreCase("batido")) {
					ps = con.prepareStatement("SELECT * FROM batido WHERE id = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, Id);

					ResultSet rsc = ps.executeQuery();

					if (rsc.next()) {
						tproducto = new TBatido(rsc.getInt("id"),rsc.getString("tamanio"),rs.getString("nombre"), rs.getString("sabor"),rs.getInt("activo") == 1? true : false,rs.getInt("proveedor_id"), rs.getFloat("precio"), rs.getInt("stock"));
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			tproducto = null;
		}

		return tproducto;
	}
	
	public List<TProducto> readByProveedor(Integer IdProveedor) {
		List<TProducto> lista = new ArrayList<TProducto>();
		TProducto tProducto;
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			String sentencia = "SELECT producto_id  FROM producto_proveedor WHERE proveedor_id = ? FOR UPDATE";
			PreparedStatement ps = con.prepareStatement(sentencia);
			ps.setInt(1, IdProveedor);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tProducto = this.readById(rs.getInt("producto_id"));
				lista.add(tProducto);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return lista;
	
	}
	
	public TProducto readByNombre(String Nombre) {
		TProducto tProducto = null;
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
	
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM producto WHERE nombre = ? FOR UPDATE",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, Nombre);
			ResultSet rs = ps.executeQuery();
			
			int id=-1;
			while (rs.next()) id = rs.getInt("id");
			tProducto = readById(id);
		} catch (Exception e) {
			 System.out.println(e);
		}
	
		return tProducto;
	}
	@Override
	public int restarStock(int id, int cantidad) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String insert = "UPDATE producto SET stock=stock - ? WHERE id=?";
		int result = -1;
		
		try {
			PreparedStatement statement = con.prepareStatement(insert);
			statement.setInt(1, cantidad);
			statement.setInt(2, id);
			result = statement.executeUpdate();
			
			statement.close();
			//conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int sumarStock(int id, int cantidad) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String insert = "UPDATE producto SET stock=stock + ? WHERE id=?";
		int result = -1;
		
		try {
			PreparedStatement statement = con.prepareStatement(insert);
			statement.setInt(1, cantidad);
			statement.setInt(2, id);
			result = statement.executeUpdate();
			
			statement.close();
			//conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
}