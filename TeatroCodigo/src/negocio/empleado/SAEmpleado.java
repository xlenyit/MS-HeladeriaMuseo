
package negocio.empleado;

import java.util.Collection;

public interface SAEmpleado {

	public int alta(TEmpleado tEmpleado);

	public int eliminar(int id);

	public TEmpleado buscar(int id);

	public int modificar(TEmpleado tEmpleado);

	public Collection<TEmpleado> mostrar();

	public Collection<TEmpleado> mostrarPorTurno(int id_turno);
	
	public void bajaFisica(int id);
}