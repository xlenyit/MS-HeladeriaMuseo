package negocio.producto;

import java.util.ArrayList;
import java.util.List;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.producto.DAOProducto;
import integracion.proveedor.DAOProveedor;
import integracion.query.FactoriaQuery;
import integracion.query.Query;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.proveedor.TProveedor;
import presentacion.controlador.Evento;

public class SAProductoImp implements SAProducto {
	DAOProducto daoProducto = FactoriaIntegracion.getInstance().generarDAOProducto();
	DAOProveedor daoProveedor = FactoriaIntegracion.getInstance().generarDAOProveedor();
	TProducto tProducto;
	
	public Integer altaProducto(TProducto tProducto) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		
		TProveedor proveedor = daoProveedor.readById(tProducto.getIdProveedor());
		TProducto aux = daoProducto.readByNombre(tProducto.getNombre());
		
		try {
				if(tProducto != null){
					
						tran.start();
						if( proveedor == null){
							tran.rollback();
							return Evento.PROVEEDOR_NO_EXISTENTE;
						}
						
						if(!proveedor.getActivo()){
							tran.rollback();
							return Evento.PROVEEDOR_NO_ACTIVO;
						}
						
						if(aux == null){ 
							id = daoProducto.create(tProducto);
							tran.commit();
						}
						else{
							aux.setActivo(true);
							daoProducto.update(aux); // Reactivacion de la actualizacion
							tran.commit();
						}
						
				}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public TProducto mostrarProducto(Integer Id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		
		TProducto t = null;

		try{
			tran.start();
			t = daoProducto.readById(Id);
			tran.commit();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return t;
	}
	
	public Integer bajaProducto(Integer Id) {
		int del = 1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		
		TProducto prodAux=daoProducto.readById(Id);

		try{
			
			if (Id > 0){
				
				tran.start();
				
				if( prodAux == null){
					tran.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				
				if(!prodAux.getActivo()){
					tran.rollback();
					return Evento.YA_INACTIVO;
				}
				else{
					del = daoProducto.delete(Id);
					tran.commit();
				}
					
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
		return del;
	}
	
	public List<TProducto> listarProductos() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		
		List<TProducto> lista=null;

		try {
			tran.start();
			lista = daoProducto.readAll();
			tran.commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return lista;
	
	}
	
	public Integer actualizarProducto(TProducto tProducto) {
		int mod = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		
		TProducto prodAuxId=daoProducto.readById(tProducto.getId());
		TProducto prodAuxNombre=daoProducto.readByNombre(tProducto.getNombre());
		TProveedor proveedor=daoProveedor.readById(tProducto.getIdProveedor());
		
		try{
			
			if (tProducto != null) { 
				
				tran.start();
				if(prodAuxId == null){ 
					tran.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				
				if(prodAuxNombre!= null && prodAuxNombre.getActivo()){
					tran.rollback();
					return Evento.NOMBRE_EXISTENTE;
				}
				
				if( proveedor == null){
					tran.rollback();
					return Evento.PROVEEDOR_NO_EXISTENTE;
				}
				
				if(!proveedor.getActivo()){
					tran.rollback();
					return Evento.PROVEEDOR_NO_ACTIVO;
				}
				
				if(prodAuxId.getTipo() != tProducto.getTipo()){
					tran.rollback();
					return Evento.TIPO_EXISTENTE;
				}
				
				mod = daoProducto.update(tProducto);
				tran.commit();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
		return mod;
	}

	public List<TProducto> listarProductosPorProveedor(Integer Id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;

		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		
		List<TProducto> lista=null;

		try{
			tran.start();
			lista=daoProducto.readByProveedor(Id);
			tran.commit();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
		
	}

	@Override
	public ArrayList<TProducto> productoMasVendido(String ini, String fin) {
		FactoriaQuery Factory = FactoriaQuery.getInstance();
		Query query = Factory.getQuery("productoMasVendido");
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;

		try {
			tran = tm.nuevaTransaccion();
		} catch (Exception e) {
			tran = tm.getTransaccion();
		}
		
		ArrayList<TProducto> listaProductos = new ArrayList<>();

		try {
			tran.start();
			listaProductos = (ArrayList<TProducto>) query.execute(ini,fin);
			tran.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listaProductos;
	}
}