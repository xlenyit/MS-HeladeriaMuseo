
package negocio.empleado;

public class TEmpleado {
	
	protected int id;
	protected boolean activo;
	protected String nif;
	//protected double bonus;
	protected int id_turno;

	public TEmpleado(int id, boolean activo, String nif,int id_turno) {
		this.id = id;
		this.activo = activo;
		this.nif = nif;
		//this.bonus = bonus;
		this.id_turno = id_turno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getId_turno() {
		return id_turno;
	}

	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}
	
}