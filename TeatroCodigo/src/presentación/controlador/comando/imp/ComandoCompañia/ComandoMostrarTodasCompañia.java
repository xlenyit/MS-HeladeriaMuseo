package presentación.controlador.comando.imp.ComandoCompañia;

import java.util.Collection;

import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarTodasCompañia implements Comando {

	@Override
	public Contexto ejecutar(Object data)  {
		SACompañia saCompañia = FactoriaAbstractaNegocio.getInstance().createSACompañia();
		Collection<TCompañia> res = saCompañia.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_COMPAÑIA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_COMPAÑIA_KO, null);

		return contexto;
	}

}
