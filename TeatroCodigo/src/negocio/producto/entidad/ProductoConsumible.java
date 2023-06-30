package negocio.producto.entidad;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import negocio.producto.TProductoConsumible;

@Entity
@DiscriminatorValue("C")
@NamedQueries({
		@NamedQuery(name = "negocio.producto.entidad.ProductoConsumible.findByversion", query = "select obj from ProductoConsumible obj where :version = obj.version "),
		@NamedQuery(name = "negocio.producto.entidad.ProductoConsumible.findByid", query = "select obj from ProductoConsumible obj where :id = obj.id "),
		@NamedQuery(name = "negocio.producto.entidad.ProductoConsumible.findBytiempo_caducidad", query = "select obj from ProductoConsumible obj where :tiempo_caducidad = obj.tiempo_caducidad ")})

public class ProductoConsumible extends Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date tiempo_caducidad;
	
	public ProductoConsumible() {
		
	}
	
	public ProductoConsumible(TProductoConsumible p) {
		super(p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getActivo());
		this.tiempo_caducidad = p.getTiempoCaducidad();
	}
	
	public TProductoConsumible toTransfer() {
		return new TProductoConsumible(getId(), getMarca().getId(), getNombre(), getStock(), getPrecio(), tiempo_caducidad, getActivo());
	}
	
	public Date getTiempoCaducidad() {
		return tiempo_caducidad;
	}
	
	public void setTiempoCaducidad(Date tiempo_caducidad) {
		this.tiempo_caducidad = tiempo_caducidad;
	}

}
