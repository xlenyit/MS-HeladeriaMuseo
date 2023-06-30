package negocio.actividad;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.NamedQuery;


@Entity
@NamedQuery(name = "negocio.actividad.Charla.findBynivel", query = "select obj from Charla obj where :nivel = obj.nivel ")
public class Charla extends Actividad implements Serializable {
	
	private static final long serialVersionUID = 0;

	private Integer nivel;

	public Charla() {}
	public Charla(TCharla tActividad) {
		super(tActividad.getId(), tActividad.getNombre(), tActividad.getCodigo(), tActividad.getActivo(), tActividad.getFecha());
		this.nivel = tActividad.getNivel();
	}
	public Charla(Integer id, String nombre, Integer codigo, boolean activo, Date fecha, Integer nivel) {
		super(id, nombre, codigo, activo, fecha);
		this.nivel = nivel;
	}
	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	public TActividad toTransfer() {
		
		TActividad t = new TCharla(super.getId(), super.getNombre(), super.getCodigo(), super.getActivo(), super.getFecha(), this.nivel);
		return t;
		
	}
}