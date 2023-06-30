/**
 * 
 */
package presentaci�n.controlador.comando.imp.ComandoFacturaTienda;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.imp.TFacturaConProducto;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;



public class ComandoMostrarFacturaPorEmpleado implements Comando {

	public Contexto ejecutar(Object data) {
		int idEmpleado = (int) data;
		Collection<TFacturaConProducto> res = FactoriaAbstractaNegocio.getInstance().createSAFacturaTienda().buscarPorEmpleado(idEmpleado);
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_ALTA_FACTURA_OK_TIENDA, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_FACTURA_KO_TIENDA, res);

		return contexto;
	}
}