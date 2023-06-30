package integración.factura;

import java.util.Collection;

import negocio.factura.TLineaFactura;

public interface DAOLineaFactura {

	public int create(TLineaFactura tLineaFactura);

	public TLineaFactura read(int idActividad, int idFactura);

	public int update(TLineaFactura tLineaFactura);

	public int deleteFisico(int idFactura, int idActividad);

	public Collection<TLineaFactura> readByFactura(int idFactura);

	public Collection<TLineaFactura> readByActividad(int idActividad);
}