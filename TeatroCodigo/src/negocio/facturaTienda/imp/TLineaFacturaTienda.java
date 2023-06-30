package negocio.facturaTienda.imp;

public class TLineaFacturaTienda {

	private int idFactura;
	
	private int idProducto;
	
	private int cantidad;
	
	private double precio;

	public TLineaFacturaTienda(int idProducto, int idFactura, int cantidad, double precio) {
		this.idProducto = idProducto;
		this.idFactura = idFactura;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String toString() {
		return "ID de factura: " + idFactura + '\n' + "ID de producto: " + idProducto + '\n' + "Cantidad: " + cantidad
				+ '\n' + "Precio: " + precio + '\n';
	}
}