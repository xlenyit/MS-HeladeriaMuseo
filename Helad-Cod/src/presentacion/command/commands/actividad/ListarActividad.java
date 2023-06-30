package presentacion.command.commands.actividad;

import java.util.ArrayList;
import java.util.List;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarActividad  implements CommandInterface {
	SAActividad saActividad= FactoriaNegocio.getInstance().generarSAActividad();
	List<TActividad> lista = new ArrayList<TActividad>();
	@Override
	public Contexto execute(Object o) {
		
		Integer i = (Integer) o;
		
//		if (i!=0) lista = saActividad.listarUsuarios(i);
		lista = saActividad.listarActividades();
		
		Contexto contexto = new Contexto (Evento.LISTAR_ACTIVIDAD,lista);
		

		return contexto;
	}
}
