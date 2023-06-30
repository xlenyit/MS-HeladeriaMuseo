package presentaci�n.controlador.comando.imp.ComandoFactura;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TFactura;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarFacturasPorCliente implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TFactura> res = FactoriaAbstractaNegocio.getInstance().createSAFactura().readByCliente((int) data);
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_BUSCAR_FACTURA_CL_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_FACTURA_CL_KO, res);

		return contexto;
	}

}
