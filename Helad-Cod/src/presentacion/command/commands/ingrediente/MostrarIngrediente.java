package presentacion.command.commands.ingrediente;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.ingrediente.SAIngrediente;
import negocio.ingrediente.TIngrediente;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarIngrediente implements CommandInterface {
	SAIngrediente saingrediente = FactoriaNegocio.getInstance().generarSAIngrediente();
    TIngrediente tIngrediente;
    int op = -1;
    Contexto contexto;
    int id;
	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		tIngrediente = saingrediente.mostrarIngrediente(id);
		contexto = new Contexto(id, tIngrediente);
		
		return contexto;
	}

}
