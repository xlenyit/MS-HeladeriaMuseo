package presentación.controlador.comando.imp.ComandoCliente;

import java.util.Collection;

import negocio.cliente.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarClientes implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TCliente> res = FactoriaAbstractaNegocio.getInstance().createSACliente().readAll();
		Contexto contexto;
		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_CLIENTE_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_CLIENTE_KO, null);

		return contexto;
	}
}
