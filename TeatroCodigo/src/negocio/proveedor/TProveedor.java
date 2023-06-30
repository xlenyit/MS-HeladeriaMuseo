
package negocio.proveedor;

public class TProveedor {

	private int id;
	private String NIF;
	private boolean activo;
	private String direccion;
	private String telefono;

	public TProveedor(int id, String NIF, String telefono, String direccion, boolean activo) {
		this.id = id;
		this.NIF = NIF;
		this.direccion = direccion;
		this.telefono = telefono;
		this.activo = activo;
	}

	public TProveedor(String NIF, String telefono, String direccion, boolean activo) {
		this.NIF = NIF;
		this.direccion = direccion;
		this.telefono = telefono;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public String toString() {
		return "Id: " + this.id + '\n' + "NIF: " + this.NIF + '\n' + "Telefono: " + this.telefono + '\n' + "Direccion: "
				+ this.direccion + '\n' + (this.activo ? "Activo" : "Inactivo") + '\n';
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}