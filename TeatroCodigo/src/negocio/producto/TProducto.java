package negocio.producto;

public class TProducto {
	private int id;
	private int idMarca;
	private String nombre;
	private int stock;
	private double precio;
	private boolean activo;


	public TProducto(int id, int idMarca, String nombre, int stock, double precio, boolean activo) {
		this.id = id;
		this.idMarca = idMarca;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdMarca() {
		return idMarca;
	}
	
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return activo;
	}
	
	public String toString() {
		return "ID: " + this.id + '\n' + "ID Marca: " + this.idMarca + '\n' + "Nombre: " + this.nombre + '\n' +"Precio: " + this.precio + '\n' + "Stock: " + this.stock + '\n' + (this.activo ? "Activo" : "Inactivo") + '\n'; 
	}

}