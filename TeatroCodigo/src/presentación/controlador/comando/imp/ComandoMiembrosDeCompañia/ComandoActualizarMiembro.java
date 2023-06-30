package presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompa�ia.SAMiembrosDeCompa�ia;
import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoActualizarMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		SAMiembrosDeCompa�ia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompa�ia();
		int res = saMComp.update((TMiembrosDeCompa�ia) data);
		Contexto contexto = new Contexto(Evento.RES_MODIFICAR_MIEMBRO_COMPA�IA_KO, null);

		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_MIEMBRO_COMPA�IA_OK, new Integer(res));

		return contexto;
	}

}
