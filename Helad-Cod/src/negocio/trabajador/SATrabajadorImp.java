package negocio.trabajador;

import java.util.Set;

import integracion.factoriaIntegracion.FactoriaIntegracion;
import integracion.query.FactoriaQuery;
import integracion.query.Query;
import integracion.seccion.DAOSeccion;
import integracion.trabajador.DAOTrabajador;
import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionManager;
import presentacion.controlador.Evento;
public class SATrabajadorImp implements SATrabajador {
	DAOTrabajador daoTrabajador = FactoriaIntegracion.getInstance().generarDAOTrabajador();
	DAOSeccion daoSeccion = FactoriaIntegracion.getInstance().generarDAOSeccion();
	
	public Integer altaTrabajador(TTrabajador tTrabajador) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		int id = -1;
		try {
			if( tTrabajador!= null){
				tran.start();
				if(daoTrabajador.readByDNI(tTrabajador.getDNI()) != null && daoTrabajador.readByDNI(tTrabajador.getDNI()).getActive()) {
					tran.rollback();
					return Evento.DNI_EXISTENTE;
				}
				if(daoSeccion.readById(tTrabajador.getIdSeccion()) == null) {
					tran.rollback();
					return Evento.SECCION_NO_EXISTENTE;
				}
				if(!daoSeccion.readById(tTrabajador.getIdSeccion()).getActivo()) {
					tran.rollback();
					return Evento.SECCION_NO_ACTIVA;
				}
				if(daoTrabajador.readByName(tTrabajador.getNombre()) == null) {
					id = daoTrabajador.create(tTrabajador);
					tran.commit();
				} else {
					TTrabajador aux = daoTrabajador.readByName(tTrabajador.getNombre());
					id = aux.getId();
					aux.setActive(true);
					daoTrabajador.update(aux);
					tran.commit();
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public Integer bajaTrabajador(Integer id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		int up = -1;
		try {
			if(id > 0){
				tran.start();
				if (daoTrabajador.readById(id) == null) {
					tran.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				if (!daoTrabajador.readById(id).getActive()) {
					tran.rollback();
					return Evento.YA_INACTIVO;
				}
				up = daoTrabajador.delete(id);
				tran.commit();
			} else {
				tran.rollback();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return up;
	}
	
	public TTrabajador mostrarTrabajador(Integer id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		TTrabajador t = null;
		try {
			tran.start();
			t = daoTrabajador.readById(id);
			tran.commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return t;
	}
	
	public Set<TTrabajador> listarTrabajador() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		Set<TTrabajador> lista = null;
		try {
			tran.start();
			lista = daoTrabajador.readAll();
			tran.commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public Integer actualizarTrabajador(TTrabajador tTrabajador) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		Integer upd = -1;
		try {
			if(tTrabajador != null) {
				tran.start();
				if(daoTrabajador.readById(tTrabajador.getId()) == null) {
					tran.rollback();
					return Evento.ID_NO_EXISTENTE;
				}
				if(daoTrabajador.readByDNI(tTrabajador.getDNI()) != null) {
					tran.rollback();
					return Evento.DNI_EXISTENTE;
				}
				if(daoSeccion.readById(tTrabajador.getIdSeccion()) == null) {
					tran.rollback();
					return Evento.SECCION_NO_EXISTENTE;
				}
				if(!daoSeccion.readById(tTrabajador.getIdSeccion()).getActivo()) {
					tran.rollback();
					return Evento.SECCION_NO_ACTIVA;
				}
				
				upd = daoTrabajador.update(tTrabajador);
				tran.commit();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return upd;
	}

	@Override
	public Set<TTrabajador> listarTrabajadorPorSeccion(Integer id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch(Exception e) {
			tran = tm.getTransaccion();
		}
		Set<TTrabajador> lista = null;
		try {
			tran.start();
			lista = daoTrabajador.readBySeccion(id);
			tran.commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	@Override
	public TTrabajador trabajadorConMasVentas(String ini, String fin) {
		FactoriaQuery Factory = FactoriaQuery.getInstance();
		Query query = Factory.getQuery("trabajadorConMasVentas");
		TransactionManager tm = TransactionManager.getInstance();
		Transaction tran;
		try {
			tran = tm.nuevaTransaccion();
		} catch (Exception e) {
			tran = tm.getTransaccion();
		}
		TTrabajador tTrabajador = null;
		try {
			tran.start();
			tTrabajador = (TTrabajador) query.execute(ini,fin);
			tran.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tTrabajador;
	}
}