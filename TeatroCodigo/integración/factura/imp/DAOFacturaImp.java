package integración.factura.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.factura.DAOFactura;
import integración.transacciones.TransactionManager;
import negocio.factura.TFactura;

public class DAOFacturaImp implements DAOFactura {

	public int create(TFactura tFactura) {
		int id = 0;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA WRITE");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO FACTURA (id_cliente, precio_tot, n_act, activo) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tFactura.getIdCliente());
			ps.setDouble(2, tFactura.getPrecioTotal());
			ps.setInt(3, tFactura.getNumActividades());
			ps.setBoolean(4, tFactura.getActivo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			ps.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public int delete(int id) {

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE FACTURA SET activo=? WHERE id_factura=? AND activo=true", Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TFactura read(int id) {
		TFactura tof = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM FACTURA WHERE id_factura=? AND activo=true", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tof = new TFactura(rs.getInt("id_factura"), rs.getDouble("precio_tot"), rs.getInt("n_act"),
						rs.getInt("id_cliente"));
			}

			ps.close();
		} catch (SQLException e) {
			return null;
		}
		return tof;
	}

	public Collection<TFactura> readAll() {
		Collection<TFactura> list = new ArrayList<TFactura>();

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA READ");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM FACTURA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TFactura toF = new TFactura(rs.getInt("id_factura"), rs.getDouble("precio_tot"), rs.getInt("n_act"),
						rs.getInt("id_cliente"));
				;
				list.add(toF);
			}
			ps.close();
		}

		catch (SQLException e) {
			return new ArrayList<TFactura>();
		}
		return list;
	}

	public int update(TFactura tFactura) {

		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA WRITE");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE FACTURA SET id_cliente=?, precio_tot=?, n_act=?, activo=? WHERE id_factura=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tFactura.getIdCliente());
			ps.setDouble(2, tFactura.getPrecioTotal());
			ps.setInt(3, tFactura.getNumActividades());
			ps.setBoolean(4, true);
			id = tFactura.getId();
			ps.setInt(5, id);
			ps.executeUpdate();
			ps.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	@Override
	public Collection<TFactura> readByCliente(int id) {
		Collection<TFactura> list = new ArrayList<TFactura>();

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA READ");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM FACTURA WHERE activo=true AND id_cliente=?", Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TFactura toF = new TFactura(rs.getInt("id_factura"), rs.getDouble("precio_tot"), rs.getInt("n_act"),
						rs.getInt("id_cliente"));
				list.add(toF);
			}
			ps.close();
		}

		catch (SQLException e) {
			return new ArrayList<TFactura>();
		}
		return list;
	}

	@Override
	public int deleteFisico(int id) {

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES FACTURA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM FACTURA WHERE id_factura=?",
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