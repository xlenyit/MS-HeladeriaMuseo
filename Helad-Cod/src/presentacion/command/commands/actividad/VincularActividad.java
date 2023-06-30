package presentacion.command.commands.actividad;

import negocio.actividad.SAActividad;
import negocio.actividad.TLineaActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class VincularActividad  implements CommandInterface {
	SAActividad saActividad= FactoriaNegocio.getInstance().generarSAActividad();
	@Override
	public Contexto execute(Object o) {
		TLineaActividad tLineaActividad = (TLineaActividad) o;
		int op = saActividad.anyadirUsuario(tLineaActividad);
		
		Contexto contexto=new Contexto (op,tLineaActividad);
		

		return contexto;
	}
	
}
