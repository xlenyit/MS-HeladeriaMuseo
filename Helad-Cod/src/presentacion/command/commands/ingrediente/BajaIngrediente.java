package presentacion.command.commands.ingrediente;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.ingrediente.SAIngrediente;
import negocio.ingrediente.TIngrediente;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaIngrediente implements CommandInterface {
	SAIngrediente saingrediente = FactoriaNegocio.getInstance().generarSAIngrediente();
    TIngrediente tIngrediente;
    int op = -1;
    Contexto contexto;
    int id;
	@Override
	public Contexto execute(Object o) {
		id= (int) o;
		op = saingrediente.bajaIngrediente(id);
		
		contexto = new Contexto(op, id);
		
		return contexto;
	}

}
