package negocio.venta;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import negocio.trabajador.TTrabajador;

public class TVenta {
	private Integer id;
	private Boolean activo;
	private float precioTotal;
	private TTrabajador trabajador;
	private List<TLineaVenta> productos;
	private Timestamp fecha;

	public TVenta(Integer id, Boolean activo, float precioTotal, TTrabajador trabajador, Timestamp fecha) {
		this.id = id;
		this.activo = activo;
		this.precioTotal = precioTotal;
		this.trabajador = trabajador;
		this.fecha = fecha;
		this.productos = new ArrayList<>();
	}
	public TVenta(Integer id) {
		this.id = id;
	}
	public TVenta() {
		this.productos = new ArrayList<>();
	}

	public TVenta(Integer id, Boolean activo, float precioTotal, TTrabajador trabajador) {
		this.id = id;
		this.activo = activo;
		this.precioTotal = precioTotal;
		this.trabajador = trabajador;
		this.productos = new ArrayList<>();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setPrecioTotal(float precio) {
		this.precioTotal = precio;
	}

	public float getPrecioTotal() {
		return this.precioTotal;
	}

	public TTrabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(TTrabajador trabajador) {
		this.trabajador = trabajador;
	}

	public List<TLineaVenta> getProductos() {
		return productos;
	}


	public void vaciarCarro() {
		productos.clear();
	}

	public void setProductos(List<TLineaVenta> productos) {
		this.productos = productos;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void addProduct(TLineaVenta producto) {
		productos.add(producto);
	}
	public boolean removeProduct(int id) {
		int i = 0;
		for(TLineaVenta p : productos) {
			if(p.isEqual(id)) {
				productos.remove(i);
				return true;
			}
			i++;
		}
		
		return false;
	}
	public void restarPrecio(float p) {
		precioTotal -= p;
	}
}
