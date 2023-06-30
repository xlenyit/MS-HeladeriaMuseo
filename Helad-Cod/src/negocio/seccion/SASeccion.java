package negocio.seccion;

import java.util.Set;
public interface SASeccion {
	public Integer altaSeccion(TSeccion tSeccion);
	public TSeccion mostrarSeccion(Integer Id);
	public Integer bajaSeccion(Integer Id);
	public Set<TSeccion> listarSecciones();
	public Integer actualizarSeccion(TSeccion tSeccion);
}