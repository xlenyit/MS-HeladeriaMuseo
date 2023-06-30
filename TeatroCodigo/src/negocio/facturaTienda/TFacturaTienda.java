package negocio.facturaTienda;

import java.sql.Date;


public class TFacturaTienda {
	
	private int id;
	
	private boolean activo;

	private double precioTotal;
	
	private int numProductos;
	
	private Date fecha;
	
	private int idEmpleado;

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

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getNumProductos() {
		return numProductos;
	}

	public void setNumProductos(int numProductos) {
		this.numProductos = numProductos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public TFacturaTienda(int id, double precioTotal, int numProductos, Date fecha, int idEmpleado, boolean activo) {
		this.id = id;
		this.precioTotal = precioTotal;
		this.numProductos = numProductos;
		this.fecha = fecha;
		this.idEmpleado = idEmpleado;
		this.activo = activo;
	}

	public String toString() {
		return "Id: " + this.id + '\n' + "Empleado: " + this.idEmpleado + '\n' + "Número de productos: "
				+ this.numProductos + '\n' + "Importe total: " + this.precioTotal + '\n' + "Fecha: " + this.fecha + '\n'
				+ '\n';
	}
}