package presentacion.command.commands.obra;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.ingrediente.SAIngrediente;
import negocio.ingrediente.TIngrediente;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarObra implements CommandInterface {
	SAObra sa = FactoriaNegocio.getInstance().generarSAObra();
	TObra tObra;
	int op = -1;
	int id;
	Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		tObra = sa.MostrarObra(id);
		
		contexto=new Contexto (id,tObra);
		
		return contexto;
	}


}
