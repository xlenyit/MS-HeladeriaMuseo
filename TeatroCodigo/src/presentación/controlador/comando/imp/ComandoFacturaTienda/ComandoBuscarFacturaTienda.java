/**
 * 
 */
package presentaci�n.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.imp.TFacturaConProducto;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBuscarFacturaTienda implements Comando {

	public Contexto ejecutar(Object data) {
		int id = (int) data;
		TFacturaConProducto res = FactoriaAbstractaNegocio.getInstance().createSAFacturaTienda().buscar(id);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_FACTURA_EMPLEADO_OK_TIENDA, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_FACTURA_EMPLEADO_KO_TIENDA, res);

		return contexto;
	}
}