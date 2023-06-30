package negocio.producto.entidad;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import negocio.producto.TProductoDurable;

@Entity
@DiscriminatorValue("D")
@NamedQueries({
		@NamedQuery(name = "negocio.producto.entidad.ProductoDurable.findByversion", query = "select obj from ProductoDurable obj where :version = obj.version "),
		@NamedQuery(name = "negocio.producto.entidad.ProductoDurable.findByid", query = "select obj from ProductoDurable obj where :id = obj.id "),
		@NamedQuery(name = "negocio.producto.entidad.ProductoDurable.findBytipo_durable", query = "select obj from ProductoDurable obj where :tipo_durable = obj.tipo_durable ")})

public class ProductoDurable extends Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tipo_durable;
	
	public ProductoDurable() {

	}
	
	public ProductoDurable(TProductoDurable p) {
		super(p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getActivo());
		this.tipo_durable = p.getTipo();
	}

	public TProductoDurable toTransfer() {
		return new TProductoDurable(getId(), getMarca().getId(), getNombre(), getStock(), getPrecio(), tipo_durable, getActivo());
	}
	
	public String getTipoDurable() {
		return tipo_durable;
	}
	
	public void setTipoDurable(String tipo) {
		this.tipo_durable = tipo;
	}

}
