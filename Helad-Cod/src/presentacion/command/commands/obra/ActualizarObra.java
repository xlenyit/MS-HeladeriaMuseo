package presentacion.command.commands.obra;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class ActualizarObra implements CommandInterface {
	SAObra saobra = FactoriaNegocio.getInstance().generarSAObra();
	TObra tObra;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		
		tObra = (TObra) o;
		op = saobra.ModificarObra(tObra);
		
		contexto = new Contexto(op, tObra);
		
		return contexto;
	}

}
