
package presentación.controlador.comando.imp.ComandoTurno;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.turno.SATurno;
import negocio.turno.TTurno;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarTurno implements Comando {

	public Contexto ejecutar(Object data) {
		SATurno saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		TTurno res = saTurno.buscar((int) data);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_TURNO_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_TURNO_KO, null);
		return contexto;

	}
}