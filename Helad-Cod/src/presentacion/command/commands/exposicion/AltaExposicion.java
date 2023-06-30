package presentacion.command.commands.exposicion;

import negocio.exposicion.SAExposicion;
import negocio.exposicion.TExposicion;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.SASeccion;
import negocio.seccion.TSeccion;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class AltaExposicion implements CommandInterface {
		SAExposicion saexposicion= FactoriaNegocio.getInstance().generarSAExposicion();
	@Override
	public Contexto execute(Object o) {
		TExposicion tExposicion = (TExposicion) o;
		int op = saexposicion.altaExposicion(tExposicion);
		
		Contexto contexto=new Contexto (op,tExposicion);
		

		return contexto;
	}
}