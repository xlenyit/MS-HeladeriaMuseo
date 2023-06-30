
package presentaci�n.controlador.comando.imp.ComandoEmpleado;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarEmpleado implements Comando {

	public Contexto ejecutar(Object data) {
		SAEmpleado saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		TEmpleado res = saEmpleado.buscar((int) data);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_EMPLEADO_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_EMPLEADO_KO, null);
		return contexto;

	}
}