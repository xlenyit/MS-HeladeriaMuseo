/**
 * 
 */
package presentación.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.imp.TFacturaConProducto;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

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