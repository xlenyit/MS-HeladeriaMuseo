package negocio.marca;

public class TMarca {

	private int id;

	private String nombre;

	private boolean activo;

	public boolean isActivo() {
		return activo;
	}

	public TMarca(int id, String nombre, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public TMarca(String nombre) {
		this.nombre = nombre;
		activo = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String toString() {
		return "Id: " + this.id + '\n' + "Nombre: " + this.nombre + '\n' + (this.activo ? "Activo" : "Inactivo") + '\n';
	}
}