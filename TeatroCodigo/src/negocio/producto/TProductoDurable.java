package negocio.producto;

public class TProductoDurable extends TProducto {
	
	public String tipo_durable;

	public TProductoDurable(int id, int idMarca, String nombre, int stock, double precio, String tipo_durable, boolean activo) {
		super(id, idMarca, nombre, stock, precio, activo);
		this.tipo_durable = tipo_durable;	
	}
	
	public TProductoDurable(int id, int idMarca, String nombre, int stock, double precio, boolean activo) {
		super(id, idMarca, nombre, stock, precio, activo);
	}
	
	public String getTipo() {
		return tipo_durable;
	}
	
	public void setTipo(String tipo_durable) {
		this.tipo_durable = tipo_durable;
	}
	
	public String toString() {
		return super.toString() + "Tipo de Producto: Durable\n" + "Tipo Durable: " + this.tipo_durable + '\n' + "-----------------------------------\n"; 
	}
	
}
