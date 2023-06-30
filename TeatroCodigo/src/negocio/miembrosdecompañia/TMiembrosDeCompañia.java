package negocio.miembrosdecompañia;

public class TMiembrosDeCompañia {

	private int id;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String Apellidos;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String Nombre;

	private String dni;

	private String apellidos;

	private String nombre;

	private boolean activo;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String Tipo;

	private String tipo;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TMiembrosDeCompañia(int id, String nombre, String apellidos, String tipo, String dni, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tipo = tipo;
		this.dni = dni;
		this.activo = activo;
	}

	public String toString() {
		return "Id: " + id + '\n' + "Nombre: " + nombre + '\n' + "Apellidos: " + apellidos + '\n' + "DNI: " + dni + '\n'
				+ "Tipo: " + tipo + '\n' + "Activo: " + activo + '\n' + '\n';
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDNI() {
		return this.dni;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}
}