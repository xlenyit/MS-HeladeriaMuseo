package presentaci�n.controlador.comando.imp.ComandoProveedor;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TLineaProveedorProducto;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoAsignarProveedorAProducto implements Comando {
	
	public Contexto ejecutar(Object data) {
		SAProveedor saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		TLineaProveedorProducto res = saProveedor.asignarProveedorAProducto((TLineaProveedorProducto) data);
		Contexto contexto;
		if (res.getIdProducto() > 0 && res.getIdProveedor()>0)
			contexto = new Contexto(Evento.RES_ASIGNAR_PROVEEDOR_A_PRODUCTO_OK, res);
		else
			contexto = new Contexto(Evento.RES_ASIGNAR_PROVEEDOR_A_PRODUCTO_KO, null);
		return contexto;
	}
}
