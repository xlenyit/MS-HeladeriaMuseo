package presentaci�n.controlador.comando.imp.ComandoCliente;

import negocio.cliente.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoActualizarCliente implements Comando {
	
	@Override
	public Contexto ejecutar(Object data)  {
		
		TCliente tcliente = (TCliente) data;
		int res= FactoriaAbstractaNegocio.getInstance().createSACliente().update(tcliente);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_CLIENTE_OK, res);
		else
			contexto = new Contexto(Evento.RES_MODIFICAR_CLIENTE_KO, res);

		return contexto;
	}
}
