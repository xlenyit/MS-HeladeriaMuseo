package presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompa�ia.SAMiembrosDeCompa�ia;
import negocio.miembrosdecompa�ia.TLineaMiembro;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoAsignarMiembroACompa�ia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_ASIGNAR_MIEMBRO_A_COMPA�IA_KO, null);
		SAMiembrosDeCompa�ia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompa�ia();
		int res = saMComp.addToCompany((TLineaMiembro) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_ASIGNAR_MIEMBRO_A_COMPA�IA_OK, new Integer(res));

		return contexto;
	}

}
