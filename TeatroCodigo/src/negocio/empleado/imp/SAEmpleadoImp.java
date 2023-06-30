
package negocio.empleado.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integración.factoriaEntityManager.FactoriaEntityManagerSingleton;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TEmpleadoTiempoCompleto;
import negocio.empleado.TEmpleadoTiempoParcial;
import negocio.empleado.entidad.Empleado;
import negocio.empleado.entidad.EmpleadoTiempoCompleto;
import negocio.empleado.entidad.EmpleadoTiempoParcial;
import negocio.turno.entidad.Turno;

public class SAEmpleadoImp implements SAEmpleado {

	public int alta(TEmpleado tEmpleado) {
		int id = -1;
		boolean is_completo = tEmpleado instanceof TEmpleadoTiempoCompleto;
		
		if (tEmpleado.getId_turno() < 0) {
			return id;
		}
		if (tEmpleado.getNif().length() != 9){
			return id;
		}
		
		if (is_completo) {
			if (((TEmpleadoTiempoCompleto) tEmpleado).getBase() < 0) {
				return id;
			}
			if (((TEmpleadoTiempoCompleto) tEmpleado).getComplemento() < 0) {
				return id;
			}
		} else {
			if (((TEmpleadoTiempoParcial) tEmpleado).getHoras() < 0) {
				return id;
			}
			if (((TEmpleadoTiempoParcial) tEmpleado).getSueldoPorHora() < 0) {
				return id;
			}
		}

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		
		try {
			
			em.getTransaction().begin();
			
			Turno aux = em.find(Turno.class, tEmpleado.getId_turno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (aux == null || !aux.getActivo()){
				id = -1;
				em.getTransaction().rollback();
			} else {
				Empleado empleado;
				if (is_completo){
					empleado = new EmpleadoTiempoCompleto((TEmpleadoTiempoCompleto) tEmpleado);
				} else {
					empleado = new EmpleadoTiempoParcial((TEmpleadoTiempoParcial) tEmpleado);
				}
				empleado.setTurno(aux);
				
				TypedQuery<Empleado> tq = em.createNamedQuery("Empleado.findByNif", Empleado.class);
				tq.setParameter("nif", tEmpleado.getNif());
				List<Empleado> leido = tq.getResultList();

				if (leido.isEmpty()) {
					em.persist(empleado);
					em.flush();
					id = empleado.getId();
					em.getTransaction().commit();
				} else if (!leido.get(0).isActivo()) {
					empleado = leido.get(0);
					empleado.setActivo(true);
					empleado.setNif(empleado.getNif());
				
					if (is_completo) {
						((EmpleadoTiempoCompleto) empleado).setBase(((TEmpleadoTiempoCompleto) tEmpleado).getBase());
						((EmpleadoTiempoCompleto) empleado).setComplemento(((TEmpleadoTiempoCompleto) tEmpleado).getComplemento());
					} else {
						((EmpleadoTiempoParcial) empleado).setSueldoPorHora(((TEmpleadoTiempoParcial) tEmpleado).getSueldoPorHora());
						((EmpleadoTiempoParcial) empleado).setHoras((int) ((TEmpleadoTiempoParcial) tEmpleado).getHoras());
					}
					id = empleado.getId();
					em.getTransaction().commit();
				} else {
					em.getTransaction().rollback();
				}
			}
			
			
			
		} catch (Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			id = -1;
		} finally {
			em.close();
		}

		
		return id; 
	}

	public int eliminar(int id) {
		int res = -1;
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try	{
			em.getTransaction().begin();
			
			Empleado leido = em.find(Empleado.class, id, LockModeType.OPTIMISTIC);
			
			if(leido != null && leido.isActivo()) {
				leido.setActivo(false);
				res = leido.getId();
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
			
		}
		catch(Exception exception) {	
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			res = -1;
		}
		finally	{
			em.close();
		}
		return res;
	}

	public TEmpleado buscar(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		TEmpleado res = null;
		try {
			
			em.getTransaction().begin();

			
			Empleado aux = em.find(Empleado.class, id, LockModeType.OPTIMISTIC);
			
			if (aux != null) {
				res = aux.toTransfer();
			}
			
			em.getTransaction().commit();
			
		} catch(Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			res = null;
		}
		finally	{
			em.close();
		}

		return res;
	}

	public int modificar(TEmpleado tEmpleado) {
		int id = -1;
		boolean is_completo = tEmpleado instanceof TEmpleadoTiempoCompleto;

		if (tEmpleado.getId_turno() < 0) {
			return id;
		}
		if (tEmpleado.getNif().length() != 9){
			return id;
		}
		
		if (is_completo) {
			if (((TEmpleadoTiempoCompleto) tEmpleado).getBase() < 0) {
				return id;
			}
			if (((TEmpleadoTiempoCompleto) tEmpleado).getComplemento() < 0) {
				return id;
			}
		} else {
			if (((TEmpleadoTiempoParcial) tEmpleado).getHoras() < 0) {
				return id;
			}
			if (((TEmpleadoTiempoParcial) tEmpleado).getSueldoPorHora() < 0) {
				return id;
			}
		}
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			
			Turno aux = em.find(Turno.class, tEmpleado.getId_turno(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (aux == null || !aux.getActivo()){
				id = -1;
				em.getTransaction().rollback();
			} else {
				
				Empleado empleado;
				
				if (is_completo){
					empleado = new EmpleadoTiempoCompleto((TEmpleadoTiempoCompleto) tEmpleado);
				} else {
					empleado = new EmpleadoTiempoParcial((TEmpleadoTiempoParcial) tEmpleado);
				}
				empleado.setTurno(aux);
				
				Empleado res = em.find(Empleado.class, tEmpleado.getId());
				
				TypedQuery<Empleado> tq = em.createNamedQuery("Empleado.findByNif", Empleado.class);
				tq.setParameter("nif", tEmpleado.getNif());
				List<Empleado> leido = tq.getResultList();

				if (leido.isEmpty() || res == null || !res.isActivo()) {
					id = -1;
					em.getTransaction().rollback();
				} else {
					empleado = res;
					empleado.setActivo(true);
					empleado.setNif(empleado.getNif());
					empleado.setTurno(aux);
					if (is_completo) {
						((EmpleadoTiempoCompleto) empleado).setBase(((TEmpleadoTiempoCompleto) tEmpleado).getBase());
						((EmpleadoTiempoCompleto) empleado).setComplemento(((TEmpleadoTiempoCompleto) tEmpleado).getComplemento());
					} else {
						((EmpleadoTiempoParcial) empleado).setSueldoPorHora(((TEmpleadoTiempoParcial) tEmpleado).getSueldoPorHora());
						((EmpleadoTiempoParcial) empleado).setHoras((int) ((TEmpleadoTiempoParcial) tEmpleado).getHoras());
					}
					id = empleado.getId();
					em.getTransaction().commit();
				}
			}
			
			
		} catch(Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			id = -1;
		}
		finally
		{
			em.close();
		}
		
		return id;
	}

	public Collection<TEmpleado> mostrar() {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		List<TEmpleado> resultado = new ArrayList<TEmpleado>();
		try {
			em.getTransaction().begin();
			TypedQuery<Empleado> tq = em.createNamedQuery("Empleado.findAll", Empleado.class);
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Empleado> leido = tq.getResultList();
			
			for (Empleado t : leido)
				resultado.add(t.toTransfer());
			
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			resultado = new ArrayList<TEmpleado>();
		} finally {
			em.close();
		}
		
		return resultado;
	}

	public Collection<TEmpleado> mostrarPorTurno(int id_turno) {
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		List<TEmpleado> resultado = new ArrayList<TEmpleado>();
		
		try {
			
			em.getTransaction().begin();
			
			Turno aux = em.find(Turno.class, id_turno, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (aux == null){
				em.getTransaction().rollback();
			} else {
				TypedQuery<Empleado> tq = em.createNamedQuery("Empleado.findByTurno", Empleado.class);
				tq.setParameter("turno", aux);
				//tq.setLockMode(LockModeType.OPTIMISTIC);
				List<Empleado> leido = tq.getResultList();
				
				for (Empleado t : leido)
					resultado.add(t.toTransfer());
				
				em.getTransaction().commit();
			}
			
		} catch (Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			resultado = new ArrayList<TEmpleado>();
		} finally {
			em.close();
		}
		
		return resultado;
	}
	
	public void bajaFisica(int id){
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try
		{
			em.getTransaction().begin();
			
			Empleado empleado = em.find(Empleado.class, id);
			
			if(empleado != null)
			{
				em.remove(empleado);
			}
		
			em.getTransaction().commit();
		}
		catch(Exception exception)
		{	
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		finally
		{
			em.close();
		}
	}
}