
package negocio.proveedor;

public class TLineaProveedorProducto {

	private int id_producto;
	private int id_proveedor;

	public TLineaProveedorProducto(int idProducto, int idProveedor) {
		this.id_producto = idProducto;
		this.id_proveedor = idProveedor;
	}

	public int getIdProducto() {
		return id_producto;
	}

	public int getIdProveedor() {
		return id_proveedor;
	}

	public void setIdProducto(int idProducto) {
		this.id_producto = idProducto;
	}

	public void setIdProveedor(int idProveedor) {
		this.id_proveedor = idProveedor;
	}

	public String toString() {
		return "ID Producto: " + id_producto + '\n' + "ID Proveedor: " + id_proveedor;
	}
}