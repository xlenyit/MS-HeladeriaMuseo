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


public class ComandoMostrarFacturaTienda implements Comando {

	public Contexto ejecutar(Object data) {
		Collection<TFacturaConProducto> res = FactoriaAbstractaNegocio.getInstance().createSAFacturaTienda().mostrar();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_FACTURA_OK_TIENDA, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_FACTURA_KO_TIENDA, res);

		return contexto;
	}
}