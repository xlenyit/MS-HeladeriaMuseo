package presentaci�n.controlador.comando.imp.ComandoTemporada;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoAltaTemporada implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		SATemporada saTemporada = FactoriaAbstractaNegocio.getInstance().createSATemporada();
		int res = saTemporada.create((TTemporada) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_TEMPORADA_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_TEMPORADA_KO, null);
		return contexto;
	}

}
