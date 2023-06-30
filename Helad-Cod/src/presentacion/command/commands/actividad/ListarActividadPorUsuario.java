package presentacion.command.commands.actividad;

import java.util.List;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarActividadPorUsuario implements CommandInterface{

	SAActividad sa = FactoriaNegocio.getInstance().generarSAActividad();
	int op = -1;
	Contexto contexto;

	@Override
	public Contexto execute(Object o) {
		Integer id=(Integer) o;
		List<TActividad> aux= sa.listarUsuarios(id);
		contexto=new Contexto (Evento.LISTAR_ACTIVIDAD_POR_USUARIO,aux);
		
		return contexto;
	}


}
