package presentacion.command.commands.exposicion;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import negocio.exposicion.SAExposicion;
import negocio.exposicion.TExposicion;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class MostrarCosteExposicion implements CommandInterface {
	SAExposicion saexposicion = FactoriaNegocio.getInstance().generarSAExposicion();
	TExposicion tExposicion;
	int op = -1;
	int id;
	Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		double coste = saexposicion.calcularCoste(id);
		tExposicion = saexposicion.mostrarExposicion(id);
		List<Object> miSet = new ArrayList<>();
		miSet.add(coste);
		miSet.add(tExposicion);
		contexto=new Contexto (id,miSet);
		
		return contexto;
	}

	
} 

