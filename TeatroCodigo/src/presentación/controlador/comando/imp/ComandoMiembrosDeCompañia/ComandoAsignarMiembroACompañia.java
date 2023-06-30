package presentación.controlador.comando.imp.ComandoMiembrosDeCompañia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompañia.SAMiembrosDeCompañia;
import negocio.miembrosdecompañia.TLineaMiembro;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAsignarMiembroACompañia implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_ASIGNAR_MIEMBRO_A_COMPAÑIA_KO, null);
		SAMiembrosDeCompañia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompañia();
		int res = saMComp.addToCompany((TLineaMiembro) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_ASIGNAR_MIEMBRO_A_COMPAÑIA_OK, new Integer(res));

		return contexto;
	}

}
