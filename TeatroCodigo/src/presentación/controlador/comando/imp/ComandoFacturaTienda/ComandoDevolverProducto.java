package presentaci�n.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.imp.TLineaFacturaTienda;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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
