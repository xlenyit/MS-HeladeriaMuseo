
package presentación.controlador.comando.imp.ComandoProveedor;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TLineaProveedorProducto;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;


public class ComandoDesasignarProveedorAProducto implements Comando {
	public Contexto ejecutar(Object data) {
		SAProveedor saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		TLineaProveedorProducto res = saProveedor.desasignarProveedorDeProducto((TLineaProveedorProducto) data);
		Contexto contexto;
		if (res.getIdProducto() > 0 && res.getIdProveedor()>0)
			contexto = new Contexto(Evento.RES_DESASIGNAR_PROVEEDOR_A_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_DESASIGNAR_PROVEEDOR_A_PRODUCTO_KO, null);
		return contexto;
	}
}