package integraci�n.miembrosdecompa�ia.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integraci�n.miembrosdecompa�ia.DAOMiembrosDeCompa�ia;
import integraci�n.transacciones.TransactionManager;
import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;

public class DAOMiembrosDeCompa�iaImp implements DAOMiembrosDeCompa�ia {

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompa�ia#create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia, int Parameter1)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia, int Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompa�ia#delete(int id, int Parameter1)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void delete(int id, int Parameter1) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public int create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {

			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO MIEMBROS_COMPA�IA (dni, nombre, apellidos, tipo, activo) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tMiembrosDeCompa�ia.getDNI());
			ps.setString(2, tMiembrosDeCompa�ia.getNombre());
			ps.setString(3, tMiembrosDeCompa�ia.getApellidos());
			ps.setString(4, tMiembrosDeCompa�ia.getTipo());
			ps.setBoolean(5, tMiembrosDeCompa�ia.getActivo());
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
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MIEMBROS_COMPA�IA SET ACTIVO=? WHERE id_miembro=? AND activo=true",
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

	public TMiembrosDeCompa�ia read(int id) {
		TMiembrosDeCompa�ia tom = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM MIEMBROS_COMPA�IA WHERE id_miembro=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tom = new TMiembrosDeCompa�ia(rs.getInt("id_miembro"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("tipo"), rs.getString("dni"), rs.getBoolean("activo"));
			}

			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return tom;
	}

	public Collection<TMiembrosDeCompa�ia> readAll() {
		Collection<TMiembrosDeCompa�ia> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM MIEMBROS_COMPA�IA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TMiembrosDeCompa�ia tom = new TMiembrosDeCompa�ia(rs.getInt("id_miembro"), rs.getString("nombre"),
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

	public int update(TMiembrosDeCompa�ia tMiembrosDeCompa�ia) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MIEMBROS_COMPA�IA SET dni=?, nombre=?, apellidos=?, tipo=?, activo=? WHERE id_miembro=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tMiembrosDeCompa�ia.getDNI());
			ps.setString(2, tMiembrosDeCompa�ia.getNombre());
			ps.setString(3, tMiembrosDeCompa�ia.getApellidos());
			ps.setString(4, tMiembrosDeCompa�ia.getTipo());
			ps.setBoolean(5, true);
			id = tMiembrosDeCompa�ia.getId();
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

	public TMiembrosDeCompa�ia readByName(String dni) {
		TMiembrosDeCompa�ia tom = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM MIEMBROS_COMPA�IA WHERE dni=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tom = new TMiembrosDeCompa�ia(rs.getInt("id_miembro"), rs.getString("nombre"),
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
	* @see DAOMiembrosDeCompa�ia#addToCompany(int idMiembro, int idCompa�ia)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompa�ia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompa�ia#removeFromCompany(int idMiembro, int idCompa�ia)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idMiembro, int idCompa�ia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOMiembrosDeCompa�ia#updateMeses(int idCompa�ia, int idMiembro, int numMeses)
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idCompa�ia, int idMiembro, int numMeses) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	@Override
	public int deleteFisico(int id) {

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {

			PreparedStatement aux = c.prepareStatement("LOCK TABLES MIEMBROS_COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = c.prepareStatement("DELETE FROM MIEMBROS_COMPA�IA WHERE id_miembro=?",
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