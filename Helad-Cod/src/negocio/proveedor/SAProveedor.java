package negocio.proveedor;

import java.util.Set;

public interface SAProveedor {
	public Integer altaProveedor(TProveedor Proveedor);
	public Integer bajaProveedor(Integer Id);
	public TProveedor mostrarProveedor(Integer Id);
	public Integer actualizarProveedor(TProveedor Proveedor);
	public Set<TProveedor> listarProveedor();
}