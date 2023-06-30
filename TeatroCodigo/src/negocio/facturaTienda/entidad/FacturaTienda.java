package negocio.facturaTienda.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.sql.Date;
import negocio.empleado.entidad.Empleado;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.OneToMany;
import javax.persistence.Version;

import negocio.facturaTienda.TFacturaTienda;

@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findByid", query = "select obj from FacturaTienda obj where :id = obj.id "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findBynProductos", query = "select obj from FacturaTienda obj where :nProductos = obj.nProductos "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findByfecha", query = "select obj from FacturaTienda obj where :fecha = obj.fecha "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findByprecioTot", query = "select obj from FacturaTienda obj where :precioTot = obj.precioTot "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findByactivo", query = "select obj from FacturaTienda obj where :activo = obj.activo "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findByempleado", query = "select obj from FacturaTienda obj where :empleado = obj.empleado "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findBylineaFacturaTienda", query = "select obj from FacturaTienda obj where :lineaFacturaTienda MEMBER OF obj.lineaFacturaTienda "),
		@NamedQuery(name = "negocio.facturaTienda.entidad.FacturaTienda.findByversion", query = "select obj from FacturaTienda obj where :version = obj.version "),
		@NamedQuery(name = "FacturaTienda.findAll", query = "select obj from FacturaTienda obj") })
public class FacturaTienda implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private int nProductos;
	private Date fecha;
	private double precioTot;
	private boolean activo;

	@ManyToOne
	private Empleado empleado;
	
	
	@OneToMany(mappedBy = "facturaTienda", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Collection<LineaGuia> lineaFacturaTienda;

	@Version
	private int version;

	public FacturaTienda(int id, int nProductos, Date fecha, double precioTot) {
		this.id = id;
		this.nProductos = nProductos;
		this.fecha = fecha;
		this.precioTot = precioTot;
	}

	public FacturaTienda(int nProductos, Date fecha, double precioTot) {
		this.nProductos = nProductos;
		this.fecha = fecha;
		this.precioTot = precioTot;
	}

	public FacturaTienda(TFacturaTienda tFacturaTienda) {
		//this.id = tFacturaTienda.getId();
		this.nProductos = tFacturaTienda.getNumProductos();
		this.fecha = tFacturaTienda.getFecha();
		this.precioTot = tFacturaTienda.getPrecioTotal();
		this.activo=tFacturaTienda.getActivo();
	}

	public FacturaTienda() {

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setNProductos(int nProductos) {
		this.nProductos = nProductos;
	}

	public int getNProductos() {
		return nProductos;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setPrecioTot(double precioTot) {
		this.precioTot = precioTot;
	}

	public double getPrecioTot() {
		return precioTot;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setLineaFactura(Collection<LineaGuia> lineaFactura) {
		lineaFacturaTienda = lineaFactura;
	}

	public Collection<LineaGuia> getLineaFactura() {
		return this.lineaFacturaTienda;
	}

	public TFacturaTienda toTransfer() {
		return new TFacturaTienda(id, precioTot, nProductos, fecha, this.getEmpleado().getId(), activo);
	}

	public String toString() {
		String r = "Id: " + this.id + '\n' + "Empleado: " + this.empleado.toString() + '\n' + "N�mero de productos: "
				+ this.nProductos + '\n' + "Importe total: " + this.precioTot + '\n' + "Fecha: " + this.fecha + '\n'
				+ '\n';
		Iterator<LineaGuia> it = lineaFacturaTienda.iterator();
		while (it.hasNext()) {
			r = r + it.next().toString() + '\n';
		}
		return r;
	}
}