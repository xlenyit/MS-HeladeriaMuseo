/**
 * 
 */
package presentación.controlador.comando.imp.ComandoProducto;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAltaProducto implements Comando {
	public Contexto ejecutar(Object data) {
		SAProducto saProdcuto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		int res = saProdcuto.alta((TProducto) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_PRODUCTO_KO, null);
		return contexto;
	}
}