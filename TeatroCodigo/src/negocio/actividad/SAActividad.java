package negocio.actividad;

import java.util.Collection;

public interface SAActividad {

	public int create(TActividad tActividad);

	public TActividad read(int id);

	public int update(TActividad tActividad);

	public int delete(int id);

	public int deleteFisico(int id);

	public Collection<TActividad> readAll();

	public Collection<TRepresentacion> readByCompañia(int id);

	public Collection<TRepresentacion> readByObra(int id);

	public Collection<TRepresentacion> readByTemporada(int id);
}