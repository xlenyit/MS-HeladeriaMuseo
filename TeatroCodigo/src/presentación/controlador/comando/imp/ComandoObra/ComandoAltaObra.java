package presentación.controlador.comando.imp.ComandoObra;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAltaObra implements Comando {

	public Contexto ejecutar(Object data) {
		Contexto contexto;
		SAObra saObra = FactoriaAbstractaNegocio.getInstance().createSAObra();
		int res = saObra.create((TObra) data);

		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_OBRA_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_OBRA_KO, null);

		return contexto;
	}

}
