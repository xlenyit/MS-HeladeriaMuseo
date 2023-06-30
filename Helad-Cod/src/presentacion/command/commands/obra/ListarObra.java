package presentacion.command.commands.obra;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarObra implements CommandInterface {
	SAObra sa = FactoriaNegocio.getInstance().generarSAObra();
	TObra tObra;
	int op = -1;
	Contexto contexto;
	List<TObra> aux= sa.ListarObra();
	
	@Override
	public Contexto execute(Object o) {
		contexto=new Contexto (Evento.LISTAR_OBRA,aux);
		
		return contexto;
	}
}
