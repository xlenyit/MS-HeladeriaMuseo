package negocio.guia;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;

import java.util.List;
import negocio.usuario.Usuario;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.guia.Guia.findByid", query = "select obj from Guia obj where :id = obj.id "),
		@NamedQuery(name = "negocio.guia.Guia.findBynombre", query = "select obj from Guia obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.guia.Guia.findByversion", query = "select obj from Guia obj where :version = obj.version "),
		@NamedQuery(name = "negocio.guia.Guia.findByusuario", query = "select obj from Guia obj where :usuario MEMBER OF obj.usuario "),
		@NamedQuery(name = "negocio.guia.Guia.findAll", query = "select obj from Guia obj"),})
public class Guia implements Serializable {

	private static final long serialVersionUID = 0;

	public Guia() {
	}

	public Guia(TGuia tGuia) {
		id = tGuia.getId();
		nombre = tGuia.getNombre();
		activo = tGuia.getActivo();
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String nombre;
	@Version
	private Integer version;
	private boolean activo;


	@OneToMany(mappedBy = "guia")
	private List<Usuario> usuario;

//	@OneToMany(mappedBy = "guias")
	private List<LineaGuia> guias;
	

	public Integer getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {
		return activo;
	}

	
	public String getNombre() {
		return nombre;
	}

	public Integer getVersion() {
		return version;
	}
	
	public TGuia toTransfer() {
		TGuia tUsuario = new TGuia();
		tUsuario.setId(this.getId());
		tUsuario.setNombre(this.getNombre());
		tUsuario.setActivo(this.getActivo());
		return tUsuario;
	}
	
}