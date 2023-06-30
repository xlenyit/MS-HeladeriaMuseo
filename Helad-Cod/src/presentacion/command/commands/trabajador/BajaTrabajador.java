package presentacion.command.commands.trabajador;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.TTrabajador;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaTrabajador implements CommandInterface {
	SATrabajador satrabajador = FactoriaNegocio.getInstance().generarSATrabajadores();
	Contexto contexto;
	TTrabajador tTrabajador;
	int op = -1;
	int id;

	@Override
	public Contexto execute(Object o) {
		id= (int) o;
		op = satrabajador.bajaTrabajador(id);
   	 	contexto = new Contexto (op,id);
   	 	
   	 	return contexto;

	}
	 
	    
}
