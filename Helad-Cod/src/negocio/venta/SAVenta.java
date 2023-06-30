package negocio.venta;

public interface SAVenta {
	public Integer altaVenta(TVenta Venta);
	public Integer devolucionVenta(Integer Id);
	public TVenta mostrarVenta(Integer Id);
	
}
