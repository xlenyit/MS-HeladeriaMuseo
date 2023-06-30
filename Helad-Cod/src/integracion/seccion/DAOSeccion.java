package integracion.seccion;

import java.util.Set;

import negocio.seccion.TSeccion;
public interface DAOSeccion {
	public Integer create(TSeccion Seccion);
	public TSeccion readById(Integer Id);
	public Set<TSeccion> readAll();
	public Integer update(TSeccion Seccion);
	public Integer delete(Integer Id);
	public TSeccion readByNombre(String Nombre);
}