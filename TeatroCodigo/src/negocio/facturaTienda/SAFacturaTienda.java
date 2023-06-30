package negocio.facturaTienda;

import negocio.facturaTienda.imp.TFacturaConProducto;
import java.util.Collection;
import negocio.facturaTienda.imp.TLineaFacturaTienda;

public interface SAFacturaTienda {
	
	public int cerrarVenta(TFacturaConProducto tFacturaConProducto);

	public TFacturaConProducto buscar(int id);

	public int modificar(TFacturaTienda tFactura);

	public Collection<TFacturaConProducto> mostrar();

	public Collection<TFacturaConProducto> buscarPorEmpleado(int idEmpleado);

	public int devolverProducto(TLineaFacturaTienda tLineaFactura);
	
	public void bajaFisica(int id);
}