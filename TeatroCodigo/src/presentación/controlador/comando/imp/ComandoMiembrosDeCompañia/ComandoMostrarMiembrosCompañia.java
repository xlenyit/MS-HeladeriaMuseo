package presentación.controlador.comando.imp.ComandoMiembrosDeCompañia;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompañia.TMiembrosDeCompañia;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarMiembrosCompañia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TMiembrosDeCompañia> res = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompañia()
				.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_MIEMBRO_COMPAÑIA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_MIEMBRO_COMPAÑIA_KO, null);

		return contexto;
	}

}
