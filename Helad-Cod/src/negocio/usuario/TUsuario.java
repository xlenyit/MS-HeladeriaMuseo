/**
 * 
 */
package negocio.usuario;

public class TUsuario {

	private Integer id;
	private Boolean activo;
	private String nombre;
	private String dni;
	private Integer idGuia;
	
	public TUsuario(){
	}
	public TUsuario(String nombre, String dni, Integer idGuia){
		activo=true;
		id=-1;
		this.nombre=nombre;
		this.dni=dni;
		this.idGuia=idGuia;
	}
	public TUsuario(Integer id,String nombre ,String dni,Integer idGuia){
		activo=true;
		this.id=id;
		this.nombre=nombre;
		this.dni=dni;
		this.idGuia=idGuia;
	}
	public Integer getId() {
		return id;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public String getNombre() {
		return nombre;	
	}

	public String getDni() {
		return dni;
	}
	
	public void setActivo(Boolean activo) {
		this.activo=activo;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setDni(String dni) {
		this.dni=dni;
	}
	
	public void setIdGuia(Integer idGuia) {
		this.idGuia=idGuia;
	}

	public Integer getIdGuia() {
		return idGuia;
	}
	public void setId(Integer id) {
		this.id=id;
	}
}