package presentaci�n.controlador.comando.imp.ComandoProducto;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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
