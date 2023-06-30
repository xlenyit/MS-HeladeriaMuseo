package negocio.producto;

public class TBatido extends TProducto {
	private Integer id;
	private String tamanio;
	
	public TBatido(Integer id_producto, String tamanio,String nombre, String sabor, Boolean activo, Integer idProveedor, Float precio, Integer stock) {
		super(id_producto,nombre, sabor, activo, "Batido", idProveedor, precio, stock);
		this.id = id_producto;
		this.tamanio = tamanio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	
}