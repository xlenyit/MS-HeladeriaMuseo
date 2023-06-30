package presentacion.command.commands.exposicion;

import negocio.exposicion.SAExposicion;
import negocio.exposicion.TExposicion;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class MostrarExposicion implements CommandInterface {
	SAExposicion saexposicion = FactoriaNegocio.getInstance().generarSAExposicion();
	TExposicion tExposicion;
	int op = -1;
	int id;
	Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		tExposicion = saexposicion.mostrarExposicion(id);
		
		contexto=new Contexto (id,tExposicion);
		
		return contexto;
	}

	
} 

