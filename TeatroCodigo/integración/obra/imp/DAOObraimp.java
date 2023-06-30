package integración.obra.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integración.obra.DAOObra;
import integración.transacciones.TransactionManager;
import negocio.obra.TObra;

public class DAOObraimp implements DAOObra {

	public int create(TObra tObra) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA WRITE");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO OBRA (sinopsis,titulo, genero, autor, año, activo) VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, tObra.getSinopsis());
			ps.setString(2, tObra.getTitulo());
			ps.setString(3, tObra.getGenero());
			ps.setString(4, tObra.getAutor());
			ps.setInt(5, tObra.getAnio());
			ps.setBoolean(6, tObra.getActivo());
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
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE OBRA SET activo=false WHERE id_obra=? AND activo=true", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.getGeneratedKeys();
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TObra read(int id) {
		TObra tobra = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM OBRA WHERE id_obra=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				tobra = new TObra(id, rs.getString(4), rs.getString(2), rs.getString(3), rs.getString(7), rs.getInt(5),
						true);
			}

			ps.close();
		}

		catch (SQLException e) {
			return null;
		}

		return tobra;
	}

	public Collection<TObra> readAll() {
		Collection<TObra> list = new ArrayList<>();

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM OBRA WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TObra tobra = new TObra(rs.getInt(1), rs.getString(4), rs.getString(2), rs.getString(3),
						rs.getString(7), rs.getInt(5), rs.getBoolean(6));
				list.add(tobra);
			}
			ps.close();
		}

		catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	public int update(TObra tObra) {
		int id = -1;

		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA WRITE");
			aux.execute();
			aux.close();

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE OBRA SET sinopsis=?,titulo=?, genero=?, autor=?, año=?, activo=? WHERE id_obra=?",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, tObra.getSinopsis());
			ps.setString(2, tObra.getTitulo());
			ps.setString(3, tObra.getGenero());
			ps.setString(4, tObra.getAutor());
			ps.setInt(5, tObra.getAnio());
			ps.setBoolean(6, true);
			id = tObra.getId();
			ps.setInt(7, id);
			ps.executeUpdate();
			ps.close();
		}

		catch (SQLException e) {
			return -1;
		}
		return id;
	}

	public TObra readByName(String titulo) {
		TObra tobra = null;
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM OBRA WHERE titulo=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tobra = new TObra(rs.getInt(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getString(5),
						rs.getInt(6), rs.getBoolean(7));
			}

			ps.close();
		}

		catch (SQLException e) {
			return null;
		}
		return tobra;
	}

	public int deleteFisico(int id) {
		Connection connection = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = connection.prepareStatement("LOCK TABLES OBRA WRITE");
			aux.execute();
			aux.close();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM OBRA WHERE id_obra=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.getGeneratedKeys();
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

}