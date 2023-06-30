package presentación.controlador.comando.imp.ComandoMiembrosDeCompañia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompañia.SAMiembrosDeCompañia;
import negocio.miembrosdecompañia.TMiembrosDeCompañia;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAltaMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_ALTA_MIEMBRO_COMPAÑIA_KO, null);
		SAMiembrosDeCompañia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompañia();
		int res = saMComp.create((TMiembrosDeCompañia) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_MIEMBRO_COMPAÑIA_OK, new Integer(res));

		return contexto;
	}

}
