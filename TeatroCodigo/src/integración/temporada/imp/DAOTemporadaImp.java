package integración.temporada.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.temporada.DAOTemporada;
import integración.transacciones.TransactionManager;
import negocio.temporada.TTemporada;

public class DAOTemporadaImp implements DAOTemporada {
	public int create(TTemporada tTemporada) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES TEMPORADA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO TEMPORADA (num_temporada, calificacion, fecha_ini, fecha_fin, activo) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tTemporada.getNumTemporada());
			ps.setDouble(2, tTemporada.getCalificacion());
			ps.setDate(3, tTemporada.getFechaInicio());
			ps.setDate(4, tTemporada.getFechaFin());
			ps.setBoolean(5, tTemporada.getActivo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public int delete(int id) {

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES TEMPORADA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE TEMPORADA SET activo=? WHERE id_temporada=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TTemporada read(int id) {
		TTemporada tot = null;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES TEMPORADA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM TEMPORADA WHERE id_temporada=? and activo = true", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tot = new TTemporada(id, rs.getInt("num_temporada"), rs.getDouble("calificacion"),
						rs.getDate("fecha_ini"), rs.getDate("fecha_fin"), rs.getBoolean("activo"));
			}
			ps.close();
		} catch (SQLException e) {
			return null;
		}
		return tot;

	}

	public int update(TTemporada tTemporada) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES TEMPORADA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE TEMPORADA SET num_temporada=?, calificacion=?, fecha_ini=?, fecha_fin=?, activo=? WHERE id_temporada=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tTemporada.getNumTemporada());
			ps.setDouble(2, tTemporada.getCalificacion());
			ps.setDate(3, tTemporada.getFechaInicio());
			ps.setDate(4, tTemporada.getFechaFin());
			ps.setBoolean(5, true);
			id = tTemporada.getId();
			ps.setInt(6, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public TTemporada readByName(int num) {
		TTemporada tot = null;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES TEMPORADA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEMPORADA WHERE num_temporada=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tot = new TTemporada(rs.getInt("id_temporada"), rs.getInt("num_temporada"),
						rs.getDouble("calificacion"), rs.getDate("fecha_ini"), rs.getDate("fecha_fin"),
						rs.getBoolean("activo"));
			}
			ps.close();
		} catch (SQLException e) {
			return null;
		}
		return tot;
	}

	@Override
	public Collection<TTemporada> readAll() {
		Collection<TTemporada> list = new ArrayList<>();

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES TEMPORADA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEMPORADA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TTemporada tot = new TTemporada(rs.getInt("id_temporada"), rs.getInt("num_temporada"),
						rs.getDouble("calificacion"), rs.getDate("fecha_ini"), rs.getDate("fecha_fin"),
						rs.getBoolean("activo"));
				list.add(tot);
			}
			ps.close();
		} catch (SQLException e) {
			return new ArrayList<>();
		}

		return list;
	}

	public int deleteFisico(int id) {
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = connection.prepareStatement("LOCK TABLES TEMPORADA WRITE");
			ps.execute();
			ps.close();

			ps = connection.prepareStatement("DELETE FROM TEMPORADA WHERE id_temporada=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}
}