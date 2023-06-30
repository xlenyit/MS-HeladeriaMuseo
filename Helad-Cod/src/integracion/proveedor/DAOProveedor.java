package integracion.proveedor;

import java.util.Set;

import negocio.proveedor.TProveedor;
public interface DAOProveedor {
	public Integer create(TProveedor tProveedor);
	public Integer delete(Integer Id);
	public Integer update(TProveedor Proveedor);
	public TProveedor readById(Integer id);
	public Set<TProveedor> readAll();
	public TProveedor readByNIF(String NIF);
}