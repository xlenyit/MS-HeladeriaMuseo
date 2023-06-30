package negocio.producto;

import java.sql.Date;

public class TProductoConsumible extends TProducto {
	
	private Date tiempo_caducidad;
	
	public TProductoConsumible(int id, int idMarca, String nombre, int stock, double precio, Date tiempo_caducidad, boolean activo) {
		super(id, idMarca, nombre, stock, precio, activo);
		this.tiempo_caducidad = tiempo_caducidad;	
	}
	
	public TProductoConsumible(int id, int idMarca, String nombre, int stock, double precio, boolean activo) {
		super(id, idMarca, nombre, stock, precio, activo);
	}

	
	public Date getTiempoCaducidad() {
		return tiempo_caducidad;
	}
	
	public void setTiempoCaducidad(Date tiempo_caducidad) {
		this.tiempo_caducidad = tiempo_caducidad;
	}
	
	public String toString() {
		return super.toString() + "Tipo de Producto: Consumible\n" + "Fecha Caducidad : " + this.tiempo_caducidad + '\n' + "-----------------------------------\n"; 
	}


}
