package integraci�n.compa�ia.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integraci�n.compa�ia.DAOCompa�ia;
import integraci�n.transacciones.TransactionManager;
import negocio.compa�ia.TCompa�ia;

public class DAOCompa�iaImp implements DAOCompa�ia {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void Operation1() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void Operation2() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public int create(TCompa�ia tCompa�ia) {

		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO COMPA�IA (nombre, tipo_comp, activo) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCompa�ia.getNombre());
			ps.setString(2, tCompa�ia.getTipo());
			ps.setBoolean(3, true);

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
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE COMPA�IA SET activo=false WHERE id_compa�ia=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			aux.close();

		} catch (SQLException e) {
			id = -1;
		}
		return id;
	}

	public TCompa�ia read(int id) {

		TCompa�ia toc = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM COMPA�IA WHERE id_compa�ia=? AND activo=true", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				toc = new TCompa�ia(rs.getInt("id_compa�ia"), rs.getString("nombre"), rs.getString("tipo_comp"),
						rs.getBoolean("activo"));
			}

			ps.close();
			aux.close();

		} catch (SQLException e) {
			return null;
		}

		return toc;
	}

	public Collection<TCompa�ia> readAll() {

		Collection<TCompa�ia> list = new ArrayList<>();

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM COMPA�IA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TCompa�ia toc = new TCompa�ia(rs.getInt("id_compa�ia"), rs.getString("nombre"),
						rs.getString("tipo_comp"), rs.getBoolean("activo"));
				list.add(toc);
			}
			ps.close();
			aux.close();
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	public TCompa�ia readByName(String nombre) {

		TCompa�ia toc = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM COMPA�IA WHERE nombre=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				toc = new TCompa�ia(rs.getInt("id_compa�ia"), rs.getString("nombre"), rs.getString("tipo_comp"),
						rs.getBoolean("activo"));
			}
			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return toc;
	}

	public int update(TCompa�ia tCompa�ia) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE COMPA�IA SET nombre=?, tipo_comp=?, activo=? WHERE id_compa�ia=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCompa�ia.getNombre());
			ps.setString(2, tCompa�ia.getTipo());
			ps.setBoolean(3, true);
			id = tCompa�ia.getId();
			ps.setInt(4, id);
			ps.executeUpdate();
			aux.close();
			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public int deleteFisico(int id) {
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPA�IA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("DELETE FROM COMPA�IA WHERE id_compa�ia=?",
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