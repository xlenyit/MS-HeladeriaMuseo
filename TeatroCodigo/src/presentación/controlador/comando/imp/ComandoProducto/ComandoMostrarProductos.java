package presentación.controlador.comando.imp.ComandoProducto;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarProductos implements Comando {

	public Contexto ejecutar(Object data) {
		SAProducto saProducto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		Collection<TProducto> res = saProducto.mostrar();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_PRODUCTO_KO, null);
		return contexto;
	}
}