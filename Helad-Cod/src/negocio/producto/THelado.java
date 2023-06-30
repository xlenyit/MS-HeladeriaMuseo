package negocio.producto;
public class THelado extends TProducto{
	private Integer id;
	private String envase;
	
	public THelado(Integer id_producto, String envase,String nombre, String sabor, Boolean activo, Integer idProveedor, Float precio, Integer stock) {
		super(id_producto, nombre, sabor, activo, "Helado", idProveedor, precio, stock);
		this.id = id_producto;
		this.envase = envase;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnvase() {
		return envase;
	}

	public void setEnvase(String envase) {
		this.envase = envase;
	}
	
	
}