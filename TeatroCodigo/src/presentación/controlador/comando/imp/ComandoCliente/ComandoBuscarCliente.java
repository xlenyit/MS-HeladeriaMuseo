package presentaci�n.controlador.comando.imp.ComandoCliente;

import negocio.cliente.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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
