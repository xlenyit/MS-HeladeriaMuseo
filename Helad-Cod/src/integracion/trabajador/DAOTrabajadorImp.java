package integracion.trabajador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import integracion.transacciones.TransactionManager;
import negocio.trabajador.TTrabajador;

public class DAOTrabajadorImp implements DAOTrabajador{
	
	public Integer create(TTrabajador tTrabajador) {
		
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		Integer id = -1;
		String s = new String("INSERT INTO trabajador (ID, DNI, activo, nombre, telefono) VALUES (NULL, ?, ?, ?, ?)");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s, java.sql.Statement.RETURN_GENERATED_KEYS);

			ps.setString( 1, tTrabajador.getDNI());
			ps.setInt( 2, 1);
			ps.setString( 3, tTrabajador.getNombre());
			ps.setInt( 4, tTrabajador.getTelefono());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()) id = rs.getInt(1);
			
			ps = con.prepareStatement("INSERT INTO trabajador_seccion (Trabajador_ID, Seccion_ID, activo_r) VALUES (?, ?, ?)",java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.setInt(2, tTrabajador.getIdSeccion());
			ps.setInt(3, 1);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			id = -1;
		}		
		
		return id;	
	}
	
	public TTrabajador readByName(String name) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		TTrabajador trabajador = null;
		String s = new String("SELECT * FROM trabajador JOIN trabajador_seccion ON id = trabajador_id WHERE nombre = ? FOR UPDATE");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setString( 1, name);
 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) trabajador = new TTrabajador(rs.getInt("id"),rs.getString("DNI"),rs.getInt("telefono"), rs.getString("nombre"), rs.getBoolean("activo"), rs.getInt("seccion_id"));
		} catch(Exception e) {
			System.out.println(e);
		}  
		return trabajador;
	}

	public TTrabajador readById(Integer id) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		TTrabajador trabajador = null;
		String s = new String("SELECT * FROM trabajador JOIN trabajador_seccion ON id = trabajador_id WHERE id = ? FOR UPDATE");
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setInt( 1, id);
			ResultSet rs = ps.executeQuery();				
			while(rs.next()) trabajador= new TTrabajador(rs.getInt("id"),rs.getString("DNI"),rs.getInt("telefono"), rs.getString("nombre"), rs.getBoolean("activo"), rs.getInt("seccion_id"));

		} catch(Exception e) {
			System.out.println(e);
		}
		return trabajador;
	}
	
	public Set<TTrabajador> readAll() {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String s = new String("SELECT * FROM trabajador JOIN trabajador_seccion ON id = trabajador_id FOR UPDATE");//WHERE Activo = 1
		Set<TTrabajador> lista = new HashSet<TTrabajador>();

        try{
            PreparedStatement ps = con.prepareStatement(s);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                TTrabajador res = new TTrabajador(rs.getInt("id"),rs.getString("DNI"),rs.getInt("telefono"), rs.getString("nombre"), rs.getBoolean("activo"), rs.getInt("seccion_id"));
                lista.add(res);
            }
        } catch(Exception e) {
        	System.out.println(e);
        }
        return lista;
	}
	
	public Integer update(TTrabajador tTrabajador) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		int upd=-1;
		String s = new String("UPDATE trabajador SET DNI = ?, activo = ?, nombre = ?, telefono = ? WHERE ID = ?");
		try{  
			PreparedStatement ps = con.prepareStatement(s, java.sql.Statement.RETURN_GENERATED_KEYS);
			
			ps.setString( 1, tTrabajador.getDNI());
			ps.setInt( 2, tTrabajador.getActive()? 1: 0);
			ps.setString( 3, tTrabajador.getNombre());
			ps.setInt( 4, tTrabajador.getTelefono());
			ps.setInt( 5, tTrabajador.getId());
		
			ps.executeUpdate();
			
			ps = con.prepareStatement("UPDATE trabajador_seccion SET seccion_id = ?, activo_r = ? WHERE trabajador_id = ?");
			ps.setInt( 1, tTrabajador.getIdSeccion());
			ps.setInt( 2, tTrabajador.getActive()? 1: 0);
			ps.setInt( 3, tTrabajador.getId());
			ps.executeUpdate();
			upd = 1;
		} catch(Exception e) {
			System.out.println(e);
		}  
		return upd;
	}
	
	public Integer delete(Integer id) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		int del = -1;
		String s = new String("UPDATE trabajador SET activo = 0 WHERE id = ?");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			ps = con.prepareStatement("UPDATE trabajador_seccion SET ACTIVO_R = 0 WHERE trabajador_id = ?",java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			del = 1;		
		} catch(Exception e) {
			System.out.println(e); 
		} 
		return del;
	}
	
	public TTrabajador readByDNI(String DNI) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		TTrabajador trabajador = null;
		String s = new String("SELECT * FROM trabajador JOIN trabajador_seccion ON id = trabajador_id WHERE DNI = ? FOR UPDATE");
		
		try{  
			PreparedStatement ps = con.prepareStatement(s);
			
			ps.setString( 1, DNI);
 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) trabajador = new TTrabajador(rs.getInt("id"),rs.getString("DNI"),rs.getInt("telefono"), rs.getString("nombre"), rs.getInt("activo") == 1? true : false, rs.getInt("seccion_id"));

		} catch(Exception e) {
			System.out.println(e);
		}  
		return trabajador;
	}
	
	public Set<TTrabajador> readBySeccion(Integer IdSeccion) {
		Connection con = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		String s = new String("SELECT trabajador_id FROM trabajador_seccion WHERE seccion_id = ? FOR UPDATE");        
		Set<TTrabajador> lista = new HashSet<TTrabajador>();

        try{
            PreparedStatement ps = con.prepareStatement(s);
            
            ps.setInt( 1, IdSeccion);
 
            ResultSet rs = ps.executeQuery();

            List<Integer> array = new ArrayList<>();
            while ( rs.next() ) {
            	int id = rs.getInt("trabajador_id");
            	array.add(id);
            }
            for(Integer x: array){
            	TTrabajador res = this.readById(x);
            	res.setIdSeccion(IdSeccion);
                lista.add(res);
            }
            if (lista.isEmpty()) lista.add(null);

        } catch(Exception e) {
        	System.out.println(e);
        }
        return lista;
	}
}