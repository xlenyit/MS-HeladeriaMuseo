package negocio.venta;

import negocio.producto.TProducto;

public class TLineaVenta {
	private Integer cantidad;
	private TProducto producto;
	
	public TLineaVenta(Integer cantidad, TProducto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}
	public TLineaVenta() {
		
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public TProducto getProducto() {
		return producto;
	}
	public void setProducto(TProducto producto) {
		this.producto = producto;
	}
	public boolean isEqual(int id) {
		return producto.getId() == id;
	}
}
