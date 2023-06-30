package presentaci�n.controlador.comando.imp.ComandoCompa�ia;

import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoAltaCompa�ia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		TCompa�ia tComp = (TCompa�ia) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSACompa�ia().create(tComp);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_COMPA�IA_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_COMPA�IA_KO, res);

		return contexto;
	}
}
