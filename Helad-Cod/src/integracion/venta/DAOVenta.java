package integracion.venta;

import negocio.venta.TVenta;

public interface DAOVenta {
	
	public Integer create(TVenta Venta);
	
	public TVenta readById(Integer Id);
	
		
	public Integer delete(Integer Id);
	
	
}
