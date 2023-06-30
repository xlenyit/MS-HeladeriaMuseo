package presentacion.command.commands.actividad;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class AltaActividad implements CommandInterface {
		SAActividad saactividad = FactoriaNegocio.getInstance().generarSAActividad();
	@Override
	public Contexto execute(Object o) {
		TActividad tActividad = (TActividad) o;
		int op = saactividad.altaActividad(tActividad);
		
		Contexto contexto=new Contexto (op,tActividad);
		

		return contexto;
	}

}
