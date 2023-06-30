
package negocio.actividad;

import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Date;


@Entity
//@NamedQuery(name = "Negocio.actividad.Taller.findByutensilios", query = "select obj from Taller obj where :utensilios = obj.utensilios ")
public class Taller extends Actividad implements Serializable {

	private static final long serialVersionUID = 0;

	private String utensilios;

	public Taller() {}

	public Taller(Integer id, String nombre, Integer codigo, boolean activo, Date fecha, String utensilios) {
		super(id, nombre, codigo, activo, fecha);
		this.utensilios = utensilios;
	}
	public Taller(TTaller tActividad) {
		super(tActividad.getId(), tActividad.getNombre(), tActividad.getCodigo(), tActividad.getActivo(), tActividad.getFecha());
		this.utensilios = tActividad.getUtensilios();
	}
	public String getUtensilios() {
		return this.utensilios;
	}

	public void setUtensilios(String utensilios) {
		this.utensilios = utensilios;
	}
	
	public TActividad toTransfer() {
		
		TActividad t = new TTaller(super.getId(), super.getNombre(), super.getCodigo(), super.getActivo(), super.getFecha(), this.utensilios);
		return t;
		
	}
}