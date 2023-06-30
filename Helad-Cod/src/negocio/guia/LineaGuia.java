
package negocio.guia;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Version;
import negocio.exposicion.Exposicion;
import jakarta.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.guia.LineaGuia.findByid", query = "select obj from LineaGuia obj where :lgid = obj.lgid "),
		//@NamedQuery(name = "negocio.guia.LineaGuia.findByidYhora", query = "select obj from LineaGuia obj where :lgid.idGuia = obj.lgid.idGuia and :lgid.horaIni = obj.lgid.horaIni"),
		@NamedQuery(name = "negocio.guia.LineaGuia.findByversion", query = "select obj from LineaGuia obj where :version = obj.version "),
		@NamedQuery(name = "negocio.guia.LineaGuia.findByguia", query = "select obj from LineaGuia obj where :guia = obj.guia "), // si falla algo es el MEMBER OF
		@NamedQuery(name = "negocio.guia.LineaGuia.findByexposicion", query = "select obj from LineaGuia obj where :exposicion = obj.exposicion "),
		@NamedQuery(name = "negocio.guia.LineaGuia.findAll", query = "select obj from LineaGuia obj  ")}) //aqui tambien
public class LineaGuia implements Serializable {

	private static final long serialVersionUID = 0;

	public LineaGuia() {
	}

	@EmbeddedId
	private LineaGuiaID lgid;
	@Version
	private Integer version;

	@ManyToOne
	@MapsId("idExposicion")
	private Exposicion exposicion;
	@ManyToOne
	@MapsId("idGuia")
	private Guia guia;

	//private Integer horaInicio; // atributo de relacion N N

	public LineaGuiaID getLgid() {
		return lgid;
	}

	public void setLgid(LineaGuiaID lgid) {
		this.lgid = lgid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Exposicion getExposicion() {
		return exposicion;
	}

	public void setExposicion(Exposicion exposicion) {
		this.exposicion = exposicion;
	}

	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}	
}