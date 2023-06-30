package presentación.controlador.comando;

public class Contexto {

	private int evento;
	private Object datos;

	public Contexto(int evento, Object data) {
		this.evento = evento;
		this.datos = data;
	}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}

	public Object getDatos() {
		return datos;
	}

	public void setDatos(Object data) {
		this.datos = data;
	}

}
