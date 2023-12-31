package integraciķn.miembrosdecompaņia.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integraciķn.miembrosdecompaņia.DAOMiembrosDeCompaņia;
import integraciķn.transacciones.TransactionManager;
import negocio.miembrosdecompaņia.TMiembrosDeCompaņia;

public class DAOMiembrosDeCompaņiaImp implements DAOMiembrosDeCompaņia {

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompaņia#create(TMiembrosDeCompaņia tMiembrosDeCompaņia, int Parameter1)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void create(TMiembrosDeCompaņia tMiembrosDeCompaņia, int Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompaņia#delete(int id, int Parameter1)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void delete(int id, int Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public int create(TMiembrosDeCompaņia tMiembrosDeCompaņia) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {

			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO MIEMBROS_COMPAŅIA (dni, nombre, apellidos, tipo, activo) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tMiembrosDeCompaņia.getDNI());
			ps.setString(2, tMiembrosDeCompaņia.getNombre());
			ps.setString(3, tMiembrosDeCompaņia.getApellidos());
			ps.setString(4, tMiembrosDeCompaņia.getTipo());
			ps.setBoolean(5, tMiembrosDeCompaņia.getActivo());
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
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MIEMBROS_COMPAŅIA SET ACTIVO=? WHERE id_miembro=? AND activo=true",
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

	public TMiembrosDeCompaņia read(int id) {
		TMiembrosDeCompaņia tom = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM MIEMBROS_COMPAŅIA WHERE id_miembro=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tom = new TMiembrosDeCompaņia(rs.getInt("id_miembro"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("tipo"), rs.getString("dni"), rs.getBoolean("activo"));
			}

			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return tom;
	}

	public Collection<TMiembrosDeCompaņia> readAll() {
		Collection<TMiembrosDeCompaņia> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM MIEMBROS_COMPAŅIA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TMiembrosDeCompaņia tom = new TMiembrosDeCompaņia(rs.getInt("id_miembro"), rs.getString("nombre"),
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

	public int update(TMiembrosDeCompaņia tMiembrosDeCompaņia) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MIEMBROS_COMPAŅIA SET dni=?, nombre=?, apellidos=?, tipo=?, activo=? WHERE id_miembro=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tMiembrosDeCompaņia.getDNI());
			ps.setString(2, tMiembrosDeCompaņia.getNombre());
			ps.setString(3, tMiembrosDeCompaņia.getApellidos());
			ps.setString(4, tMiembrosDeCompaņia.getTipo());
			ps.setBoolean(5, true);
			id = tMiembrosDeCompaņia.getId();
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

	public TMiembrosDeCompaņia readByName(String dni) {
		TMiembrosDeCompaņia tom = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM MIEMBROS_COMPAŅIA WHERE dni=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tom = new TMiembrosDeCompaņia(rs.getInt("id_miembro"), rs.getString("nombre"),
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
	* @see DAOMiembrosDeCompaņia#addToCompany(int idMiembro, int idCompaņia)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompaņia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompaņia#removeFromCompany(int idMiembro, int idCompaņia)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idMiembro, int idCompaņia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompaņia#updateMeses(int idCompaņia, int idMiembro, int numMeses)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idCompaņia, int idMiembro, int numMeses) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	@Override
	public int deleteFisico(int id) {

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {

			PreparedStatement aux = c.prepareStatement("LOCK TABLES MIEMBROS_COMPAŅIA WRITE");
			aux.execute();

			PreparedStatement ps = c.prepareStatement("DELETE FROM MIEMBROS_COMPAŅIA WHERE id_miembro=?",
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