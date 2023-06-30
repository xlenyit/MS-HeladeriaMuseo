package negocio.ingrediente;

public class TIngrediente {

	private String nombre;
	private Integer cantidad;
	private String codigo;
	private String tipo;
	private Integer id;
	private Integer idProducto;
	private Boolean activo;

	public TIngrediente(String nombre, Integer cantidad, String codigo, String tipo, Boolean activo, Integer id, Integer idProducto) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.codigo = codigo;
		this.tipo = tipo;
		this.activo = activo;
		this.id = id;
		this.idProducto=idProducto;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public TIngrediente(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

}