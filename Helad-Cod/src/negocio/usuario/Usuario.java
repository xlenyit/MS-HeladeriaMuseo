package negocio.usuario;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Version;
import jakarta.persistence.NamedQueries;

import java.util.ArrayList;
import java.util.List;

import negocio.actividad.Actividad;
import negocio.guia.Guia;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.usuario.Usuario.findByid", query = "select obj from Usuario obj where :id = obj.id "),
		@NamedQuery(name = "negocio.usuario.Usuario.findByactivo", query = "select obj from Usuario obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.usuario.Usuario.findByversion", query = "select obj from Usuario obj where :version = obj.version "),
		@NamedQuery(name = "negocio.usuario.Usuario.findBynombre", query = "select obj from Usuario obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.usuario.Usuario.findBydni", query = "select obj from Usuario obj where :dni = obj.dni "),
		@NamedQuery(name = "negocio.usuario.Usuario.findByactividad", query = "select obj from Usuario obj where :actividades MEMBER OF obj.actividades "),
		@NamedQuery(name = "negocio.usuario.Usuario.findByguia", query = "select obj from Usuario obj where :guia = obj.guia "),
		@NamedQuery(name="negocio.usuario.Usuario.findAll", query="select obj FROM Usuario obj")		
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 0;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	private Boolean activo;
	@Version
	private Integer version;
	private String nombre;
	private String dni;
	
	@ManyToMany
	private List<Actividad> actividades;
	
	@ManyToOne
	private Guia guia;
	
	public Usuario() {
	}
	public Usuario(TUsuario tUsuario) {
		id=tUsuario.getId();
		activo=tUsuario.getActivo();
		nombre=tUsuario.getNombre();
		dni=tUsuario.getDni();
		actividades=new ArrayList<Actividad>();
	}
	public Integer getId() {
		return id;
	}
	public Boolean getActivo() {
		return activo;
	}

	public Integer getVersion() {
		return version;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}
	
	public Guia getGuia() {
		return guia;
	}
	
	public void setGuia(Guia guia){
		this.guia = guia;
	}

	public void setActivo(Boolean activo) {
		this.activo=activo;
	}

	public void setVersion(Integer version) {
		this.version=version;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setDni(String dni) {
		this.dni=dni;
	}

	public TUsuario toTransfer() {
		TUsuario tUsuario = new TUsuario ();
		tUsuario.setActivo(this.getActivo());
		tUsuario.setNombre(this.getNombre());
		tUsuario.setDni(this.getDni());
		tUsuario.setIdGuia(this.guia.getId());
		tUsuario.setId(this.getId());

		
		return tUsuario;
	}
	
	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
}