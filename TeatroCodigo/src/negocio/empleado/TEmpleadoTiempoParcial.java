
package negocio.empleado;

public class TEmpleadoTiempoParcial extends TEmpleado {
	
	private double horas;
	private double sueldoPorHora;
	
	public TEmpleadoTiempoParcial(int id, boolean activo, String nif, int id_turno, double horas, double sueldoPorHora) {
		super(id, activo, nif, id_turno);
		this.horas = horas;
		this.sueldoPorHora = sueldoPorHora;
	}

	public double getHoras() {
		return horas;
	}

	public void setHoras(double horas) {
		this.horas = horas;
	}

	public double getSueldoPorHora() {
		return sueldoPorHora;
	}

	public void setSueldoPorHora(double sueldoPorHora) {
		this.sueldoPorHora = sueldoPorHora;
	}
	public String toString() {
		return "Id: " + this.id + '\n' + "NIF: " + this.nif + '\n'
				+ "ID_Turno: " + this.id_turno + '\n'
				+"Horas: " + this.horas + '\n' + "Sueldo por hora: " + this.sueldoPorHora + '\n'
				+ (this.activo ? "Activo" : "Inactivo") + '\n';
	}
	
}