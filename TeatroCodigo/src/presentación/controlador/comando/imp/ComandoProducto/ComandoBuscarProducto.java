/**
 * 
 */
package presentaci�n.controlador.comando.imp.ComandoProducto;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarProducto implements Comando {

	public Contexto ejecutar(Object data) {
		SAProducto saProducto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		TProducto res = saProducto.buscar((int) data);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_PRODUCTO_KO, null);
		return contexto;

	}
}