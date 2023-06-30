package negocio.proveedor;

public class TProveedor {
	private Integer id;
	private String NIF;
	private String nombre;
	private String numCuenta;
	private boolean activo;
	
	
	public TProveedor(Integer id, String nIF, String nombre, String numCuenta, boolean activo) {
		super();
		this.id = id;
		this.activo=activo;
		NIF = nIF;
		this.nombre = nombre;
		this.numCuenta = numCuenta;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public String getNIF() {
		return this.NIF;
	}
	public void setNIF(String NIF) {
		this.NIF=NIF;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String Nombre) {
		this.nombre=Nombre;
	}
	public String getNumCuenta() {
		return this.numCuenta;
	}
	public void setNumCuenta(String NumCuenta) {
		this.numCuenta=NumCuenta;
	}

	public void setActivo(boolean activo) {

		this.activo = activo;
	}

	public boolean getActivo() {
		return activo;
	}
}