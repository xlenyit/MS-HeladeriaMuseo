/**
 * 
 */
package presentaci�n.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.imp.TFacturaConProducto;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoCerrarVentaTienda implements Comando {

	public Contexto ejecutar(Object data) {
		TFacturaConProducto tFacturaConProducto = (TFacturaConProducto) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSAFacturaTienda().cerrarVenta(tFacturaConProducto);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_FACTURA_OK_TIENDA, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_FACTURA_KO_TIENDA, res);

		return contexto;
	}
}