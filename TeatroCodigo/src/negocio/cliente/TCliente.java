package negocio.cliente;

public class TCliente {

	private int id;

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

	private boolean socio;

	/** 
	* @return the socio
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean isSocio() {
		// begin-user-code
		return socio;
		// end-user-code
	}

	private String dni;
	private String nombre;
	private String apellidos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean getSocio() {
		return socio;
	}

	public void setSocio(boolean activo) {
		this.socio = activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param dni
	* @param nombre
	* @param apellidos
	* @param socio
	* @param id
	* @param activo
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TCliente(String dni, String nombre, String apellidos, boolean socio, int id, boolean activo) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public TCliente(int id, String dni, String nombre, String apellidos, boolean socio, boolean activo) {
		this.id = id;
		this.activo = activo;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.socio = socio;
	}

	public TCliente() {
	}

	@Override
	public String toString() {
		return "id: " + id + '\n' + "dni: " + dni + '\n' + "nombre: " + nombre + '\n' + "apellidos: " + apellidos + '\n'
				+ "socio: " + socio + '\n' + '\n';
	}
}