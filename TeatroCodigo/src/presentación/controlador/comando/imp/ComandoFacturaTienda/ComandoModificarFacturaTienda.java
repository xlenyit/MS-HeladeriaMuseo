package presentaci�n.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.TFacturaTienda;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoModificarFacturaTienda implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		TFacturaTienda tFacturaTienda = (TFacturaTienda) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSAFacturaTienda().modificar(tFacturaTienda);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_FACTURA_OK_TIENDA, res);
		else
			contexto = new Contexto(Evento.RES_MODIFICAR_FACTURA_KO_TIENDA, res);

		return contexto;
	}

}
