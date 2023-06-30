package presentacion.controlador;

public abstract class Controlador {
	private static Controlador instance;
	
	public static synchronized Controlador getInstance() {
		 
		if(instance == null) instance = new ControladorImp();
		return instance;
	}
	public abstract void update(Integer evento, Object datos);
}