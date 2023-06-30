package negocio.facturaTienda.entidad;

import java.io.Serializable;

public class LineaFacturaTiendaId implements Serializable {
	private static final long serialVersionUID = 0;

	private Integer facturaTienda;
	private Integer producto;
	
	public LineaFacturaTiendaId() {
	}

	public LineaFacturaTiendaId(int idFactura, int idProducto) {
		this.facturaTienda = idFactura;
		this.producto = idProducto;
	}
	
	public int getFactura() {
		return facturaTienda;
	}

	public void setFactura(int factura) {
		this.facturaTienda = factura;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof LineaFacturaTiendaId))
			return false;
		LineaFacturaTiendaId pk = (LineaFacturaTiendaId) obj;
		if (!(facturaTienda == pk.facturaTienda))
			return false;
		if (!(producto == pk.producto))
			return false;
		return true;
	}

	public int hashCode() {
		return facturaTienda * 31 + producto;
	}
}