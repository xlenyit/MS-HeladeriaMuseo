package negocio.factura;

public class TLineaFactura {

	private int idFactura;

	private int idActividad;

	private int cantidad;

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int id) {
		this.idFactura = id;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int id) {
		this.idActividad = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public TLineaFactura(int idActividad, int idFactura, int cantidad) {
		this.idActividad = idActividad;
		this.idFactura = idFactura;
		this.cantidad = cantidad;
	}

	public String toString() {
		return "Actividad: " + this.idActividad + '\n' + "Cantidad: " + this.cantidad + '\n';
	}
}