package presentación.controlador.comando.imp.ComandoCompañia;

import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAltaCompañia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		TCompañia tComp = (TCompañia) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSACompañia().create(tComp);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_COMPAÑIA_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_COMPAÑIA_KO, res);

		return contexto;
	}
}
