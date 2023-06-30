
package presentaci�n.controlador.comando.imp.ComandoEmpleado;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoAltaEmpleado implements Comando {

	public Contexto ejecutar(Object data) {
		SAEmpleado saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		int res = saEmpleado.alta((TEmpleado) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_EMPLEADO_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_EMPLEADO_KO, null);
		return contexto;
	}
}