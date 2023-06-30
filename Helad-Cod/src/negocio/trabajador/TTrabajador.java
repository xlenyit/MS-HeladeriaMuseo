package negocio.trabajador;
public class TTrabajador {
	private String DNI;
	private Integer Telefono;
	private String Nombre;
	private Boolean active;
	private Integer Id;
	private Integer IdSeccion;
	
	public TTrabajador(Integer id){
		this.Id = id;
	}
	public TTrabajador( Integer id,String dNI, Integer telefono, String nombre, Boolean active, Integer idSeccion) {
		super();
		DNI = dNI;
		Telefono = telefono;
		Nombre = nombre;
		this.active = active;
		Id = id;
		IdSeccion = idSeccion;
	}
	public String getDNI() {
		return this.DNI;
	}
	public void setDNI(String DNI) {
		this.DNI=DNI;
	}
	public Integer getTelefono() {
		return this.Telefono;
	}
	public void setTelefono(Integer Telefono) {
		this.Telefono=Telefono;
	}
	public String getNombre() {
		return this.Nombre;
	}
	public void setNombre(String Nombre) {
		this.Nombre=Nombre;
	}
	public Boolean getActive() {
		return this.active;
	}
	public void setActive(Boolean Active) {
		this.active=Active;
	}
	public Integer getId() {
		return this.Id;
	}
	public Integer getIdSeccion() {
		return this.IdSeccion;
	}
	public void setIdSeccion(Integer IdSeccion) {
		this.IdSeccion=IdSeccion;
	}
}