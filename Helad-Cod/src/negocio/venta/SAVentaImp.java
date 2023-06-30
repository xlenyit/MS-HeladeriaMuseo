package negocio.venta;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.producto.DAOProducto;
import integracion.trabajador.DAOTrabajador;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import integracion.venta.DAOVenta;
import negocio.producto.TProducto;
import negocio.trabajador.TTrabajador;
import presentacion.controlador.Evento;

public class SAVentaImp implements SAVenta {
	DAOVenta daoVenta = FactoriaIntegracion.getInstance().generarDAOVenta();
	DAOProducto daoProducto= FactoriaIntegracion.getInstance().generarDAOProducto();
	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generarDAOTrabajador();	
	public Integer altaVenta(TVenta tVenta) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans = tm.nuevaTransaccion();

		try
		{
			if( tVenta!= null){
				trans.start();
				TTrabajador trabajador = daoTrabajador.readById(tVenta.getTrabajador().getId());
				if (trabajador == null) {
					trans.rollback();
					return Evento.TRABAJADOR_NO_EXISTENTE;
				}
					
				if (!trabajador.getActive()) {
					trans.rollback();
					return Evento.TRABAJADOR_NO_ACTIVO;
				}
				for (TLineaVenta p : tVenta.getProductos()) {
					TProducto producto = daoProducto.readById(p.getProducto().getId());
					if (producto == null) {
						trans.rollback();
						return Evento.PRODUCTO_NO_EXISTENTE;
					}
					if(!producto.getActivo()) {
						trans.rollback();
						return Evento.PRODUCTO_NO_ACTIVO;
					}
					if (producto.getStock() >= p.getCantidad() && producto.getActivo()) {
						float precioTotal = 0; 
						precioTotal+= p.getCantidad() * producto.getPrecio();
						tVenta.setPrecioTotal(precioTotal);
					}
					else {
						trans.rollback();
						return Evento.NO_STOCK;
					}
				}
				for (TLineaVenta t : tVenta.getProductos()) 
					daoProducto.restarStock(t.getProducto().getId(), t.getCantidad());
				
				id = daoVenta.create(tVenta);
				if(id == -1) {
					
					trans.rollback();
					return Evento.ERROR_GENERICO;
				}else {
					trans.commit();
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
	public Integer devolucionVenta(Integer id) {
		int del=1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction trans = tm.nuevaTransaccion();
		
		try {
			trans.start();
			TVenta tVenta = daoVenta.readById(id);
			
			if (id > 0) {
				if (tVenta == null) {
					trans.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
					
				if (!tVenta.getActivo()) {
					trans.rollback();
					return Evento.YA_INACTIVO;
				}
				for (TLineaVenta t : tVenta.getProductos()) 
					daoProducto.sumarStock(t.getProducto().getId(), t.getCantidad());
				del = daoVenta.delete(id);
				trans.commit();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}     
        return del;
	}
	
	public TVenta mostrarVenta(Integer id) {
		Transaction trans = TransactionManager.getInstance().nuevaTransaccion();
		trans.start();
		TVenta venta=FactoriaIntegracion.getInstance().generarDAOVenta().readById(id);

		try {
			trans.commit();
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}

		return venta;
	}
	
	
}
