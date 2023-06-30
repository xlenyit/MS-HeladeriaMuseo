package integración.cliente.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.cliente.DAOCliente;
import integración.transacciones.TransactionManager;
import negocio.cliente.TCliente;

public class DAOClienteImp implements DAOCliente {

	public int create(TCliente tCliente) {

		int id = -1;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES CLIENTE WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO CLIENTE (dni, nombre, apellidos, socio, activo) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCliente.getDni());
			ps.setString(2, tCliente.getNombre());
			ps.setString(3, tCliente.getApellidos());
			ps.setBoolean(4, tCliente.getSocio());
			ps.setBoolean(5, tCliente.getActivo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next())
				id = rs.getInt(1);
			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public int delete(int id) {

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES CLIENTE WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE CLIENTE SET activo=false WHERE id_cliente=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public TCliente read(int id) {

		TCliente tCliente = null;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES CLIENTE READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM CLIENTE WHERE id_cliente=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tCliente = new TCliente(id, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getBoolean("socio"), rs.getBoolean("activo"));
			}
			ps.close();
		} catch (SQLException e) {
			return null;
		}

		return tCliente;
	}

	public Collection<TCliente> readAll() {

		Collection<TCliente> list = new ArrayList<>();

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES CLIENTE READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM CLIENTE WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TCliente tCliente = new TCliente(rs.getInt("id_cliente"), rs.getString("dni"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getBoolean("socio"), rs.getBoolean("activo"));
				list.add(tCliente);
			}

			ps.close();
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}

		return list;
	}

	public int update(TCliente tCliente) {

		int id = -1;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES CLIENTE WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE CLIENTE SET dni=?, nombre=?, apellidos=?, socio=?, activo=? WHERE id_cliente=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tCliente.getDni());
			ps.setString(2, tCliente.getNombre());
			ps.setString(3, tCliente.getApellidos());
			ps.setBoolean(4, tCliente.getSocio());
			ps.setBoolean(5, true);
			id = tCliente.getId();
			ps.setInt(6, id);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public TCliente readByName(String dni) {

		TCliente tCliente = null;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES CLIENTE READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM CLIENTE WHERE dni=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tCliente = new TCliente(rs.getInt("id_cliente"), rs.getString("dni"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getBoolean("socio"), rs.getBoolean("activo"));
			}
			ps.close();
		} catch (SQLException e) {
			return null;
		}

		return tCliente;
	}

	@Override
	public int deleteFisico(int id) {
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES CLIENTE WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM CLIENTE WHERE id_cliente=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			id = -1;
		}
		return id;
	}
}