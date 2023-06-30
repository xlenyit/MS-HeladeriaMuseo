package negocio.actividad;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.actividad.LineaActividad.findByid", query = "select obj from LineaActividad obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.actividad.LineaActividad.findByversion", query = "select obj from LineaActividad obj where :version = obj.version ") })
public class LineaActividad implements Serializable {
	private static final long serialVersionUID = 0;

	public LineaActividad() {
	}

	@Id
	private Integer id;
	private Integer version;
}