package negocio.obra;

public class TObra {
	private Integer id;
	private Boolean activo;
	private String nombre;
	private String autor;
	private Double coste;
	private Integer idExposicion;
	protected Double precio;

	public TObra(Integer id, Boolean activo, String nombre, String autor, Double coste, Integer idExposicion){
		this.id = id;
		this.activo = activo;
		this.nombre = nombre;
		this.autor = autor;
		this.coste = coste;
		this.idExposicion = idExposicion;
//		this.precio = precio;
	}
	public Integer getId() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo= activo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor=autor;
	}

	public Double getCoste() {
		return coste;
	}
	public void setCoste(Double coste) {
		this.coste= coste;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdExposicion() {
		return idExposicion;
	}

	public void setIdExposicion(Integer idExposicion) {
		this.idExposicion = idExposicion;
	}
	
	public void setPrecio(Double precio){
		this.precio = precio;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
}
