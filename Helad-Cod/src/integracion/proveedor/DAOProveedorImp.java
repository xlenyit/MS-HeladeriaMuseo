package integracion.proveedor;

import integracion.transacciones.TransactionManager;
import negocio.proveedor.TProveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class DAOProveedorImp implements DAOProveedor {
	public Integer create(TProveedor tProveedor) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		Integer id = -1;
		String s1 = new String("INSERT INTO proveedor (ID, NIF, activo, nombre, numerocuenta) VALUES (NULL, ?, ?, ?, ?)");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s1, java.sql.Statement.RETURN_GENERATED_KEYS);
			
			ps.setString( 1, tProveedor.getNIF());
			ps.setInt(2, tProveedor.getActivo()? 1 : 0);
			ps.setString( 3, tProveedor.getNombre());
			ps.setString( 4, tProveedor.getNumCuenta());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()) id = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			id = -1;
		}		
		return id;	
	}
		
	public Integer delete(Integer id) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		int del = -1;
		
		String s = new String("UPDATE proveedor SET activo = 0 WHERE id = ?");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
						
			ps.setInt(1, id);
			ps.executeUpdate();
			del = 1;		
		} catch(Exception e) {
			System.out.println(e);
		} 
		return del;
	}
	
	public Integer update(TProveedor tProveedor) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		int upd=-1;
		String s = new String("UPDATE proveedor SET NIF = ?, activo = ?, nombre = ?, numerocuenta = ? WHERE ID = ?");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setString( 1, tProveedor.getNIF());
			ps.setInt(2, tProveedor.getActivo()? 1 : 0);
			ps.setString( 3, tProveedor.getNombre());
			ps.setString( 4, tProveedor.getNumCuenta());
			ps.setInt( 5, tProveedor.getId());
			ps.executeUpdate();
			upd=1;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		return upd;
	}
	
	public TProveedor readById(Integer id) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		TProveedor proveedor = null;

		String s = new String("SELECT * FROM proveedor WHERE id = ? FOR UPDATE");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setInt( 1, id);
 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) proveedor= new TProveedor(rs.getInt("id"), rs.getString("nif"), rs.getString("nombre"), rs.getString("numerocuenta"), (rs.getInt("activo") == 1) ? true: false);
		} catch(Exception e) { 
			System.out.println(e);
		} 
		
		return proveedor;
	}
	
	public Set<TProveedor> readAll() {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String s = new String("SELECT * FROM proveedor FOR UPDATE");
        
		Set<TProveedor> lista = new HashSet<TProveedor>();

        try{
            PreparedStatement ps = con.prepareStatement(s);
 
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                TProveedor res = new TProveedor(rs.getInt("id"), rs.getString("nif"), rs.getString("nombre"), rs.getString("numerocuenta"),rs.getBoolean("activo"));
                lista.add(res);
            }

        } catch(Exception e) { 
        	System.out.println(e);
        }
        return lista;
	}
	
	public TProveedor readByNIF(String nif) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		TProveedor proveedor = null;
		String s = new String("SELECT * FROM proveedor WHERE NIF = ? FOR UPDATE");
		
		try{  	
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setString( 1, nif);
 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) proveedor = new TProveedor(rs.getInt("id"), rs.getString("nif"), rs.getString("nombre"), rs.getString("numerocuenta"),rs.getInt("activo")== 1? true:false);

		} catch(Exception e) {
			System.out.println(e);
		}  
		
		return proveedor;
	}
	
}