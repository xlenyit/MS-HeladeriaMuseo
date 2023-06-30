/**
 * 
 */
package presentación.controlador.comando.imp.ComandoTurno;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.turno.SATurno;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;


public class ComandoCalcularNominaTurno implements Comando {

	public Contexto ejecutar(Object data) {
		SATurno saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		int res = saTurno.calcularNomina((int) data);
		Contexto contexto;
		if (res >= 0)
			contexto = new Contexto(Evento.RES_NOMINA_TURNO_OK, res);
		else
			contexto = new Contexto(Evento.RES_NOMINA_TURNO_KO, null);
		return contexto;
	}
}