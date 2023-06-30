package presentacion.command.commands.ingrediente;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.ingrediente.SAIngrediente;
import negocio.ingrediente.TIngrediente;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class AltaIngrediente implements CommandInterface {
	SAIngrediente saingrediente = FactoriaNegocio.getInstance().generarSAIngrediente();
    TIngrediente tIngrediente;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		tIngrediente = (TIngrediente) o;
		op = saingrediente.altaIngrediente(tIngrediente, tIngrediente.getTipo());
		
		contexto = new Contexto(op, tIngrediente);
		
		return contexto;
	}

}
