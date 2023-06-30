package presentacion.command.commands.ingrediente;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.ingrediente.SAIngrediente;
import negocio.ingrediente.TIngrediente;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarIngrediente implements CommandInterface {
	private static final int event = Evento.LISTAR_INGREDIENTE;
	SAIngrediente saingrediente = FactoriaNegocio.getInstance().generarSAIngrediente();
    TIngrediente tIngrediente;
    int op = -1;
    Contexto contexto;
    int id;
    List<TIngrediente> aux=saingrediente.listarIngredientes();
	@Override
	public Contexto execute(Object o) {
		contexto = new Contexto (event,aux);
		
		return contexto;
	}

}
