
package negocio.empleado.entidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import negocio.empleado.TEmpleadoTiempoCompleto;

@Entity
@DiscriminatorValue("C")
@NamedQueries({
		@NamedQuery(name = "EmpleadoTiempoCompleto.findById", query = "select obj from EmpleadoTiempoCompleto obj where :id = obj.id "),
		@NamedQuery(name = "EmpleadoTiempoCompleto.findByVersion", query = "select obj from EmpleadoTiempoCompleto obj where :version = obj.version "),
		@NamedQuery(name = "EmpleadoTiempoCompleto.findByBase", query = "select obj from EmpleadoTiempoCompleto obj where :base = obj.base "),
		@NamedQuery(name = "EmpleadoTiempoCompleto.findByComplemento", query = "select obj from EmpleadoTiempoCompleto obj where :complemento = obj.complemento ")})
public class EmpleadoTiempoCompleto extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	private double base;

	private double complemento;

	public EmpleadoTiempoCompleto() {
	}

	public EmpleadoTiempoCompleto(TEmpleadoTiempoCompleto e) {
		super(e.getId(), e.getNif(), e.isActivo(), e.getId_turno());
		this.complemento = e.getComplemento();
		this.base = e.getBase();
	}


	public int calcularSueldo() {
		return (int) (base + complemento);
	}

	@Override
	public TEmpleadoTiempoCompleto toTransfer() {
		return new TEmpleadoTiempoCompleto(getId(), isActivo(), getNif(), getTurno().getId(), base, complemento);
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getComplemento() {
		return complemento;
	}

	public void setComplemento(double complemento) {
		this.complemento = complemento;
	}

}