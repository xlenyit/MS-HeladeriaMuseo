package negocio.proveedor;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.producto.TProducto;
import presentacion.controlador.Evento;

import java.util.Set;

public class SAProveedorImp implements SAProveedor {
	DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generarDAOProveedor();
	DAOProducto daoProducto= FactoriaIntegracion.getInstance().generarDAOProducto();
	
	public Integer altaProveedor(TProveedor tProveedor) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		try {
			if(tProveedor!= null) {
				tran.start();
				if(daoProveedor.readByNIF(tProveedor.getNIF())==null) {
					id = daoProveedor.create(tProveedor);
					tran.commit();
				}
				else if(daoProveedor.readByNIF(tProveedor.getNIF()).getActivo()) {
					tran.rollback();
					return Evento.NIF_EXISTENTE;
				}
				else {
					TProveedor aux = daoProveedor.readByNIF(tProveedor.getNIF());
					id = aux.getId();
					aux.setActivo(true);
					daoProveedor.update(aux);
					tran.commit();	
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public Integer bajaProveedor(Integer Id) {
		int del=-1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
     try {
    	 if (Id > 0){
				tran.start();
				if(daoProveedor.readById(Id) == null) {
					tran.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				if(!daoProveedor.readById(Id).getActivo()) {
					tran.rollback();
					return Evento.YA_INACTIVO;
				}
				for(TProducto t: daoProducto.readAll()) {
					if(t.getIdProveedor()==Id && t.getActivo()) {
						tran.rollback();
						return Evento.PROVEEDOR_CON_PRODUCTO;
					}
				}
				del = daoProveedor.delete(Id);
				tran.commit();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
     return del;
	}
	
	public TProveedor mostrarProveedor(Integer id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		TProveedor t = null;
		try {
			tran.start();
			t = daoProveedor.readById(id);
			tran.commit();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
        return t;
	}
	
	public Integer actualizarProveedor(TProveedor tProveedor) {
		Integer mod = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		try {
	        if(tProveedor !=null){
	        	tran.start();	
	        	if(daoProveedor.readById(tProveedor.getId())== null) {
	        		tran.rollback();
	        		return Evento.ID_NO_EXISTENTE;
	        	}
				if(daoProveedor.readByNIF(tProveedor.getNIF()) != null && daoProveedor.readByNIF(tProveedor.getNIF()).getActivo()) {
					tran.rollback();
					return Evento.NIF_EXISTENTE;
				}
				mod = daoProveedor.update(tProveedor);
				tran.commit();
	        }	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return mod;	
	}
	
	public Set<TProveedor> listarProveedor() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		Set<TProveedor> lista = null;
		try {
			tran.start();
			lista = daoProveedor.readAll();
			tran.commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return lista;
	}
}