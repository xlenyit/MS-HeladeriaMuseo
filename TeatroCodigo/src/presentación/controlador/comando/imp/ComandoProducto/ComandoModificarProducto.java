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

public class ComandoModificarProducto implements Comando {

	public Contexto ejecutar(Object data) {
		SAProducto saProducto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		int res = saProducto.modificar((TProducto) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_MODIFICAR_PRODUCTO_KO, null);
		return contexto;
	}
}