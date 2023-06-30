package presentaci�n.controlador.comando.imp.ComandoObra;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarObra implements Comando {

	public Contexto ejecutar(Object data) {
		Contexto contexto;
		SAObra saObra = FactoriaAbstractaNegocio.getInstance().createSAObra();
		TObra res = saObra.read((Integer) data);

		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_OBRA_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_OBRA_KO, null);

		return contexto;
	}

}
