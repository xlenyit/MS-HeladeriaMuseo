/**
 * 
 */
package presentaci�n.controlador.comando.imp.ComandoTurno;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.turno.SATurno;
import negocio.turno.TTurno;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoModificarTurno implements Comando {

	public Contexto ejecutar(Object data) {
		SATurno saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		int res = saTurno.modificar((TTurno) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_TURNO_OK, res);
		else
			contexto = new Contexto(Evento.RES_MODIFICAR_TURNO_KO, null);
		return contexto;
	}
}