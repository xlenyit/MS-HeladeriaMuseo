package presentacion.command.commands.trabajador;

import java.util.Set;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.TSeccion;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.TTrabajador;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarTrabajador implements CommandInterface {
	private static final int event = Evento.LISTAR_TRABAJADOR;
	SATrabajador satrabajador = FactoriaNegocio.getInstance().generarSATrabajadores();
	TSeccion tSeccion;
	Contexto contexto;
	Set<TTrabajador> aux = satrabajador.listarTrabajador();

	@Override
	public Contexto execute(Object o) {
		
		if((Integer)o!=0) aux=satrabajador.listarTrabajadorPorSeccion((Integer)o);

		contexto = new Contexto (event,aux);// recoger en el sa la excepcion de si aux esta empty
   	 
   	 	return contexto;
		
	}
	 
	    
}
