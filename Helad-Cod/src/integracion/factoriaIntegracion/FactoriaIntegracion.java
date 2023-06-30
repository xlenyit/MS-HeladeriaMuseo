package integracion.factoriaIntegracion;

import integracion.ingrediente.DAOIngrediente;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.seccion.DAOSeccion;
import integracion.trabajador.DAOTrabajador;
import integracion.venta.DAOVenta;

public abstract class FactoriaIntegracion {
	private static FactoriaIntegracion instancia;
	public abstract DAOProducto generarDAOProducto();
	public abstract DAOProveedor generarDAOProveedor();
	public abstract DAOSeccion generarDAOSeccion();
	public abstract DAOTrabajador generarDAOTrabajador();
	public abstract DAOVenta generarDAOVenta();
	public abstract DAOIngrediente generarDAOIngrediente();
	
	public static synchronized FactoriaIntegracion getInstance() {
			
		if (instancia == null) instancia = new FactoriaIntegracionImp();
		return instancia;
	}
}