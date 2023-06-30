
package negocio.actividad;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Version;
import jakarta.persistence.NamedQueries;

import java.util.Date;
import java.util.List;

import negocio.usuario.Usuario;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.actividad.Actividad.findByid", query = "select obj from Actividad obj where :id = obj.id "),
		@NamedQuery(name = "negocio.actividad.Actividad.findByversion", query = "select obj from Actividad obj where :version = obj.version "),
		@NamedQuery(name = "negocio.actividad.Actividad.findByactivo", query = "select obj from Actividad obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.actividad.Actividad.findBycodigo", query = "select obj from Actividad obj where :codigo = obj.codigo "),
		@NamedQuery(name = "negocio.actividad.Actividad.findBynombre", query = "select obj from Actividad obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.actividad.Actividad.findByfecha", query = "select obj from Actividad obj where :fecha = obj.fecha "),
		@NamedQuery(name = "negocio.actividad.Actividad.findByusuario", query = "select obj from Actividad obj where :usuario MEMBER OF obj.usuario "),
		@NamedQuery(name = "negocio.actividad.Actividad.findAll", query = "select obj from Actividad obj ")})
public class Actividad implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	protected Integer id;
	
	@Version
	private Integer version;
	
	protected boolean activo;
	protected Integer codigo;
	protected Date fecha;
	protected String nombre;
	
	@ManyToMany(mappedBy = "actividades")
	protected List<Usuario> usuario;
	
	public Actividad() {}
	public Actividad(Integer id, String nombre, Integer codigo, boolean activo, Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.activo = activo;
		this.fecha = fecha;
	}
	public Actividad(TActividad tActividad) {
		id = tActividad.getId();
		activo = tActividad.getActivo();
		codigo = tActividad.getCodigo();
		nombre = tActividad.getNombre();
		fecha = tActividad.getFecha();
		//usuario = new ArrayList<Usuario>();
	}

	
	public Integer getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public List<Usuario> getUsuarios() {
		return usuario;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuario = usuarios;
	}
	
	public TActividad toTransfer() {
		return null;
	}
}