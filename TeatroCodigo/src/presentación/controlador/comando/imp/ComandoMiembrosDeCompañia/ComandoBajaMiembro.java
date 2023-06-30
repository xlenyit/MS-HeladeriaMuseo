package presentación.controlador.comando.imp.ComandoMiembrosDeCompañia;

import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBajaMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_ELIMINAR_MIEMBRO_COMPAÑIA_KO, null);
		Integer res = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompañia().delete((Integer) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_MIEMBRO_COMPAÑIA_OK, res);
		return contexto;
	}

}
