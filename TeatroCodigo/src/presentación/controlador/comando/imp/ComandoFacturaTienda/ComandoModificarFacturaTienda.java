package presentación.controlador.comando.imp.ComandoFacturaTienda;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.facturaTienda.TFacturaTienda;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

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
