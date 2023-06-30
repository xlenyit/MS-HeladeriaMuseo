package negocio.seccion;
public class TSeccion {
	private String nombre;
	private Double sueldo;
	private Integer id;
	private Boolean activo;
	
	
	public TSeccion(Integer id, String nombre, Double sueldo, Boolean activo) {
		super();
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.id = id;
		this.activo = activo;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public Double getSueldo() {
		return this.sueldo;
	}
	public void setSueldo(Double sueldo) {
		this.sueldo=sueldo;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Boolean getActivo() {
		return this.activo;
	}
	public void setActivo(Boolean activo) {
		this.activo=activo;
	}
}