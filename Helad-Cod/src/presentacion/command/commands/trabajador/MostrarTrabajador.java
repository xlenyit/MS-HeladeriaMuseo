package presentacion.command.commands.trabajador;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.trabajador.TTrabajador;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.factoriaGUI.FactoriaGUI;

public class MostrarTrabajador implements CommandInterface {
	FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstance();
	 FactoriaGUI factoriaGUI = FactoriaGUI.getInstance();
	 TTrabajador tTrabajador;
	 int op = -1;
	 int id;
	 

	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		tTrabajador = factoriaNegocio.generarSATrabajadores().mostrarTrabajador(id);

   	 	Contexto contexto = new Contexto (id,tTrabajador);
   	 
   	 	return contexto;
	}
	 
	    
}
