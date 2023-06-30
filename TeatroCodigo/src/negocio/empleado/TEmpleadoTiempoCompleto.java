
package negocio.empleado;

public class TEmpleadoTiempoCompleto extends TEmpleado {
	
	private double base;
	private double complemento;
	
	public TEmpleadoTiempoCompleto(int id, boolean activo, String nif, int id_turno, double base, double complemento) {
		super(id, activo, nif, id_turno);
		this.base = base;
		this.complemento = complemento;
	}
	
	public TEmpleadoTiempoCompleto(int id, boolean activo, String nif, int id_turno) {
		super(id, activo, nif, id_turno);
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
	public String toString() {
		return "Id: " + this.id + '\n' + "NIF: " + this.nif + '\n'
				+ "ID_Turno: " + this.id_turno + '\n'
				+"Base: " + this.base + '\n' + "Complemento: " + this.complemento + '\n'
				+ (this.activo ? "Activo" : "Inactivo") + '\n';
	}
	
}