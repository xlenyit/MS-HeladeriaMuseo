package presentaci�n.controlador.comando.imp.ComandoFactura;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TFactura;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoListarFacturas implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TFactura> res = FactoriaAbstractaNegocio.getInstance().createSAFactura().readAll();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_FACTURA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_FACTURA_KO, res);

		return contexto;
	}

}
