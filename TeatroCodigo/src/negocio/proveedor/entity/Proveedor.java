
package negocio.proveedor.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.NamedQueries;
import java.util.Collection;
import negocio.producto.entidad.Producto;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import negocio.proveedor.TProveedor;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "NIF") })
@NamedQueries({
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findByid", query = "select obj from Proveedor obj where :id = obj.id "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findByversion", query = "select obj from Proveedor obj where :version = obj.version "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findByactivo", query = "select obj from Proveedor obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findBytelefono", query = "select obj from Proveedor obj where :telefono = obj.telefono "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findByNIF", query = "select obj from Proveedor obj where :NIF = obj.NIF "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findBydireccion", query = "select obj from Proveedor obj where :direccion = obj.direccion "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findByproducto", query = "select obj from Proveedor obj where :producto MEMBER OF obj.producto "),
		@NamedQuery(name = "negocio.proveedor.entity.Proveedor.findAll", query = "select obj from Proveedor obj") })

public class Proveedor implements Serializable {

	private static final long serialVersionUID = 0;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@Version
	private int version;

	private boolean activo;

	private String telefono;

	private String NIF;

	private String direccion;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Producto> producto;

	public Proveedor(Integer id, String Nif, String dir, String telf) {
		this.id = id;
		this.NIF = Nif;
		this.direccion = dir;
		this.telefono = telf;

	}

	public Proveedor(String NIF) {
		this.NIF = NIF;
	}

	public Proveedor(TProveedor tProveedor) {
		this.id = tProveedor.getId();
		this.activo = tProveedor.getActivo();
		this.NIF = tProveedor.getNIF();
		this.telefono = tProveedor.getTelefono();
		this.direccion = tProveedor.getDireccion();

	}

	public Proveedor() {

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNIF() {
		return this.NIF;
	}

	public void setNIF(String nif) {
		this.NIF = nif;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telf) {
		this.telefono = telf;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Collection<Producto> getProductos() {
		return this.producto;
	}

	public void setProductos(Collection<Producto> ListaProductos) {
		this.producto = ListaProductos;
	}
	
	public void addProducto(Producto p){
		this.producto.add(p);
	}
	
	public void removeProducto(Producto p)
	{
		this.producto.remove(p);
	}

	public TProveedor toTransfer() {
		return new TProveedor((int) id, NIF, telefono, direccion, activo);
	}
}