package presentaciķn.controlador.comando.imp.ComandoMiembrosDeCompaņia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompaņia.SAMiembrosDeCompaņia;
import negocio.miembrosdecompaņia.TMiembrosDeCompaņia;
import presentaciķn.controlador.Evento;
import presentaciķn.controlador.comando.Comando;
import presentaciķn.controlador.comando.Contexto;

public class ComandoActualizarMiembro implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		SAMiembrosDeCompaņia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompaņia();
		int res = saMComp.update((TMiembrosDeCompaņia) data);
		Contexto contexto = new Contexto(Evento.RES_MODIFICAR_MIEMBRO_COMPAŅIA_KO, null);

		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_MIEMBRO_COMPAŅIA_OK, new Integer(res));

		return contexto;
	}

}
