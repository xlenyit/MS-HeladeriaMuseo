package presentacion.controlador;

import presentacion.command.CommandFactory;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.factoriaGUI.FactoriaGUI;

public class ControladorImp extends Controlador {

    @Override
    public void update(Integer e, Object o) {
    	CommandFactory cf=CommandFactory.getInstance();
        CommandInterface com = cf.getCommand(e); 
        Integer eMostrar=cf.mostrarEvento(e);
        FactoriaGUI factoriaGUI = FactoriaGUI.getInstance();
       
        if (com != null) { //Si esta en el array -> es un comando cualquiera
        	Contexto context = com.execute(o);
        	factoriaGUI.createVista(eMostrar).update(context.getEvento(), context.getDatos());
        }
        else{ //Sino es una vista
        	factoriaGUI.createVista(e);
       }
    }
}