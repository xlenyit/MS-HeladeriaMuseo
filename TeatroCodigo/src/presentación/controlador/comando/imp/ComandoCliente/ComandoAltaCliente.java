package presentación.controlador.comando.imp.ComandoCliente;

import negocio.cliente.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAltaCliente implements Comando {

	@Override
	public Contexto ejecutar(Object data) {

		TCliente tcliente = (TCliente) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSACliente().create(tcliente);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_CLIENTE_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_CLIENTE_KO, res);

		return contexto;
	}

}
