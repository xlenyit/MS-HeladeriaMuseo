package presentación.controlador.comando.imp.ComandoTemporada;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.temporada.TTemporada;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarTemporadas implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TTemporada> res = FactoriaAbstractaNegocio.getInstance().createSATemporada().readAll();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_TEMPORADA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_TEMPORADA_KO, res);

		return contexto;
	}

}
