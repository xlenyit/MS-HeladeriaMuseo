package integracion.trabajador;

import java.util.Set;

import negocio.trabajador.TTrabajador;
public interface DAOTrabajador {
	public Integer create(TTrabajador Trabajador);
	public TTrabajador readById(Integer Id);
	public Set<TTrabajador> readAll();
	public Integer update(TTrabajador Trabajador);
	public Integer delete(Integer Id);
	public TTrabajador readByDNI(String DNI);
	public TTrabajador readByName(String name);
	public Set<TTrabajador> readBySeccion(Integer IdSeccion);
}