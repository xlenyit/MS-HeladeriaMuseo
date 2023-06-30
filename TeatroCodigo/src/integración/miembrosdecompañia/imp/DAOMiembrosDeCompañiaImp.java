package integración.miembrosdecompañia.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.miembrosdecompañia.DAOMiembrosDeCompañia;
import integración.transacciones.TransactionManager;
import negocio.miembrosdecompañia.TMiembrosDeCompañia;

public class DAOMiembrosDeCompañiaImp implements DAOMiembrosDeCompañia {

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompañia#create(TMiembrosDeCompañia tMiembrosDeCompañia, int Parameter1)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void create(TMiembrosDeCompañia tMiembrosDeCompañia, int Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompañia#delete(int id, int Parameter1)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void delete(int id, int Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public int create(TMiembrosDeCompañia tMiembrosDeCompañia) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {

			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO MIEMBROS_COMPAÑIA (dni, nombre, apellidos, tipo, activo) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tMiembrosDeCompañia.getDNI());
			ps.setString(2, tMiembrosDeCompañia.getNombre());
			ps.setString(3, tMiembrosDeCompañia.getApellidos());
			ps.setString(4, tMiembrosDeCompañia.getTipo());
			ps.setBoolean(5, tMiembrosDeCompañia.getActivo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			ps.close();
			aux.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public int delete(int id) {
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MIEMBROS_COMPAÑIA SET ACTIVO=? WHERE id_miembro=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
			aux.close();

		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public TMiembrosDeCompañia read(int id) {
		TMiembrosDeCompañia tom = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM MIEMBROS_COMPAÑIA WHERE id_miembro=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tom = new TMiembrosDeCompañia(rs.getInt("id_miembro"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("tipo"), rs.getString("dni"), rs.getBoolean("activo"));
			}

			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return tom;
	}

	public Collection<TMiembrosDeCompañia> readAll() {
		Collection<TMiembrosDeCompañia> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM MIEMBROS_COMPAÑIA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TMiembrosDeCompañia tom = new TMiembrosDeCompañia(rs.getInt("id_miembro"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("tipo"), rs.getString("dni"), rs.getBoolean("activo"));
				list.add(tom);
			}
			ps.close();
			aux.close();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	public int update(TMiembrosDeCompañia tMiembrosDeCompañia) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MIEMBROS_COMPAÑIA SET dni=?, nombre=?, apellidos=?, tipo=?, activo=? WHERE id_miembro=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tMiembrosDeCompañia.getDNI());
			ps.setString(2, tMiembrosDeCompañia.getNombre());
			ps.setString(3, tMiembrosDeCompañia.getApellidos());
			ps.setString(4, tMiembrosDeCompañia.getTipo());
			ps.setBoolean(5, true);
			id = tMiembrosDeCompañia.getId();
			ps.setInt(6, id);
			ps.executeUpdate();
			ps.close();
			aux.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TMiembrosDeCompañia readByName(String dni) {
		TMiembrosDeCompañia tom = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM MIEMBROS_COMPAÑIA WHERE dni=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tom = new TMiembrosDeCompañia(rs.getInt("id_miembro"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("tipo"), rs.getString("dni"), rs.getBoolean("activo"));
			}
			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return tom;

	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompañia#addToCompany(int idMiembro, int idCompañia)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompañia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompañia#removeFromCompany(int idMiembro, int idCompañia)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idMiembro, int idCompañia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompañia#updateMeses(int idCompañia, int idMiembro, int numMeses)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idCompañia, int idMiembro, int numMeses) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	@Override
	public int deleteFisico(int id) {

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {

			PreparedStatement aux = c.prepareStatement("LOCK TABLES MIEMBROS_COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = c.prepareStatement("DELETE FROM MIEMBROS_COMPAÑIA WHERE id_miembro=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			aux.close();

		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

}