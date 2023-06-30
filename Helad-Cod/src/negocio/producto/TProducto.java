package negocio.producto;

public class TProducto {
	private String nombre;
	private String sabor;
	
	private Boolean activo;
	private Integer idProveedor;
	
	private String tipo;
	private Integer id;
	private Float precio;
	private Integer stock;
	
	
	public TProducto(Integer id, String nombre, String sabor,  Boolean activo,  String tipo, Integer idProveedor, Float precio, Integer stock) {
		super();
		this.nombre = nombre;
		this.sabor = sabor;
		this.activo = activo;
		this.tipo = tipo;
		this.idProveedor =idProveedor;
		this.id =id;
		this.precio = precio;
		this.stock = stock;
	}
	public TProducto(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setSabor(String sabor) {
		this.sabor=sabor;
	}
	
	public String getSabor() {
		return this.sabor;
	}
	public Boolean getActivo() {
		return this.activo;
	}
	public void setActivo(Boolean activo) {
		this.activo=activo;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock= stock;
	}
}