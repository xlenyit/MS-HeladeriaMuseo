package presentación.controlador.comando.imp.ComandoProducto;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarPorProveedor implements Comando{
	public Contexto ejecutar(Object data) {
		SAProducto saProducto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		Collection<TProducto> res = saProducto.mostrarProdPorProveedor((int) data);
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.MOSTRAR_PRODUCTO_PROVEEDOR_OK, res);
		else
			contexto = new Contexto(Evento.MOSTRAR_PRODUCTO_PROVEEDOR_KO, null);
		return contexto;
	}
}
