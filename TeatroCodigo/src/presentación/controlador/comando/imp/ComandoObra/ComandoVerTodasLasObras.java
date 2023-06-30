package presentaci�n.controlador.comando.imp.ComandoObra;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoVerTodasLasObras implements Comando {

	public Contexto ejecutar(Object data) {
		SAObra saObra = FactoriaAbstractaNegocio.getInstance().createSAObra();
		Collection<TObra> res = saObra.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_OBRA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_OBRA_KO, null);

		return contexto;
	}

}
