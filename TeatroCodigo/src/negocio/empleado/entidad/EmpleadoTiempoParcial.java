
package negocio.empleado.entidad;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import negocio.empleado.TEmpleadoTiempoParcial;

@Entity
@DiscriminatorValue("P")
@NamedQueries({
		@NamedQuery(name = "EmpleadoTiempoParcial.findById", query = "select obj from EmpleadoTiempoParcial obj where :id = obj.id "),
		@NamedQuery(name = "EmpleadoTiempoParcial.findByVersion", query = "select obj from EmpleadoTiempoParcial obj where :version = obj.version "),
		@NamedQuery(name = "EmpleadoTiempoParcial.findByHoras", query = "select obj from EmpleadoTiempoParcial obj where :horas = obj.horas "),
		@NamedQuery(name = "EmpleadoTiempoParcial.findBySueldoPorHora", query = "select obj from EmpleadoTiempoParcial obj where :sueldoPorHora = obj.sueldoPorHora ") })
public class EmpleadoTiempoParcial extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	private int horas;

	private double sueldoPorHora;


	public EmpleadoTiempoParcial() {
	}

	public EmpleadoTiempoParcial(TEmpleadoTiempoParcial e) {
		super(e.getId(), e.getNif(), e.isActivo(), e.getId_turno());
		this.sueldoPorHora = e.getSueldoPorHora();
		this.horas = (int) e.getHoras();
	}

	public int calcularSueldo() {
		return (int) (horas * sueldoPorHora);
	}

	@Override
	public TEmpleadoTiempoParcial toTransfer() {
		return new TEmpleadoTiempoParcial(getId(), isActivo(), getNif(), getTurno().getId(), horas, sueldoPorHora);
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public double getSueldoPorHora() {
		return sueldoPorHora;
	}

	public void setSueldoPorHora(double sueldoPorHora) {
		this.sueldoPorHora = sueldoPorHora;
	}
	
	
}