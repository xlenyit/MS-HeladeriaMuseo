package presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia;

import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBajaMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_ELIMINAR_MIEMBRO_COMPA�IA_KO, null);
		Integer res = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompa�ia().delete((Integer) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_MIEMBRO_COMPA�IA_OK, res);
		return contexto;
	}

}
