package negocio.actividad;

import java.util.Date;

public class TCharla extends TActividad {
	
	private Integer nivel;
	
	public TCharla(String nombre, Integer codigo, Date fecha, Integer nivel){
		super(-1,nombre,codigo,true,fecha);
		this.nivel = nivel;
	}

	public TCharla(Integer id, String nombre, Integer codigo, boolean activo, Date fecha, Integer nivel) {
		super(id, nombre, codigo, activo, fecha);
		this.nivel = nivel;
	}
	public TCharla(Integer id, String nombre, Integer codigo, boolean activo, Integer nivel) {
		super(id, nombre, codigo, activo);
		this.nivel = nivel;
	}
	
	
	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
}
