
package presentación.controlador.comando.imp.ComandoEmpleado;

import negocio.empleado.SAEmpleado;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoEliminarEmpleado implements Comando {

	public Contexto ejecutar(Object data) {
		SAEmpleado saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		int res = saEmpleado.eliminar((int) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_EMPLEADO_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_EMPLEADO_KO, null);
		return contexto;
	}
}