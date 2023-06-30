package presentaci�n.controlador.comando.imp.ComandoTurno;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.turno.SATurno;
import negocio.turno.TTurno;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarTurnos implements Comando {

	public Contexto ejecutar(Object data) {
		SATurno saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		Collection<TTurno> res = saTurno.mostrarTurnos();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_TURNOS_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_TURNOS_KO, null);
		return contexto;
	}
}