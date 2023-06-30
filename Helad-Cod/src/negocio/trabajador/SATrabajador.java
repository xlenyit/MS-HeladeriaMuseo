package negocio.trabajador;

import java.util.Set;
public interface SATrabajador {
	public Integer altaTrabajador(TTrabajador Trabajador);
	public Integer bajaTrabajador(Integer Id);
	public TTrabajador mostrarTrabajador(Integer Id);
	public Set<TTrabajador> listarTrabajador();
	public Set<TTrabajador> listarTrabajadorPorSeccion(Integer id);
	public Integer actualizarTrabajador(TTrabajador Trabajador);
	public TTrabajador trabajadorConMasVentas(String ini, String fin);
}