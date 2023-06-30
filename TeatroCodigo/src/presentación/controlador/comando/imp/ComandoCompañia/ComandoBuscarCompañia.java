package presentación.controlador.comando.imp.ComandoCompañia;

import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarCompañia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		int id = (int) data;
		TCompañia res = FactoriaAbstractaNegocio.getInstance().createSACompañia().read(id);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_COMPAÑIA_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_COMPAÑIA_KO, res);

		return contexto;
	}

}
