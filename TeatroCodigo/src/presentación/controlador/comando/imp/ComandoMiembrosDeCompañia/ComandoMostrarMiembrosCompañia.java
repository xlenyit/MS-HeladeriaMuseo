package presentaciķn.controlador.comando.imp.ComandoMiembrosDeCompaņia;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompaņia.TMiembrosDeCompaņia;
import presentaciķn.controlador.Evento;
import presentaciķn.controlador.comando.Comando;
import presentaciķn.controlador.comando.Contexto;

public class ComandoMostrarMiembrosCompaņia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TMiembrosDeCompaņia> res = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompaņia()
				.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_MIEMBRO_COMPAŅIA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_MIEMBRO_COMPAŅIA_KO, null);

		return contexto;
	}

}
