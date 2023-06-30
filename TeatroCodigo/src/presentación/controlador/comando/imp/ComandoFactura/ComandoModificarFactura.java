package presentaci�n.controlador.comando.imp.ComandoFactura;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TOAFacturaConActividad;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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
