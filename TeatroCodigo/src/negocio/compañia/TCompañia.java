package negocio.compañia;

public class TCompañia {
	private int id;
	private String nombre;
	private String tipo;
	private boolean activo;

	/** 
	* @return the activo
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean isActivo() {
		// begin-user-code
		return activo;
		// end-user-code
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {
		return activo;
	}

	public TCompañia(int id, String nombre, String tipo, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.activo = activo;
	}

	public TCompañia(String nombre, String tipo, boolean activo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.activo = activo;
	}

	public String toString() {
		return "Id: " + id + '\n' + "Nombre: " + nombre + '\n' + "Tipo: " + tipo + '\n' + "Activo: " + activo + '\n'
				+ '\n';
	}

}