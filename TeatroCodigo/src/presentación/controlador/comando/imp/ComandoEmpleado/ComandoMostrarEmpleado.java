
package presentaci�n.controlador.comando.imp.ComandoEmpleado;

import java.util.Collection;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarEmpleado implements Comando {

	public Contexto ejecutar(Object data) {
		SAEmpleado saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		Collection<TEmpleado> res = saEmpleado.mostrar();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_EMPLEADO_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_EMPLEADO_KO, null);
		return contexto;
	}
}