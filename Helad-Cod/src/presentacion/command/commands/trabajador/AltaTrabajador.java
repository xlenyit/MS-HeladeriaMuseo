package presentacion.command.commands.trabajador;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.TTrabajador;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class AltaTrabajador implements CommandInterface {
	SATrabajador satrabajador = FactoriaNegocio.getInstance().generarSATrabajadores();
    TTrabajador tTrabajador;
    int op = -1;
    Contexto contexto;

    @Override
    public Contexto execute(Object o) {
    	tTrabajador = (TTrabajador) o; //
		//op = factoriaNegocio.generarSATrabajadores().altaTrabajador(tTrabajador);
    	op=satrabajador.altaTrabajador(tTrabajador);
    	
    	
    	 contexto = new Contexto (op,tTrabajador);
    	 
    	 return contexto;
    	
		//factoriaGUI.createVista(Evento.MOSTRAR_ALTA_TRABAJADOR).update(op,tTrabajador);
    }

	
}
	
