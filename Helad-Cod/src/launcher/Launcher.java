package launcher;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class Launcher {

	public static void main(String[] args) {
		Controlador.getInstance().update(Evento.ELECCION_INICIO_PROGRAMA,null);
	}
}