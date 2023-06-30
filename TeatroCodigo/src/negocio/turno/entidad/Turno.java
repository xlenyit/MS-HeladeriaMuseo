package negocio.turno.entidad;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Collection;
import negocio.empleado.entidad.Empleado;
import javax.persistence.OneToMany;
import negocio.turno.TTurno;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "horario") })
@Entity
@NamedQueries({ @NamedQuery(name = "Turno.findById", query = "select obj from Turno obj where :id = obj.id "),
		@NamedQuery(name = "Turno.findByVersion", query = "select obj from Turno obj where :version = obj.version "),
		@NamedQuery(name = "Turno.findByHorario", query = "select obj from Turno obj where :horario = obj.horario "),
		@NamedQuery(name = "Turno.findByActivo", query = "select obj from Turno obj where :activo = obj.activo "),
		@NamedQuery(name = "Turno.findByEmpleados", query = "select obj from Turno obj where :empleados MEMBER OF obj.empleados "),
		@NamedQuery(name = "Turno.findAll", query = "select obj from Turno obj") })

public class Turno implements Serializable {

	private static final long serialVersionUID = 0;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Version
	private int version;
	private String horario;
	private boolean activo;

	@OneToMany(mappedBy = "turno")
	private Collection<Empleado> empleados;
	
	public Turno() {
	}
	
	public Turno(String horario) {
		this.horario = horario;
	}
	
	public Turno(int id, String horario) {
		this.id = id;
		this.horario = horario;
	}
	
	public Turno(TTurno tTurno) {
		this.id = tTurno.getId();
		this.horario = tTurno.getHorario();
		this.activo = tTurno.getActivo();
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getHorario() {
		return horario;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {

		return activo;
	}

	public void setEmpleados(Collection<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Collection<Empleado> getEmpleados() {
		return empleados;
	}

	public TTurno toTransfer() {
		return new TTurno(id, horario, activo);
	}
}