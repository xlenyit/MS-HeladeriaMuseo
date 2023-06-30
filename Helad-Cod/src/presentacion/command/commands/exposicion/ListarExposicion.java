package presentacion.command.commands.exposicion;

import java.util.List;

import negocio.exposicion.SAExposicion;
import negocio.exposicion.TExposicion;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.SASeccion;
import negocio.seccion.TSeccion;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;


public class ListarExposicion implements CommandInterface {
	
	SAExposicion saexposicion = FactoriaNegocio.getInstance().generarSAExposicion();
	TExposicion tExposicion;
	int op = -1;
	Contexto contexto;
	List<TExposicion> aux= saexposicion.listarExposicion();
	
	@Override
	public Contexto execute(Object o) {
		contexto=new Contexto (Evento.LISTAR_EXPOSICION,aux);
		
		return contexto;
	}

}