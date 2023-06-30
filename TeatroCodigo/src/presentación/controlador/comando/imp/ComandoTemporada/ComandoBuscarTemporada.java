package presentaci�n.controlador.comando.imp.ComandoTemporada;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarTemporada implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		SATemporada saTemporada = FactoriaAbstractaNegocio.getInstance().createSATemporada();
		TTemporada res = saTemporada.read((int) data);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_TEMPORADA_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_TEMPORADA_KO, res);
		return contexto;
	}

}
