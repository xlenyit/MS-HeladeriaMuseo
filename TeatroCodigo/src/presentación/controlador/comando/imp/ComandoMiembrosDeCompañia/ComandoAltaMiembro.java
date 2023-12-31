package presentaciķn.controlador.comando.imp.ComandoMiembrosDeCompaņia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompaņia.SAMiembrosDeCompaņia;
import negocio.miembrosdecompaņia.TMiembrosDeCompaņia;
import presentaciķn.controlador.Evento;
import presentaciķn.controlador.comando.Comando;
import presentaciķn.controlador.comando.Contexto;

public class ComandoAltaMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_ALTA_MIEMBRO_COMPAŅIA_KO, null);
		SAMiembrosDeCompaņia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompaņia();
		int res = saMComp.create((TMiembrosDeCompaņia) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_MIEMBRO_COMPAŅIA_OK, new Integer(res));

		return contexto;
	}

}
