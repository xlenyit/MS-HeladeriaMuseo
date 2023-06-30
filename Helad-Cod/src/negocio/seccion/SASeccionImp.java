package negocio.seccion;

import java.util.Set;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.seccion.DAOSeccion;
import integracion.trabajador.DAOTrabajador;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import negocio.trabajador.TTrabajador;
import presentacion.controlador.Evento;

public class SASeccionImp implements SASeccion {
	DAOSeccion daoSeccion = FactoriaIntegracion.getInstance().generarDAOSeccion();
	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generarDAOTrabajador();
	
	public Integer altaSeccion(TSeccion tSeccion) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		try {
			if(tSeccion!= null) {
				tran.start();
				if(daoSeccion.readByNombre(tSeccion.getNombre()) == null) {
					id = daoSeccion.create(tSeccion);
					tran.commit();
				}
				else if(daoSeccion.readByNombre(tSeccion.getNombre()).getActivo()) {
					tran.rollback();
					return Evento.NOMBRE_EXISTENTE;
				}
				else {
					TSeccion aux = daoSeccion.readByNombre(tSeccion.getNombre());
					id = aux.getId();
					aux.setActivo(true);
					daoSeccion.update(aux);
					tran.commit();					
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return id;
	}	
			
	public TSeccion mostrarSeccion(Integer Id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		TSeccion t = null;
		try {
			tran.start();
			t = daoSeccion.readById(Id);
			tran.commit();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
        return t;
}
	
	public Integer bajaSeccion(Integer id) {
		int del=-1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		try {
			if (id > 0){
				tran.start();
				if(daoSeccion.readById(id)== null) {
					tran.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				if(!daoSeccion.readById(id).getActivo()) {
					tran.rollback();
					return Evento.YA_INACTIVO;
				}
				for(TTrabajador t: daoTrabajador.readAll()) {
					if(t.getIdSeccion()==id && t.getActive()) {
						tran.rollback();
						return Evento.SECCION_CON_TRABAJADOR;
					}
				}
				del = daoSeccion.delete(id);
				tran.commit();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return del;
	}
	
	public Set<TSeccion> listarSecciones() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		Set<TSeccion> lista = null;
		try {
			tran.start();
			lista = daoSeccion.readAll();
			tran.commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return lista;
}
	
	public Integer actualizarSeccion(TSeccion tSeccion) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		Integer mod=-1;
		try {
	        if(tSeccion !=null){
	        	tran.start();	
	        	if(daoSeccion.readById(tSeccion.getId())== null) {
	        		tran.rollback();
	        		return Evento.ID_NO_EXISTENTE;
	        	}
				if(daoSeccion.readByNombre(tSeccion.getNombre()) != null && daoSeccion.readByNombre(tSeccion.getNombre()).getActivo()) {
					tran.rollback();
					return Evento.NOMBRE_EXISTENTE;
				}
				mod = daoSeccion.update(tSeccion);
				tran.commit();
	        }	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return mod;
    }
}