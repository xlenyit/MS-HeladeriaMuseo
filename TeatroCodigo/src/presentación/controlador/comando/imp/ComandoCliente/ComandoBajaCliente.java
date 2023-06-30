package presentaci�n.controlador.comando.imp.ComandoCliente;

import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoBajaCliente implements Comando {
	@Override
	public Contexto ejecutar(Object data) {

		int id = (int) data;
		int res = FactoriaAbstractaNegocio.getInstance().createSACliente().delete(id);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_CLIENTE_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_CLIENTE_KO, res);

		return contexto;
	}
}
