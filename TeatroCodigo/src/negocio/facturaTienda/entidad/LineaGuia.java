package negocio.facturaTienda.entidad;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import negocio.facturaTienda.imp.TLineaFacturaTienda;
import negocio.producto.entidad.Producto;

@Entity
@IdClass(LineaFacturaTiendaId.class)
@NamedQueries({
		@NamedQuery(name = "negocio.facturaTienda.entidad.LineaFacturaTienda.findBycantidad", query = "select obj from LineaFacturaTienda obj where :cantidad = obj.cantidad "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.LineaFacturaTienda.findByprecio", query = "select obj from LineaFacturaTienda obj where :precio = obj.precio "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.LineaFacturaTienda.findByfacturaTienda", query = "select obj from LineaFacturaTienda obj where :facturaTienda = obj.facturaTienda "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.LineaFacturaTienda.findByproducto", query = "select obj from LineaFacturaTienda obj where :producto = obj.producto "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.LineaFacturaTienda.findByversion", query = "select obj from LineaFacturaTienda obj where :version = obj.version ") })
public class LineaGuia implements Serializable {
	private static final long serialVersionUID = 0;
	
	@Id
	@ManyToOne
	private Producto producto;
	
	@Id
	@ManyToOne
	private FacturaTienda facturaTienda;

	private int cantidad;
	private double precio;
	
	@Version
	private int version;

	public LineaGuia() {

	}
	
	public LineaGuia(int cantidad, double precio) {
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public LineaGuia(TLineaFacturaTienda tLineaFacturaTienda) {
		this.cantidad = tLineaFacturaTienda.getCantidad();
		this.precio = tLineaFacturaTienda.getPrecio();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setFactura(FacturaTienda factura) {
		this.facturaTienda = factura;
	}

	public FacturaTienda getFactura() {
		return facturaTienda;
	}

	public TLineaFacturaTienda toTransfer() {
		return new TLineaFacturaTienda(producto.getId(), facturaTienda.getId(), cantidad, precio);
	}

	public String toString() {
		return "Factura: " + this.facturaTienda.getId() + '\n' + "Producto: " + this.producto.getId() + '\n' + "Cantidad: "
				+ this.cantidad + '\n' + "Precio: " + this.precio + '\n';
	}
}