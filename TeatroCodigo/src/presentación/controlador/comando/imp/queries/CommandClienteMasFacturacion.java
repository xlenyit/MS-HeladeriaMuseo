package presentaci�n.controlador.comando.imp.queries;

import java.util.Collection;

import negocio.cliente.TCliente;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class CommandClienteMasFacturacion implements Comando{

	@Override
	public Contexto ejecutar(Object data)  {
		 Collection<TCliente> response=FactoriaAbstractaNegocio.getInstance().createSACliente().clienteConMasFacturacion();
		if(!response.isEmpty())return new Contexto(Evento.RES_CLIENTE_MAS_FACTURACION_OK,response);
		else return new Contexto(Evento.RES_CLIENTE_MAS_FACTURACION_KO,response);
	}

}
 