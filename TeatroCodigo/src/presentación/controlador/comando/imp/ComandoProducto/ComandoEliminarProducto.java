/**
 * 
 */
package presentación.controlador.comando.imp.ComandoProducto;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoEliminarProducto implements Comando {

	public Contexto ejecutar(Object data) {
		SAProducto saProducto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		int res = saProducto.eliminar((int) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_PRODUCTO_KO, null);
		return contexto;
	}
}