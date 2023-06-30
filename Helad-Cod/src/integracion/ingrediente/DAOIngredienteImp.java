package integracion.ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.ingrediente.TIngrediente;
import negocio.ingrediente.TLiquido;
import negocio.ingrediente.TSolido;

public class DAOIngredienteImp implements DAOIngrediente {

	public int create(TIngrediente tIngrediente) {
		
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		int id = -1;

		try {
			String s = "INSERT INTO ingrediente(id,nombre, cantidad, codigo, tipo, activo) VALUES (null,?, ?, ?,?, ?)";
			PreparedStatement ps = con.prepareStatement(s, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tIngrediente.getNombre());
			ps.setInt(2, tIngrediente.getCantidad());
			ps.setString(3, tIngrediente.getCodigo());
			ps.setString(4, tIngrediente.getTipo());
			ps.setBoolean(5, tIngrediente.getActivo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while(rs.next()) id = rs.getInt(1);	

			if (tIngrediente.getTipo().equalsIgnoreCase("liquido")) {
				ps.close();
				ps = con.prepareStatement("INSERT INTO LIQUIDO (id, espesor, activo) VALUES (?,?,?)");
				TLiquido tLiq = (TLiquido) tIngrediente;
				ps.setInt(1, id);
				ps.setInt(2, tLiq.getEspesor());
				ps.setBoolean(3, true);
				ps.executeUpdate();
				ps.close();
			}
			else if (tIngrediente.getTipo().equalsIgnoreCase("solido")) {
				ps.close();
				ps = con.prepareStatement("INSERT INTO SOLIDO (id, tamanio, activo) VALUES (?,?,?)");
				TSolido tSol = (TSolido) tIngrediente;
				ps.setInt(1, id);
				ps.setString(2, tSol.getTamanio());
				ps.setBoolean(3, true);
				ps.executeUpdate();
				ps.close();
			}
			
			ps = con.prepareStatement("INSERT INTO producto_ingrediente (producto_id, ingrediente_id, activo_r) VALUES (?,?,?)");
			ps.setInt(1, tIngrediente.getIdProducto());
			ps.setInt(2,id);
			ps.setInt(3, 1);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			id = -1;
		}

		return id;
	}

	public int delete(int id) {
		int del = -1;
		Transaction t = TransactionManager.getInstance().getTransaccion();
		Connection con = (Connection) t.getResource();
		try {

			String tipo = readById(id).getTipo();

			PreparedStatement ps = con.prepareStatement("UPDATE ingrediente SET activo = false WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps = con.prepareStatement("UPDATE " + tipo + " SET activo = false WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto_ingrediente SET activo_r = 0 WHERE ingrediente_id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			del = 1;

		} catch (Exception e) {
			del = -1;
			System.out.println(e);
		}

		return del;
	}

	public TIngrediente readById(int id) {
		TIngrediente Tingrediente = null;
		Transaction t = TransactionManager.getInstance().getTransaccion();
		Connection con = (Connection) t.getResource();

		try {

			String s = "SELECT * FROM ingrediente JOIN producto_ingrediente ON id = ingrediente_id WHERE id = ? FOR UPDATE";
			PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString("tipo").equalsIgnoreCase("liquido")) {
					ps = con.prepareStatement("SELECT * FROM liquido WHERE id = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, id);

					ResultSet rsr = ps.executeQuery();

					if (rsr.next()) {
						Tingrediente = new TLiquido(rsr.getInt("id"), rsr.getInt("espesor"), rs.getString("nombre"),
								rs.getInt("cantidad"), rs.getString("codigo"), rs.getBoolean("activo"), rs.getInt("producto_id"));
					}

				}

				else  {
					ps = con.prepareStatement("SELECT * FROM solido WHERE id = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, id);

					ResultSet rsc = ps.executeQuery();

					if (rsc.next()) {
						Tingrediente = new TSolido(rsc.getInt("id"), rsc.getString("tamanio"), rs.getString("nombre"),
								rs.getInt("cantidad"), rs.getString("codigo"), rs.getBoolean("activo"), rs.getInt("producto_id"));
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return Tingrediente;
	}

	public List<TIngrediente> readAll() {
		Transaction t = TransactionManager.getInstance().getTransaccion();
		Connection conn = (Connection) t.getResource();
		List<TIngrediente> lista = new ArrayList<>();

		try {

			String sentencia = "SELECT id FROM ingrediente FOR UPDATE";

			PreparedStatement ps = conn.prepareStatement(sentencia, java.sql.Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				TIngrediente tingrediente = readById(id);
				lista.add(tingrediente);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return lista;

	}

	public int update(TIngrediente tingrediente) {
		Transaction t = TransactionManager.getInstance().getTransaccion();
		Connection con = (Connection) t.getResource();
		int upd = -1;
		int Id = tingrediente.getId();
		try {

			String tipo = tingrediente.getTipo();

			PreparedStatement ps = con.prepareStatement(
					"UPDATE ingrediente SET nombre = ?, cantidad = ?, codigo = ?, tipo = ?, activo = ? WHERE id = ?");
			ps.setString(1, tingrediente.getNombre());
			ps.setInt(2, tingrediente.getCantidad());
			ps.setString(3, tingrediente.getCodigo());
			ps.setString(4, tingrediente.getTipo());
			ps.setBoolean(5, tingrediente.getActivo());
			ps.setInt(6, Id);
			ps.executeUpdate();
			ps = con.prepareStatement("UPDATE " + tipo + " SET " + (tipo == "liquido" ? "espesor" : "tamanio")
					+ " = ?, activo = ? WHERE id = ?");
			
			String aux = "";
			if(tipo.equals("liquido")) aux = String.valueOf( ((TLiquido) tingrediente).getEspesor() );
			else aux = ((TSolido) tingrediente).getTamanio();
			ps.setString(1, aux);
			ps.setBoolean(2, tingrediente.getActivo());
			ps.setInt(3, Id);

			ps.executeUpdate();

			ps = con.prepareStatement(
					"UPDATE producto_ingrediente SET activo_r = ?,producto_id = ? WHERE ingrediente_id = ?");
			ps.setBoolean(1, tingrediente.getActivo());
			ps.setInt(2, tingrediente.getIdProducto());
			ps.setInt(3, Id);
			ps.executeUpdate();
			ps.close();
			upd = 1;

		} catch (Exception e) {
			upd = -1;
			System.out.println(e);
		}
		return upd;
	}

	public TIngrediente readByCodigo(String Codigo) {
		TIngrediente tIngrediente = null;
		Transaction t = TransactionManager.getInstance().getTransaccion();
		Connection con = (Connection) t.getResource();
		try {

			PreparedStatement ps = con.prepareStatement("SELECT * FROM ingrediente WHERE codigo = ? FOR UPDATE",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, Codigo);
			ResultSet rs = ps.executeQuery();

			int id = -1;
			while (rs.next()) id = rs.getInt("id");

				//tIngrediente=new TIngrediente(rs.getString("nombre"), rs.getInt("cantidad"),rs.getString("codigo"), rs.getString("tipo"),rs.getBoolean("activo"),rs.getInt("id"),rs.getInt("cantidad"));
			tIngrediente = readById(id);
		} catch (Exception e) {
			System.out.println(e);
		}

		return tIngrediente;
	}
}