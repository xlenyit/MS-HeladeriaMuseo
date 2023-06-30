package presentacion.command.commands.obra;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarObraPorExposicion implements CommandInterface{
		SAObra sa = FactoriaNegocio.getInstance().generarSAObra();
		TObra tObra;
		int op = -1;
		Contexto contexto;

	@Override
	public Contexto execute(Object o) {
		Integer id=(Integer) o;
		List<TObra> aux= sa.ListarObraPorExposicion(id);
		contexto=new Contexto (Evento.LISTAR_OBRA_POR_EXPOSICION,aux);
		
		return contexto;
	}


}
