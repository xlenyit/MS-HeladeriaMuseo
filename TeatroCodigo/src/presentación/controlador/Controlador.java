package presentación.controlador;

import presentación.controlador.imp.ControladorImp;

public abstract class Controlador {

	private static Controlador instance;

	public static Controlador getInstance() {
		if (instance == null)
			instance = new ControladorImp();
		return instance;
	}

	public abstract void accion(int evento, Object datos) throws Exception;
}