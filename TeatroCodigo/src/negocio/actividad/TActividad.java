package negocio.actividad;

import java.sql.Date;
import java.sql.Time;

public class TActividad {

	private int id;

	private boolean activo;

	/** 
	* @return the activo
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean isActivo() {
		// begin-user-code
		return activo;
		// end-user-code
	}

	private int entradasDisponibles;

	private double precio;

	private Date fechaInicio;

	private Date fechaFin;

	private Time hora;

	private int duracion;

	private String tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getEntradasDisponibles() {
		return entradasDisponibles;
	}

	public void setEntradasDisponibles(int entradas) {
		this.entradasDisponibles = entradas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fecha) {
		this.fechaInicio = fecha;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fecha) {
		this.fechaFin = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param entradasDisponibles
	* @param precio
	* @param fechaInicio
	* @param fechaFin
	* @param hora
	* @param duracion
	* @param tipo
	* @param activo
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TActividad(int id, int entradasDisponibles, double precio, Date fechaInicio, Date fechaFin, Time hora,
			int duracion, String tipo, boolean activo) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public TActividad(int id, String tipo, int entradasDisponibles, double precio, Date fechaInicio, Date fechaFin,
			Time hora, int duracion, boolean activo) {
		this.id = id;
		this.tipo = tipo;
		this.activo = true;
		this.entradasDisponibles = entradasDisponibles;
		this.precio = precio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.hora = hora;
		this.duracion = duracion;
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "ID: " + id + '\n' + "Entradas disponibles: " + entradasDisponibles + '\n' + "Precio: " + precio + '\n'
				+ "Fecha de inicio: " + fechaInicio + '\n' + "Fecha de fin: " + fechaFin + '\n' + "Hora: " + hora + '\n'
				+ "Duracion: " + duracion + '\n' + "Tipo: " + tipo + '\n';
	}
}