package presentación.controlador.comando.imp.ComandoMarca;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarMarcas implements Comando {
	
	public Contexto ejecutar(Object data) {
		SAMarca saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		Collection<TMarca> result = saMarca.mostrarMarcas();
		Contexto contexto;
		if (!result.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_MARCAS_OK, result);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_MARCAS_KO, null);
		return contexto;
	}
	
}