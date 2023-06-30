package integración.miembrosdecompañia.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.miembrosdecompañia.DAOLineaMiembros;
import integración.transacciones.TransactionManager;
import negocio.miembrosdecompañia.TLineaMiembro;
public class DAOLineaMiembrosImp implements DAOLineaMiembros {

	public int addToCompany(TLineaMiembro tLineaMiembro) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_MIEMBRO WRITE");
			aux.execute();
			
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO LINEA_MIEMBRO (id_lmiembro, id_lcompañia, num_meses) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, tLineaMiembro.getIdMiembro());
			ps.setInt(2, tLineaMiembro.getIdCompañia());
			ps.setInt(3, tLineaMiembro.getNMeses());
			id = tLineaMiembro.getIdMiembro();

			ps.executeUpdate();
			ps.close();
			aux.close();
		}

		catch (SQLException e) {
			return -1;
		}
		
		return id;
	}

	public int removeFromCompany(TLineaMiembro tLineaMiembro) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_MIEMBRO WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("DELETE FROM LINEA_MIEMBRO WHERE id_lmiembro=? AND id_lcompañia=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tLineaMiembro.getIdMiembro());
			ps.setInt(2, tLineaMiembro.getIdCompañia());
			ps.executeUpdate();
			ps.close();
			aux.close();
			id = tLineaMiembro.getIdMiembro();

		} catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public int updateMeses(TLineaMiembro tLineaMiembro) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_MIEMBRO WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE LINEA_MIEMBRO SET num_meses=? WHERE id_lmiembro=? AND id_lcompañia=?",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, tLineaMiembro.getNMeses());
			ps.setInt(2, tLineaMiembro.getIdMiembro());
			ps.setInt(3, tLineaMiembro.getIdCompañia());

			ps.executeUpdate();
			id = tLineaMiembro.getIdMiembro();
			ps.close();
			aux.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TLineaMiembro read(int idMiembro, int idCompañia) {
		TLineaMiembro tomc = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_MIEMBRO READ");
			aux.execute();
			

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LINEA_MIEMBRO WHERE id_lmiembro=? AND id_lcompañia=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idMiembro);
			ps.setInt(2, idCompañia);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tomc = new TLineaMiembro(rs.getInt("id_lcompañia"), rs.getInt("id_lmiembro"), rs.getInt("num_meses"));
			}
			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return tomc;
	}

	public Collection<TLineaMiembro> readByCompañia(int idCompañia) {
		Collection<TLineaMiembro> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_MIEMBRO READ");
			aux.execute();	
			
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM LINEA_MIEMBRO WHERE id_lcompañia=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idCompañia);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TLineaMiembro tomc = new TLineaMiembro(rs.getInt("id_lcompañia"), rs.getInt("id_lmiembro"),
						rs.getInt("num_meses"));
				list.add(tomc);
			}
			
			ps.close();
			aux.close();	
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}
	
	public Collection<TLineaMiembro> readByMiembro(int idMiembro) {
		Collection<TLineaMiembro> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_MIEMBRO READ");
			aux.execute();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM LINEA_MIEMBRO WHERE id_lmiembro=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idMiembro);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TLineaMiembro tomc = new TLineaMiembro(rs.getInt("id_lcompañia"), rs.getInt("id_lmiembro"),
						rs.getInt("num_meses"));
				list.add(tomc);
			}
			ps.close();
			aux.close();
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	public int deleteFisicoMiembro(int id) {
		
		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		try {
			
			PreparedStatement aux = c.prepareStatement("LOCK TABLES LINEA_MIEMBRO WRITE");
			aux.execute();
			
			PreparedStatement ps = c.prepareStatement("DELETE FROM LINEA_MIEMBRO WHERE id_lmiembro=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			aux.close();
			
		} catch (SQLException e){ 
			return -1;
		}
		
		return id;
	}
	
	public int deleteFisicoCompañia(int id) {
		
		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
		
		try {
			
			PreparedStatement aux = c.prepareStatement("LOCK TABLES LINEA_MIEMBRO WRITE");
			aux.execute();
			
			PreparedStatement ps = c.prepareStatement("DELETE FROM LINEA_MIEMBRO WHERE id_lcompañia=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			aux.close();
			
		} catch (SQLException e){ 
			return -1;
		}
		
		return id;
	}
	
}
