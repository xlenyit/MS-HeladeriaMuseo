package presentacion.command.commands.trabajador;

import java.util.ArrayList;
import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.trabajador.TTrabajador;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.factoriaGUI.FactoriaGUI;

public class MostrarTrabajadorConMasVentas implements CommandInterface {
	FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstance();
	 FactoriaGUI factoriaGUI = FactoriaGUI.getInstance();
	 TTrabajador tTrabajador;
	 int op = -1;
	 int id;

	@Override
	public Contexto execute(Object o) {
		List<String> lista = (ArrayList<String>) o;
		tTrabajador = factoriaNegocio.generarSATrabajadores().trabajadorConMasVentas(lista.get(0),lista.get(1));

   	 	Contexto contexto = new Contexto (id,tTrabajador);
   	 
   	 	return contexto;
	}
	 
	    
}
