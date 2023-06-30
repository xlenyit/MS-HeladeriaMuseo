package presentaci�n.controlador.comando.imp.queries;

import java.util.Collection;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.obra.TObra;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class CommandObraRepresentacionMasVista implements Comando{

	@Override
	public Contexto ejecutar(Object data) {
		Collection<TObra> response=FactoriaAbstractaNegocio.getInstance().createSAObra().obraConRepresentacionMasVista();
		
		if(!response.isEmpty())return new Contexto(Evento.RES_OBRA_CON_REPRESENTACION_MAS_VISTA_OK,response);
		else return new Contexto(Evento.RES_OBRA_CON_REPRESENTACION_MAS_VISTA_KO,response);
	}

}
