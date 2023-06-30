package presentación.controlador.comando.imp.ComandoCliente;

import negocio.cliente.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarCliente implements Comando {

	@Override
	public Contexto ejecutar(Object data) {

		int id = (int) data;
		TCliente res = FactoriaAbstractaNegocio.getInstance().createSACliente().read(id);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_CLIENTE_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_CLIENTE_KO, res);

		return contexto;
	}

}
