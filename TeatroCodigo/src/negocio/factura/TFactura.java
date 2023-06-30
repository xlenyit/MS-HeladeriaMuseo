package negocio.factura;

public class TFactura {

	private int id;

	private boolean activo;

	private double precioTotal;

	private int numActividades;

	private int idCliente;

	public TFactura(int id, double precioTotal, int numActividades, int idCliente) {
		this.id = id;
		this.precioTotal = precioTotal;
		this.numActividades = numActividades;
		this.idCliente = idCliente;
		this.activo = true;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public double getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(double precio) {
		this.precioTotal = precio;
	}

	public int getNumActividades() {
		return this.numActividades;
	}

	public void setNumActividades(int numActividades) {
		this.numActividades = numActividades;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String toString() {
		return "Id: " + this.id + '\n' + "Cliente: " + this.idCliente + '\n' + "Número de actividades: "
				+ this.numActividades + '\n' + "Importe total: " + this.precioTotal + '\n' + '\n';
	}

}