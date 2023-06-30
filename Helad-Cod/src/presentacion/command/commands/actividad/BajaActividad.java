package presentacion.command.commands.actividad;

import negocio.actividad.SAActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaActividad  implements CommandInterface {
	SAActividad saActividad= FactoriaNegocio.getInstance().generarSAActividad();
	@Override
	public Contexto execute(Object o) {
		Integer i = (Integer) o;
		int op = saActividad.bajaActividad(i);
		
		Contexto contexto=new Contexto (op,i);
		

		return contexto;
	}
}