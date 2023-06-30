package presentación.controlador.comando.imp.ComandoMiembrosDeCompañia;

import negocio.factoria.FactoriaNegocio;
import negocio.miembrosdecompañia.TMiembrosDeCompañia;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_BUSCAR_MIEMBRO_COMPAÑIA_KO, null);
		TMiembrosDeCompañia res = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia().read((Integer) data);
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_MIEMBRO_COMPAÑIA_OK, res);
		return contexto;
	}

}
