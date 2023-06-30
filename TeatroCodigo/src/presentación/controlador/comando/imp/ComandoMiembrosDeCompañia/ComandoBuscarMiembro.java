package presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia;

import negocio.factoria.FactoriaNegocio;
import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_BUSCAR_MIEMBRO_COMPA�IA_KO, null);
		TMiembrosDeCompa�ia res = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia().read((Integer) data);
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_MIEMBRO_COMPA�IA_OK, res);
		return contexto;
	}

}
