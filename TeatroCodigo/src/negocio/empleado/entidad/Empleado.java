
package negocio.empleado.entidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import negocio.turno.entidad.Turno;
import javax.persistence.ManyToOne;

import java.util.Collection;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import negocio.facturaTienda.entidad.FacturaTienda;
import negocio.empleado.TEmpleado;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nif") })
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPO_EMPLEADO")
@NamedQueries({
		@NamedQuery(name = "Empleado.findById", query = "select obj from Empleado obj where :id = obj.id "),
		@NamedQuery(name = "Empleado.findByVersion", query = "select obj from Empleado obj where :version = obj.version "),
		@NamedQuery(name = "Empleado.findByNif", query = "select obj from Empleado obj where :nif = obj.nif "),
		@NamedQuery(name = "Empleado.findByTurno", query = "select obj from Empleado obj where :turno = obj.turno "),
		@NamedQuery(name = "Empleado.findByActivo", query = "select obj from Empleado obj where :activo = obj.activo "),
		@NamedQuery(name = "Empleado.findAll", query = "select obj from Empleado obj"),
		@NamedQuery(name = "Empleado.findByFacturaTienda", query = "select obj from Empleado obj where :facturaTienda MEMBER OF obj.facturaTienda ")})

public abstract class Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Version
	private int version;

	private String nif;

	//private double bonus;

	@ManyToOne
	private Turno turno;

	private boolean activo;
	
	@OneToMany(mappedBy = "empleado")
	private Collection<FacturaTienda> facturaTienda;

	public Empleado(int id, String nif, boolean activo, int id_turno) {
		//turno = ;
		this.nif = nif;
		this.activo = activo;
		this.id = id;
	}
	
	public Empleado (TEmpleado e) {
		//bonus = e.getBonus();
		id = e.getId();
		//turno = ;
		nif = e.getNif();
		activo = e.isActivo();
	}

	public Empleado(String nif, boolean activo, int id_turno) {
		//turno = ;
		this.nif = nif;
		this.activo = activo;
	}

	public Empleado() {
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Collection<FacturaTienda> getFacturaTienda() {
		return facturaTienda;
	}

	public void setFacturaTienda(Collection<FacturaTienda> facturaTienda) {
		this.facturaTienda = facturaTienda;
	}

	public abstract int calcularSueldo();
	public abstract TEmpleado toTransfer();
}