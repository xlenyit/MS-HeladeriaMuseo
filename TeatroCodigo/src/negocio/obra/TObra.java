package negocio.obra;

public class TObra {
	private int id;
	private boolean activo;

	private int anio;
	private String sinopsis;
	private String genero;
	private String autor;
	private String titulo;

	public TObra(int id, String genero, String sinopsis, String titulo, String autor, int anio, boolean activo) {
		this.id = id;
		this.activo = activo;
		this.anio = anio;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.titulo = titulo;
		this.autor = autor;
		this.activo = activo;
	}

	public TObra() {

	}

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

	public int getAnio() {
		return anio;
	}

	public void setAnio(int num) {
		anio = num;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param calificacion
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setSinopsis(double calificacion) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAutor() {
		return autor;
	}

	void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setTitulo() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String toString() {
		return "Id: " + this.id + '\n' + "Titulo: " + this.titulo + '\n' + "Género: " + this.genero + '\n' + "Autor: "
				+ this.autor + '\n' + "Sinopsis: " + this.sinopsis + '\n' + "Año: " + this.anio + '\n' + '\n';
	}

}