package presentacion.command.commands.obra;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.obra.SAObra;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaObra implements CommandInterface {
	SAObra sa= FactoriaNegocio.getInstance().generarSAObra();
	@Override
	public Contexto execute(Object o) {
		Integer i = (Integer) o;
		int op = sa.BajaObra(i);
		
		Contexto contexto=new Contexto (op,i);
		

		return contexto;
	}
}
