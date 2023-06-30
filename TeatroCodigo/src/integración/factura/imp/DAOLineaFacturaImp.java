
package integración.factura.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.factura.DAOLineaFactura;
import integración.transacciones.TransactionManager;
import negocio.factura.TLineaFactura;

public class DAOLineaFacturaImp implements DAOLineaFactura {

	public int create(TLineaFactura tLineaFactura) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_FACT WRITE");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO LINEA_FACT (id_actividad, id_factura, num_entradas, activo) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tLineaFactura.getIdActividad());
			ps.setInt(2, tLineaFactura.getIdFactura());
			ps.setInt(3, tLineaFactura.getCantidad());
			ps.setBoolean(4, true);
			ps.executeUpdate();
			id = tLineaFactura.getIdFactura();
			ps.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TLineaFactura read(int idActividad, int idFactura) {
		TLineaFactura tlf = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_FACT READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM LINEA_FACT WHERE id_actividad=? AND id_factura=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idActividad);
			ps.setInt(2, idFactura);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tlf = new TLineaFactura(rs.getInt("id_actividad"), rs.getInt("id_factura"), rs.getInt("num_entradas"));
			}
			ps.close();
		} catch (SQLException e) {
			return null;
		}
		return tlf;
	}

	public int update(TLineaFactura tLineaFactura) {
		int id = -1;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_FACT WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE LINEA_FACT SET num_entradas=? WHERE id_actividad=? AND id_factura=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tLineaFactura.getCantidad());
			ps.setInt(2, tLineaFactura.getIdActividad());
			ps.setInt(3, tLineaFactura.getIdFactura());

			ps.executeUpdate();
			id = tLineaFactura.getIdFactura();
			ps.close();
		}

		catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public Collection<TLineaFactura> readByFactura(int idFactura) {

		Collection<TLineaFactura> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_FACT READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LINEA_FACT WHERE id_factura=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idFactura);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TLineaFactura tlf = new TLineaFactura(rs.getInt("id_actividad"), rs.getInt("id_factura"),
						rs.getInt("num_entradas"));
				list.add(tlf);
			}
			ps.close();
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	public Collection<TLineaFactura> readByActividad(int idActividad) {
		Collection<TLineaFactura> list = new ArrayList<>();
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_FACT READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LINEA_FACT WHERE id_actividad=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idActividad);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TLineaFactura tlf = new TLineaFactura(rs.getInt("id_actividad"), rs.getInt("id_factura"),
						rs.getInt("num_entradas"));
				list.add(tlf);
			}
			ps.close();
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	@Override
	public int deleteFisico(int idFactura, int idActividad) {
		int id = idFactura;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES LINEA_FACT WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"DELETE FROM LINEA_FACT WHERE id_actividad=? AND id_factura=?", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idActividad);
			ps.setInt(2, idFactura);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			id = -1;
		}
		return id;
	}
}