package presentación.controlador.comando.imp.ComandoFactura;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TOAFacturaConActividad;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoModificarFactura implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		TOAFacturaConActividad tFacturaConActividad = (TOAFacturaConActividad) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSAFactura().update(tFacturaConActividad);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_FACTURA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MODIFICAR_FACTURA_KO, res);
		return contexto;
	}

}
