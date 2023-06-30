package presentación.controlador.comando.imp.ComandoMiembrosDeCompañia;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.miembrosdecompañia.SAMiembrosDeCompañia;
import negocio.miembrosdecompañia.TLineaMiembro;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoModificarMeses implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Contexto contexto = new Contexto(Evento.RES_MODIFICAR_NUMERO_MESES_KO, null);
		SAMiembrosDeCompañia saMComp = FactoriaAbstractaNegocio.getInstance().createSAMiembrosDeCompañia();
		int res = saMComp.updateMeses((TLineaMiembro) data);
		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_NUMERO_MESES_OK, new Integer(res));

		return contexto;
	}

}
