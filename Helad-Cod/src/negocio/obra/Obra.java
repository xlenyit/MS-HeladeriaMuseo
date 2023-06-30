package negocio.obra;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Version;
import negocio.exposicion.Exposicion;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.obra.Obra.findByid", query = "select obj from Obra obj where :id = obj.id "),
		@NamedQuery(name = "negocio.obra.Obra.findByversion", query = "select obj from Obra obj where :version = obj.version "),
		@NamedQuery(name = "negocio.obra.Obra.findByactivo", query = "select obj from Obra obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.obra.Obra.findBynombre", query = "select obj from Obra obj where :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.obra.Obra.findByautor", query = "select obj from Obra obj where :autor = obj.autor "),
		@NamedQuery(name = "negocio.obra.Obra.findBycoste", query = "select obj from Obra obj where :coste = obj.coste "),
		@NamedQuery(name = "negocio.obra.Obra.findByexposicion", query = "select obj from Obra obj where :exposicion = obj.exposicion "), 
		@NamedQuery(name = "negocio.obra.Obra.findBynombreYautor", query = "select obj from Obra obj where :autor = obj.autor and :nombre = obj.nombre "),
		@NamedQuery(name = "negocio.obra.Obra.findAll", query = "select obj from Obra obj")})
public class Obra implements Serializable {
	private static final long serialVersionUID = 0;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	protected Integer id;
	
	@Version
	private Integer version;
	
	protected Boolean activo;
	protected String nombre;
	protected String autor;
	protected Double coste;
	
	@ManyToOne
	protected Exposicion exposicion;
	
//	public static final Map<String, Double> PORCENTAJE_ESCULTURAS = new HashMap<String, Double>();
	
	public Obra(TObra tObra) {
		this.id = tObra.getId();
		this.activo = tObra.getActivo();
		this.nombre = tObra.getNombre();
		this.autor = tObra.getAutor();
		this.coste = tObra.getCoste();
		
	}
	public Obra() {
		
	}

	public Integer getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version=version;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo=activo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setCoste(Double coste) {
		this.coste=coste;
	}

	public Double getCoste() {
		return coste;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor=autor;
	}
	public Exposicion getExposicion() {
		return exposicion;
	}

	public void setExposicion(Exposicion exposicion) {
		this.exposicion = exposicion;
	}

	public double calcularCoste() {
		return 0;
	}

}