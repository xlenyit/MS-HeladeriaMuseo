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