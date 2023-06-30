package negocio.exposicion;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import java.util.List;

import integracion.factoriaEntityManager.FactoriaEntityManager;
import negocio.guia.LineaGuia;
import negocio.obra.Obra;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.exposicion.Exposicion.findByid", query = "select obj from Exposicion obj where :id = obj.id "),
		@NamedQuery(name = "negocio.exposicion.Exposicion.findBygenero", query = "select obj from Exposicion obj where :genero = obj.genero "),
		@NamedQuery(name = "negocio.exposicion.Exposicion.findBynombre", query = "select obj from Exposicion obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.exposicion.Exposicion.findByactivo", query = "select obj from Exposicion obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.exposicion.Exposicion.findByversion", query = "select obj from Exposicion obj where :version = obj.version "),
		@NamedQuery(name = "negocio.exposicion.Exposicion.findByobra", query = "select obj from Exposicion obj where :obra MEMBER OF obj.obra "),
		@NamedQuery(name = "negocio.exposicion.Exposicion.findAll", query = "select obj from Exposicion obj")})

public class Exposicion implements Serializable {

	private static final long serialVersionUID = 0;

	public Exposicion() {
	}
	
	public Exposicion(TExposicion tExposicion){
		id = tExposicion.getId();
		genero = tExposicion.getGenero();
		nombre = tExposicion.getNombre();
		activo = tExposicion.getActivo();
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String genero;
	private String nombre;
	private Boolean activo;
	@Version
	private Integer version;
	
	/*@OneToMany (mappedBy = "exposicion")*/
	private List<LineaGuia> guias;
	
	@OneToMany(mappedBy = "exposicion")
	private List<Obra> obra;
	
	public List<Obra> getObras() {
		return obra;
	}
	
	public List<LineaGuia> getGuias() {
		return guias;
	}

	public void setGenero(String genero) {
		this.genero=genero;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setActivo(Boolean activo) {
		this.activo=activo;
	}

	public void setVersion(Integer version) {
		this.version=version;
	}

	public String getGenero() {
		return genero;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public Boolean getActivo() {
		return activo;
	}

	public Integer getVersion() {
		return version;
	}
	
	public Integer getId() {
		return id;
	}
	
	public TExposicion toTransfer(){
		return new TExposicion(nombre,genero, activo, id);
	}
	
	public List<Exposicion> findByNombre(TExposicion tExposicion){
		EntityManager entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Exposicion> query = entityManager.createNamedQuery("negocio.exposicion.Exposicion.findBynombre", Exposicion.class);
		query.setParameter("nombre", tExposicion.getNombre());
		return query.getResultList();
	}
}