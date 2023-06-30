package integracion.seccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import integracion.transacciones.TransactionManager;
import negocio.seccion.TSeccion;
public class DAOSeccionImp implements DAOSeccion {
	public Integer create(TSeccion Seccion) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		Integer id = -1;
		String s1 = new String("INSERT INTO seccion (id, nombre, sueldo, activo) VALUES (null, ?, ?, ?)");
		
		try {  
			PreparedStatement ps = con.prepareStatement(s1, java.sql.Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, Seccion.getNombre());
			ps.setDouble(2, Seccion.getSueldo());
			ps.setInt(3, Seccion.getActivo() ? 1 : 0);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()) id = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			id = -1;
		}		
		
		return id;	
	}
	
	public TSeccion readById(Integer Id) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();	
		TSeccion seccion = null;

		String s = new String("SELECT * FROM seccion AS c WHERE c.id = ? FOR UPDATE");
		
		try{
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setInt(1, Id);
 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) seccion = new TSeccion( rs.getInt("id"),rs.getString("nombre"), rs.getDouble("sueldo"), rs.getBoolean("activo"));;

		} catch(Exception e) {
			System.out.println(e);
		} 
		return seccion;
	}
	
	public Set<TSeccion> readAll() {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		String s = new String("SELECT * FROM seccion FOR UPDATE");
        
		Set<TSeccion> lista = new HashSet<TSeccion>();

        try{
            PreparedStatement ps = con.prepareStatement(s);
 
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
            	TSeccion res = new TSeccion(rs.getInt("id"),rs.getString("nombre"), rs.getDouble("sueldo"), rs.getInt("activo") == 1? true : false);
                lista.add(res);
            }
        } catch(Exception e) {
        	System.out.println(e);
        }
        return lista;
	}
	
	public Integer update(TSeccion Seccion) {
		int upd=-1;
		
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String s = new String("UPDATE seccion SET nombre = ?, sueldo = ?, activo = ? WHERE id = ?"); 
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setString(1, Seccion.getNombre());
			ps.setDouble(2, Seccion.getSueldo());
			ps.setInt(3, Seccion.getActivo() ? 1 : 0);
			ps.setInt(4, Seccion.getId());
			ps.executeUpdate();
			upd=1;

		} catch(Exception e) {
			System.out.println(e);
		}  
		return upd;
	}
	
	public Integer delete(Integer Id) {
		int del = -1;
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		String s = new String("UPDATE seccion SET activo = 0 WHERE id = ?");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setInt(1, Id);
			ps.executeUpdate();
			del = 1;		
		} catch(Exception e) {
			System.out.println(e);
		}
		return del;
	}
	
	public TSeccion readByNombre(String Nombre) {
		TSeccion seccion = null;
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		String s = new String("SELECT * FROM seccion WHERE nombre = ? FOR UPDATE");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setString(1, Nombre);
 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) seccion = new TSeccion(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("sueldo"), rs.getInt("activo") == 1? true : false);

		} catch(Exception e) {
			System.out.println(e);
		}  
		return seccion;
	}
}