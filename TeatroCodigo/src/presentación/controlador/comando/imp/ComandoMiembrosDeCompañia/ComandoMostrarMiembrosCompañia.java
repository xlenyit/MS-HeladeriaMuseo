package presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarMiembrosCompa�ia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TMiembrosDeCompa�ia> res = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompa�ia()
				.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_MIEMBRO_COMPA�IA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_MIEMBRO_COMPA�IA_KO, null);

		return contexto;
	}

}
