package presentaci�n.controlador.comando.imp.ComandoCompa�ia;

import java.util.Collection;

import negocio.compa�ia.SACompa�ia;
import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarTodasCompa�ia implements Comando {

	@Override
	public Contexto ejecutar(Object data)  {
		SACompa�ia saCompa�ia = FactoriaAbstractaNegocio.getInstance().createSACompa�ia();
		Collection<TCompa�ia> res = saCompa�ia.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_COMPA�IA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_COMPA�IA_KO, null);

		return contexto;
	}

}
