package presentación.controlador.comando.imp.ComandoMarca;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAltaMarca implements Comando {
	
	public Contexto ejecutar(Object data) {
		SAMarca saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		int result = saMarca.alta((TMarca) data);
		Contexto contexto;
		if (result > 0)
			contexto = new Contexto(Evento.RES_ALTA_MARCA_OK, result);
		else
			contexto = new Contexto(Evento.RES_ALTA_MARCA_KO, null);
		return contexto;
	}
	
}