package negocio.producto.entidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import java.util.Collection;
import negocio.facturaTienda.entidad.LineaGuia;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import negocio.marca.entidad.Marca;
import javax.persistence.ManyToOne;
import negocio.proveedor.entity.Proveedor;
import negocio.producto.TProducto;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"nombre", "marca_id"})})
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPO_PRODUCTO")
@NamedQueries({
		@NamedQuery(name = "Producto.findByversion", query = "select obj from Producto obj where :version = obj.version "),
		@NamedQuery(name = "Producto.findByid", query = "select obj from Producto obj where :id = obj.id "),
		@NamedQuery(name = "Producto.findBynombre", query = "select obj from Producto obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Producto.findByprecio", query = "select obj from Producto obj where :precio = obj.precio "),
		@NamedQuery(name = "Producto.findBystock", query = "select obj from Producto obj where :stock = obj.stock "),
		@NamedQuery(name = "Producto.findByactivo", query = "select obj from Producto obj where :activo = obj.activo "),
		@NamedQuery(name = "Producto.findBylineaFacturaTienda", query = "select obj from Producto obj where :lineaFacturaTienda MEMBER OF obj.lineaFacturaTienda "),
		@NamedQuery(name = "Producto.findBymarca", query = "select obj from Producto obj where :marca = obj.marca "),
		@NamedQuery(name = "Producto.findBymarcaAndNombre", query = "select obj from Producto obj where :marca = obj.marca and :nombre = obj.nombre "),
		@NamedQuery(name = "Producto.findAll", query = "select obj from Producto obj"),
		@NamedQuery(name = "Producto.findByproveedor", query = "select obj from Producto obj where :proveedor MEMBER OF obj.proveedor") })

public  class Producto implements Serializable {

	private static final long serialVersionUID = 0;
	@Version
	private Integer version;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String nombre;
	private Double precio;
	private Integer stock;
	private Boolean activo;
	@ManyToOne
	private Marca marca;
	
	@ManyToMany
	private Collection<Proveedor> proveedor;
	
	public Producto() {
		
	}
	
	public Producto(Integer id, String nombre, Double precio, Integer stock, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.activo = activo;		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getActivo() {
		return activo;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@OneToMany(mappedBy = "producto")
	private Collection<LineaGuia> lineaFacturaTienda;

	public Collection<LineaGuia> getLineaFacturaTienda() {
		return lineaFacturaTienda;
	}

	public void setLineaFacturaTienda(Collection<LineaGuia> lineaFacturaTienda) {
		this.lineaFacturaTienda = lineaFacturaTienda;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Collection<Proveedor> getProveedor() {
		return proveedor;
	}

	public void setProveedor(Collection<Proveedor> proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public TProducto toTransfer() {
		return new TProducto(id, marca.getId(), nombre, stock, precio, activo);
	}

	public void getProveedores(Proveedor ListaProveedores) {

	}

	public Collection<Proveedor> setProveedores() {
		return null;
	}
	
	public void addProveedor(Proveedor p){
		this.proveedor.add(p);
	}
	
	public void removeProveedor(Proveedor p) {
		this.proveedor.remove(p);
	}

}