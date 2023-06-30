package presentacion.command.commands.obra;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class AltaObra implements CommandInterface {
	SAObra sa= FactoriaNegocio.getInstance().generarSAObra();
	@Override
	public Contexto execute(Object o) {
		TObra tObra = (TObra) o;
		int op = sa.AltaObra(tObra);
		
		Contexto contexto=new Contexto (op,tObra);
		

		return contexto;
	}
}
