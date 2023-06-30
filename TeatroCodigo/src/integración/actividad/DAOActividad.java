package integración.actividad;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import negocio.actividad.TActividad;
import negocio.actividad.TRepresentacion;

public interface DAOActividad {

	public int create(TActividad tactividad);

	public int delete(int id);

	public int deleteFisico(int id);

	public TActividad read(int id);

	public Collection<TActividad> readAll();

	public int update(TActividad tactividad);

	public TActividad readByName(Date fechaInicio, Time hora);

	public Collection<TRepresentacion> readByTemporada(int id);

	public Collection<TRepresentacion> readByObra(int id);

	public Collection<TRepresentacion> readByCompañia(int id);
}