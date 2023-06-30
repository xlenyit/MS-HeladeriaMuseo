package presentaci�n.controlador.comando.imp.ComandoTemporada;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.temporada.TTemporada;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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
