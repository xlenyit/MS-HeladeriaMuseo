package presentacion.command.commands.seccion;

import java.util.Set;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.SASeccion;
import negocio.seccion.TSeccion;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarSeccion implements CommandInterface {
	private static final int event = Evento.LISTAR_SECCION;
	SASeccion saseccion = FactoriaNegocio.getInstance().generarSASeccion();
	TSeccion tSeccion;
	int op = -1;
	Contexto contexto;
	Set<TSeccion> aux= saseccion.listarSecciones();
	@Override
	public Contexto execute(Object o) {
		
		contexto=new Contexto (event,aux);
		
		return contexto;
	}

}