package presentación.controlador.comando.imp.ComandoMarca;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarMarca implements Comando {

	public Contexto ejecutar(Object data) {
		SAMarca saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		TMarca result = saMarca.buscar((int) data);
		Contexto contexto;
		if (result != null)
			contexto = new Contexto(Evento.RES_BUSCAR_MARCA_OK, result);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_MARCA_KO, null);
		return contexto;
	}
	
}