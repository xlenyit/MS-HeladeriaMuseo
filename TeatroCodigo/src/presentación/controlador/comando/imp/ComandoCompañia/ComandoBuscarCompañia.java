package presentaci�n.controlador.comando.imp.ComandoCompa�ia;

import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarCompa�ia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		int id = (int) data;
		TCompa�ia res = FactoriaAbstractaNegocio.getInstance().createSACompa�ia().read(id);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_COMPA�IA_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_COMPA�IA_KO, res);

		return contexto;
	}

}
