package negocio.marca.entidad;

import javax.persistence.Entity;

import java.io.Serializable;
import negocio.marca.TMarca;
import negocio.producto.entidad.Producto;

import java.util.Collection;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Marca.findById", query = "select obj from Marca obj where :id = obj.id "),
		@NamedQuery(name = "Marca.findByVersion", query = "select obj from Marca obj where :version = obj.version "),
		@NamedQuery(name = "Marca.findByNombre", query = "select obj from Marca obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Marca.findByActivo", query = "select obj from Marca obj where :activo = obj.activo "),
		@NamedQuery(name = "Marca.findByProducto", query = "select obj from Marca obj where :producto MEMBER OF obj.productos "),
		@NamedQuery(name = "Marca.findAll", query = "select obj from Marca obj") })

public class Marca implements Serializable {

	private static final long serialVersionUID = 0;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@Version
	private int version;

	@OneToMany(mappedBy = "marca")
	private Collection<Producto> productos;

	private String nombre;

	private boolean activo;

	public Marca() {}

	public Marca(String nombre) {
		this.nombre = nombre;
	}
	
	public Marca(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Marca(TMarca tMarca) {
		this.id = tMarca.getId();
		this.nombre = tMarca.getNombre();
		this.activo = tMarca.getActivo();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setProducto(Collection<Producto> productos) {
		this.productos = productos;
	}

	public Collection<Producto> getProducto() {
		return productos;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public TMarca toTransfer() {
		return new TMarca(id, nombre, activo);
	}

}