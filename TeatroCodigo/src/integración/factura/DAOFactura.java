package integración.factura;

import java.util.Collection;

import negocio.factura.TFactura;

public interface DAOFactura {

	public int create(TFactura tFactura);

	public int delete(int id);

	public int deleteFisico(int id);

	public TFactura read(int id);

	public Collection<TFactura> readAll();

	public int update(TFactura tFactura);

	public Collection<TFactura> readByCliente(int id);
}