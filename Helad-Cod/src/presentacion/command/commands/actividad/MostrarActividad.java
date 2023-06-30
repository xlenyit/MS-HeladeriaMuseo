package presentacion.command.commands.actividad;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarActividad  implements CommandInterface {
	SAActividad saActividad= FactoriaNegocio.getInstance().generarSAActividad();
	@Override
	public Contexto execute(Object o) {
		Integer i = (Integer) o;
		TActividad tActividad= saActividad.mostrarActividad(i);
		
		Contexto contexto=new Contexto (i,tActividad);
		

		return contexto;
	}
}

