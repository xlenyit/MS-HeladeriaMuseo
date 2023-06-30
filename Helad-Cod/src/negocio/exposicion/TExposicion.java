package negocio.exposicion;


public class TExposicion {
	
	private Integer id;
	private String genero;
	private String nombre;
	private Boolean activo;

	public TExposicion(String nombre ,String genero){
		activo = true;
		id = -1;
		this.nombre = nombre;
		this.genero = genero;
	}
	
	
	public TExposicion(String nombre ,String genero, boolean activo, int id){
		this.activo = activo;
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
	}

	public TExposicion(Integer id,String nombre ,String genero){
		activo = true;
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
	}
	public void setGenero(String genero) {
		this.genero=genero;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setActivo(Boolean activo) {
		this.activo=activo;
	}

	public Integer getId() {
		return id;
	}
	
	public String getGenero() {
		return genero;
	}

	public String getNombre() {
		return nombre;
	}

	public Boolean getActivo() {
		return activo;
	}
}