package negocio.temporada;

import java.sql.Date;

public class TTemporada {

	private int id;

	private boolean activo;

	private int Numerodetemporada;

	private double Calificacion;

	private Date Fechadeinicio;

	private Date Fechadefin;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getNumTemporada() {
		return this.Numerodetemporada;
	}

	public void setNumTemporada(int num) {
		this.Numerodetemporada = num;
	}

	public double getCalificacion() {
		return this.Calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.Calificacion = calificacion;
	}

	public Date getFechaInicio() {
		return this.Fechadeinicio;
	}

	public void setFechaInicio(Date fecha) {
		this.Fechadeinicio = fecha;
	}

	public Date getFechaFin() {
		return this.Fechadefin;
	}

	public void setFechaFin(Date fecha) {
		this.Fechadefin = fecha;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param num
	* @param calificacion
	* @param fechaInicio
	* @param fechaFin
	* @param id
	* @param activo
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TTemporada(int num, double calificacion, Date fechaInicio, Date fechaFin, int id, boolean activo) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public TTemporada(int id, int num, double calificacion, Date fechaInicio, Date fechaFin, boolean activo) {
		this.id = id;
		this.activo = true;
		this.Numerodetemporada = num;
		this.Calificacion = calificacion;
		this.Fechadeinicio = fechaInicio;
		this.Fechadefin = fechaFin;
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "id: " + id + '\n' + "numero de temporada: " + Numerodetemporada + '\n' + "Calificacion: " + Calificacion
				+ '\n' + "Fecha inicio: " + Fechadeinicio + '\n' + "Fecha fin: " + Fechadefin + '\n' + "Activa: "
				+ activo + '\n' + '\n';
	}
}