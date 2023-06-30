package integracion.factoriaIntegracion;

import integracion.ingrediente.DAOIngrediente;
import integracion.ingrediente.DAOIngredienteImp;
import integracion.producto.DAOProducto;
import integracion.producto.DAOProductoImp;
import integracion.proveedor.DAOProveedor;
import integracion.proveedor.DAOProveedorImp;
import integracion.seccion.DAOSeccion;
import integracion.seccion.DAOSeccionImp;
import integracion.trabajador.DAOTrabajador;
import integracion.trabajador.DAOTrabajadorImp;
import integracion.venta.DAOVenta;
import integracion.venta.DAOVentaImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {
	public DAOProducto generarDAOProducto() {
		return new DAOProductoImp();
	}
	public DAOProveedor generarDAOProveedor() {
		return new DAOProveedorImp();		
	}
	public DAOSeccion generarDAOSeccion() {
		
		return new DAOSeccionImp();
	}
	public DAOTrabajador generarDAOTrabajador() {
		return new DAOTrabajadorImp();
	}
	public DAOVenta generarDAOVenta() {
		return new DAOVentaImp();	
	}
	public DAOIngrediente generarDAOIngrediente() {
		return new DAOIngredienteImp();
	}
}