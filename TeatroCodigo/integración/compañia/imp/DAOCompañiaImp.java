package integración.compañia.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.compañia.DAOCompañia;
import integración.transacciones.TransactionManager;
import negocio.compañia.TCompañia;

public class DAOCompañiaImp implements DAOCompañia {

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

	public int create(TCompañia tCompañia) {

		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO COMPAÑIA (nombre, tipo_comp, activo) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCompañia.getNombre());
			ps.setString(2, tCompañia.getTipo());
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
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE COMPAÑIA SET activo=false WHERE id_compañia=? AND activo=true",
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

	public TCompañia read(int id) {

		TCompañia toc = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM COMPAÑIA WHERE id_compañia=? AND activo=true", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				toc = new TCompañia(rs.getInt("id_compañia"), rs.getString("nombre"), rs.getString("tipo_comp"),
						rs.getBoolean("activo"));
			}

			ps.close();
			aux.close();

		} catch (SQLException e) {
			return null;
		}

		return toc;
	}

	public Collection<TCompañia> readAll() {

		Collection<TCompañia> list = new ArrayList<>();

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM COMPAÑIA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TCompañia toc = new TCompañia(rs.getInt("id_compañia"), rs.getString("nombre"),
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

	public TCompañia readByName(String nombre) {

		TCompañia toc = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA READ");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM COMPAÑIA WHERE nombre=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				toc = new TCompañia(rs.getInt("id_compañia"), rs.getString("nombre"), rs.getString("tipo_comp"),
						rs.getBoolean("activo"));
			}
			ps.close();
			aux.close();
		} catch (SQLException e) {
			return null;
		}
		return toc;
	}

	public int update(TCompañia tCompañia) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE COMPAÑIA SET nombre=?, tipo_comp=?, activo=? WHERE id_compañia=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCompañia.getNombre());
			ps.setString(2, tCompañia.getTipo());
			ps.setBoolean(3, true);
			id = tCompañia.getId();
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
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES COMPAÑIA WRITE");
			aux.execute();

			PreparedStatement ps = connection.prepareStatement("DELETE FROM COMPAÑIA WHERE id_compañia=?",
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