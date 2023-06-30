
package negocio.proveedor;

import java.util.Collection;

public interface SAProveedor {

	public int alta(TProveedor tproveedor);

	public TProveedor buscar(int id);

	public int eliminar(int id);

	public int modificar(TProveedor tproveedor);

	public Collection<TProveedor> readAll();

	public Collection<TProveedor> mostrarProvPorProd(int id);

	public TLineaProveedorProducto asignarProveedorAProducto(TLineaProveedorProducto tlinea);

	public TLineaProveedorProducto desasignarProveedorDeProducto(TLineaProveedorProducto tlinea);
	
	public void bajaFisica(int id);

}