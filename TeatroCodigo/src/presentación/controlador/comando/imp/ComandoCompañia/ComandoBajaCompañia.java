package presentaci�n.controlador.comando.imp.ComandoCompa�ia;

import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBajaCompa�ia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		int id = (int) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSACompa�ia().delete(id);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_COMPA�IA_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_COMPA�IA_KO, res);

		return contexto;
	}

}
