package presentación.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.imp.TLineaFacturaTienda;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoDevolverProducto implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		TLineaFacturaTienda tLineaFactura = (TLineaFacturaTienda) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSAFacturaTienda().devolverProducto(tLineaFactura);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_DEVOLVER_PRODUCTO_FACTURA_OK_TIENDA, res);
		else
			contexto = new Contexto(Evento.RES_DEVOLVER_PRODUCTO_FACTURA_KO_TIENDA, res);

		return contexto;
	}

}
