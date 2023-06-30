package integración.actividad.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import integración.actividad.DAOActividad;
import integración.transacciones.TransactionManager;
import negocio.actividad.TActCultural;
import negocio.actividad.TActividad;
import negocio.actividad.TRepresentacion;

public class DAOActividadImp implements DAOActividad {

	public int create(TActividad tactividad) {

		int id = -1;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = c
					.prepareStatement("LOCK TABLES ACTIVIDAD WRITE, REPRESENTACION WRITE, ACTIVIDAD_CULTURAL WRITE");
			ps.execute();
			ps.close();

			ps = c.prepareStatement(
					"INSERT INTO ACTIVIDAD (tipo,fecha_ini,fecha_fin,hora,duracion,precio,n_entradas_dis,activo) VALUES (?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, tactividad.getTipo());
			ps.setDate(2, tactividad.getFechaInicio());
			ps.setDate(3, tactividad.getFechaFin());
			ps.setTime(4, tactividad.getHora());
			ps.setInt(5, tactividad.getDuracion());
			ps.setDouble(6, tactividad.getPrecio());
			ps.setInt(7, tactividad.getEntradasDisponibles());
			ps.setBoolean(8, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			ps.close();

			if (rs.next()) {
				id = rs.getInt(1);
			}

			if (tactividad.getTipo() == "representacion") {
				ps = c.prepareStatement(
						"INSERT INTO REPRESENTACION (id_actividad, id_compañia, id_obra, id_temporada, activo) VALUES (?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				TRepresentacion tRep = (TRepresentacion) tactividad;
				ps.setInt(1, id);
				ps.setInt(2, tRep.getIdCompañia());
				ps.setInt(3, tRep.getIdObra());
				ps.setInt(4, tRep.getIdTemporada());
				ps.setBoolean(5, true);
				ps.executeUpdate();
				ps.close();
			} else if (tactividad.getTipo() == "cultural") {
				ps = c.prepareStatement("INSERT INTO ACTIVIDAD_CULTURAL (id_actividad, titulo, activo) VALUES (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				TActCultural tAC = (TActCultural) tactividad;
				ps.setInt(1, id);
				ps.setString(2, tAC.getTitulo());
				ps.setBoolean(3, true);
				ps.executeUpdate();
				ps.close();
			}
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public int delete(int id) {

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			TActividad tActividad = read(id);
			String tipo = tActividad.getTipo();
			if (tipo == "representacion")
				tipo = "REPRESENTACION";
			else if (tipo == "cultural")
				tipo = "ACTIVIDAD_CULTURAL";

			PreparedStatement ps = c.prepareStatement("LOCK TABLES " + tipo + " WRITE, ACTIVIDAD WRITE");
			ps.execute();
			ps.close();

			ps = c.prepareStatement("UPDATE " + tipo + " SET activo=false WHERE id_actividad=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

			ps = c.prepareStatement("UPDATE ACTIVIDAD SET activo=false WHERE id_actividad=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			id = -1;
		}

		return id;
	}

	public TActividad read(int id) {

		TActividad tActividad = null;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = c
					.prepareStatement("LOCK TABLES ACTIVIDAD READ, REPRESENTACION READ, ACTIVIDAD_CULTURAL READ");
			ps.execute();
			ps.close();

			ps = c.prepareStatement("SELECT * FROM ACTIVIDAD WHERE id_actividad=? and activo=true",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ps.close();

			while (rs.next()) {

				if (rs.getString("tipo").equals("representacion")) {
					ps = c.prepareStatement("SELECT * FROM REPRESENTACION WHERE id_actividad=? ",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, id);

					ResultSet rsr = ps.executeQuery();
					ps.close();

					if (rsr.next()) {
						tActividad = new TRepresentacion(id, rs.getInt("n_entradas_dis"), rs.getDouble("precio"),
								rs.getDate("fecha_ini"), rs.getDate("fecha_fin"), rs.getTime("hora"),
								rs.getInt("duracion"), rsr.getInt("id_obra"), rsr.getInt("id_compañia"),
								rsr.getInt("id_temporada"), rs.getBoolean("activo"));
					}

				}

				else if (rs.getString("tipo").equals("cultural")) {
					ps = c.prepareStatement("SELECT * FROM ACTIVIDAD_CULTURAL WHERE id_actividad=? ",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, id);

					ResultSet rsc = ps.executeQuery();
					ps.close();

					if (rsc.next()) {
						tActividad = new TActCultural(id, rsc.getString("titulo"), rs.getInt("n_entradas_dis"),
								rs.getDate("fecha_ini"), rs.getDate("fecha_fin"), rs.getTime("hora"),
								rs.getInt("duracion"), rs.getDouble("precio"), rs.getBoolean("activo"));
					}
				}
			}
		}

		catch (SQLException e) {
			return null;
		}

		return tActividad;
	}

	public Collection<TActividad> readAll() {

		Collection<TActividad> list = new ArrayList<>();

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c
					.prepareStatement("LOCK TABLES ACTIVIDAD READ, REPRESENTACION READ, ACTIVIDAD_CULTURAL READ");
			aux.execute();
			aux.close();

			PreparedStatement ps = c.prepareStatement("SELECT * FROM ACTIVIDAD WHERE activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			ps.close();
			while (rs.next()) {
				String tipo = rs.getString("tipo");
				if (tipo.equals("representacion")) {
					ps = c.prepareStatement("SELECT * FROM REPRESENTACION WHERE id_actividad=? ",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id_actividad"));

					ResultSet rsr = ps.executeQuery();
					ps.close();
					while (rsr.next()) {
						TRepresentacion tRep = new TRepresentacion(rs.getInt("id_actividad"),
								rs.getInt("n_entradas_dis"), rs.getDouble("precio"), rs.getDate("fecha_ini"),
								rs.getDate("fecha_fin"), rs.getTime("hora"), rs.getInt("duracion"),
								rsr.getInt("id_obra"), rsr.getInt("id_compañia"), rsr.getInt("id_temporada"),
								rs.getBoolean("activo"));
						list.add(tRep);
					}
				}

				else if (tipo.equals("cultural")) {
					ps = c.prepareStatement("SELECT * FROM ACTIVIDAD_CULTURAL WHERE id_actividad=? ",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id_actividad"));

					ResultSet rsc = ps.executeQuery();
					ps.close();
					while (rsc.next()) {
						TActCultural tAc = new TActCultural(rs.getInt("id_actividad"), rsc.getString("titulo"),
								rs.getInt("n_entradas_dis"), rs.getDate("fecha_ini"), rs.getDate("fecha_fin"),
								rs.getTime("hora"), rs.getInt("duracion"), rs.getDouble("precio"),
								rs.getBoolean("activo"));
						list.add(tAc);
					}
				}
			}
		} catch (SQLException e) {
			return new ArrayList<>();
		}

		return list;
	}

	public int update(TActividad tactividad) {

		int id = -1;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c
					.prepareStatement("LOCK TABLES ACTIVIDAD WRITE, REPRESENTACION WRITE, ACTIVIDAD_CULTURAL WRITE");
			aux.execute();
			aux.close();

			PreparedStatement ps = c.prepareStatement(
					"UPDATE ACTIVIDAD SET tipo=? , fecha_ini=?, fecha_fin=?, hora=?, duracion=?, precio=?, n_entradas_dis=?, activo=? WHERE id_actividad=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tactividad.getTipo());
			ps.setDate(2, tactividad.getFechaInicio());
			ps.setDate(3, tactividad.getFechaFin());
			ps.setTime(4, tactividad.getHora());
			ps.setInt(5, tactividad.getDuracion());
			ps.setDouble(6, tactividad.getPrecio());
			ps.setInt(7, tactividad.getEntradasDisponibles());
			ps.setBoolean(8, true);
			ps.setInt(9, tactividad.getId());
			ps.executeUpdate();
			ps.close();

			if (tactividad.getTipo() == "representacion") {

				TRepresentacion tRep = (TRepresentacion) tactividad;
				ps = c.prepareStatement(
						"UPDATE REPRESENTACION SET id_compañia=?, id_obra=?, id_temporada=?, activo=true WHERE id_actividad=?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(4, tRep.getId());
				ps.setInt(1, tRep.getIdCompañia());
				ps.setInt(2, tRep.getIdObra());
				ps.setInt(3, tRep.getIdTemporada());
				ps.executeUpdate();
				id = tRep.getId();
				ps.close();

			} else if (tactividad.getTipo() == "cultural") {
				TActCultural tAC = (TActCultural) tactividad;
				ps = c.prepareStatement("UPDATE ACTIVIDAD_CULTURAL SET titulo=?, activo=true WHERE id_actividad=?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, tAC.getTitulo());
				ps.setInt(2, tAC.getId());
				ps.executeUpdate();
				id = tAC.getId();
				ps.close();
			}
		} catch (SQLException e) {
			return -1;
		}

		return id;
	}

	public TActividad readByName(Date fechaInicio, Time hora) {

		TActividad tActividad = null;

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement aux = c.prepareStatement("LOCK TABLES ACTIVIDAD READ");
			aux.execute();
			aux.close();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM ACTIVIDAD WHERE fecha_ini = ? AND hora =?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, fechaInicio);
			ps.setTime(2, hora);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tActividad = new TActividad(rs.getInt("id_actividad"), rs.getString("tipo"),
						rs.getInt("n_entradas_dis"), rs.getDouble("precio"), rs.getDate("fecha_ini"),
						rs.getDate("fecha_fin"), rs.getTime("hora"), rs.getInt("duracion"), rs.getBoolean("activo"));
			}
			ps.close();
		} catch (SQLException e) {
			return null;
		}

		return tActividad;
	}

	@Override
	public Collection<TRepresentacion> readByTemporada(int id) {

		Collection<TRepresentacion> list = new ArrayList<>();
		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = c.prepareStatement("LOCK TABLES REPRESENTACION READ, ACTIVIDAD READ");
			ps.execute();
			ps.close();

			ps = c.prepareStatement(
					"SELECT * FROM ACTIVIDAD JOIN REPRESENTACION USING(id_actividad, activo) WHERE id_temporada=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rsr = ps.executeQuery();
			ps.close();
			while (rsr.next()) {
				TRepresentacion trep = new TRepresentacion(rsr.getInt("id_actividad"), rsr.getInt("n_entradas_dis"),
						rsr.getDouble("precio"), rsr.getDate("fecha_ini"), rsr.getDate("fecha_fin"),
						rsr.getTime("hora"), rsr.getInt("duracion"), rsr.getInt("id_obra"), rsr.getInt("id_compañia"),
						rsr.getInt("id_temporada"), rsr.getBoolean("activo"));
				list.add(trep);
			}

		}

		catch (SQLException e) {
			return null;
		}

		return list;
	}

	@Override
	public Collection<TRepresentacion> readByObra(int id) {

		Collection<TRepresentacion> list = new ArrayList<>();

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = c.prepareStatement("LOCK TABLES REPRESENTACION READ, ACTIVIDAD READ");
			ps.execute();
			ps.close();
			ps = c.prepareStatement(
					"SELECT * FROM ACTIVIDAD JOIN REPRESENTACION USING(id_actividad, activo) WHERE id_obra=? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rsr = ps.executeQuery();
			ps.close();
			while (rsr.next()) {
				TRepresentacion trep = new TRepresentacion(rsr.getInt("id_actividad"), rsr.getInt("n_entradas_dis"),
						rsr.getDouble("precio"), rsr.getDate("fecha_ini"), rsr.getDate("fecha_fin"),
						rsr.getTime("hora"), rsr.getInt("duracion"), rsr.getInt("id_obra"), rsr.getInt("id_compañia"),
						rsr.getInt("id_temporada"), rsr.getBoolean("activo"));
				list.add(trep);
			}

		}

		catch (SQLException e) {
			return null;
		}

		return list;
	}

	public Collection<TRepresentacion> readByCompañia(int id) {

		Collection<TRepresentacion> list = new ArrayList<>();

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = c.prepareStatement("LOCK TABLES REPRESENTACION READ, ACTIVIDAD READ");
			ps.execute();
			ps.close();

			ps = c.prepareStatement(
					"SELECT * FROM ACTIVIDAD JOIN REPRESENTACION USING(id_actividad, activo) WHERE id_compañia = ? AND activo=true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rsr = ps.executeQuery();
			ps.close();
			while (rsr.next()) {
				TRepresentacion trep = new TRepresentacion(rsr.getInt("id_actividad"), rsr.getInt("n_entradas_dis"),
						rsr.getDouble("precio"), rsr.getDate("fecha_ini"), rsr.getDate("fecha_fin"),
						rsr.getTime("hora"), rsr.getInt("duracion"), rsr.getInt("id_obra"), rsr.getInt("id_compañia"),
						rsr.getInt("id_temporada"), rsr.getBoolean("activo"));
				list.add(trep);
			}
		} catch (SQLException e) {
			return null;
		}

		return list;
	}

	public int deleteFisico(int id) {

		Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

		try {
			PreparedStatement ps = c
					.prepareStatement("LOCK TABLES REPRESENTACION WRITE, ACTIVIDAD_CULTURAL WRITE, ACTIVIDAD WRITE");
			ps.execute();
			ps.close();

			ps = c.prepareStatement("DELETE FROM REPRESENTACION WHERE id_actividad=?", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

			ps = c.prepareStatement("DELETE FROM ACTIVIDAD_CULTURAL WHERE id_actividad=?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

			ps = c.prepareStatement("DELETE FROM ACTIVIDAD WHERE id_actividad=?", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			id = -1;
		}

		return id;
	}

}