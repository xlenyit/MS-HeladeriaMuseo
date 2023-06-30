package negocio.exposicion;

import java.util.List;


public interface SAExposicion {
	public Integer altaExposicion(TExposicion tExposicion);
	public Integer bajaExposicion(Integer id);
	public Integer modificarExposicion(TExposicion tExposicion);
	public TExposicion mostrarExposicion(Integer id);
	public List<TExposicion> listarExposicion();
	public double calcularCoste(int id);
}