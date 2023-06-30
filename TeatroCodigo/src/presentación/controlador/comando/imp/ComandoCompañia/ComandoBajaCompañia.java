package presentación.controlador.comando.imp.ComandoCompañia;

import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBajaCompañia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		int id = (int) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSACompañia().delete(id);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_COMPAÑIA_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_COMPAÑIA_KO, res);

		return contexto;
	}

}
