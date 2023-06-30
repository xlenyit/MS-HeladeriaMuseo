
package presentaci�n.controlador.comando.imp.ComandoProveedor;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarProveedPorProducto implements Comando {

	public Contexto ejecutar(Object data) {
		SAProveedor saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		Collection<TProveedor> res = saProveedor.mostrarProvPorProd((int) data);
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_PROVEEDOR_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_PROVEEDOR_PRODUCTO_KO, null);
		return contexto;
	}
}