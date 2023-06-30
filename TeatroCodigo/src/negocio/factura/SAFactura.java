package negocio.factura;

import java.util.Collection;

public interface SAFactura {

	public int create(TOAFacturaConActividad tFacturaConActividad);

	public TFactura read(int id);

	public int update(TOAFacturaConActividad tFacturaConActividad);

	public int delete(int id);

	public int deleteFisico(int id);

	public Collection<TFactura> readAll();

	public Collection<TFactura> readByCliente(int id);
}