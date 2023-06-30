
package presentación.controlador.comando.imp.ComandoEmpleado;

import java.util.Collection;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarPorTurno implements Comando {
	
	public Contexto ejecutar(Object data) {
		SAEmpleado saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		Collection<TEmpleado> res = saEmpleado.mostrarPorTurno((int) data);
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_POR_TURNO_EMPLEADO_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_POR_TURNO_EMPLEADO_KO, null);
		return contexto;
	}
}