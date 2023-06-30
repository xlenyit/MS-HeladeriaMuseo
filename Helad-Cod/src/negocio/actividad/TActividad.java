package negocio.actividad;

import java.util.Date;

public class TActividad {
	
	private Integer id;
	private boolean activo;
	private Integer codigo;
	private String nombre;
	private Date fecha;

	public TActividad(Integer id, String nombre, Integer codigo, boolean activo, Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.activo = activo;
		this.fecha = fecha;
	}
	public TActividad(Integer id, String nombre, Integer codigo, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.activo = activo;
	}
	

	public Integer getId() {
		return this.id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}