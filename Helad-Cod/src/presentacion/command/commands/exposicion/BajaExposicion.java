package presentacion.command.commands.exposicion;

import negocio.exposicion.SAExposicion;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.SASeccion;
import negocio.seccion.TSeccion;
import negocio.usuario.SAUsuario;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class BajaExposicion implements CommandInterface {
	SAExposicion saexposicion= FactoriaNegocio.getInstance().generarSAExposicion();
	@Override
	public Contexto execute(Object o) {
		Integer i = (Integer) o;
		int op = saexposicion.bajaExposicion(i);
		
		Contexto contexto=new Contexto (op,i);
		

		return contexto;
	}
}