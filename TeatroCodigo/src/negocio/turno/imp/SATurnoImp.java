package negocio.turno.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integración.factoriaEntityManager.FactoriaEntityManagerSingleton;
import negocio.empleado.entidad.Empleado;
import negocio.turno.SATurno;
import negocio.turno.TTurno;
import negocio.turno.entidad.Turno;

public class SATurnoImp implements SATurno {

	@Override
	public int alta(TTurno tTurno) {
		int id = -1;
		
		if(tTurno.getHorario().equals(""))
			return id;
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try
		{
			em.getTransaction().begin();
			
			Turno turno = new Turno(tTurno);
			
			TypedQuery<Turno> tq = em.createNamedQuery("Turno.findByHorario", Turno.class);
			tq.setParameter("horario", turno.getHorario());
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Turno> leido =tq.getResultList();
			
			if(leido.isEmpty())
			{
				em.persist(turno);
				em.flush();
				
				id = turno.getId();
			}
			else if(!leido.get(0).getActivo())
			{
				turno = leido.get(0);
				turno.setActivo(true);
				
				id = turno.getId();
			}
			
			if(id > 0)
				em.getTransaction().commit();
			else
				em.getTransaction().rollback();
		}
		catch(Exception exception)
		{
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

	@Override
	public TTurno buscar(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		TTurno res = null;
		
		try
		{
			em.getTransaction().begin();
				
			Turno turno = em.find(Turno.class, id, LockModeType.OPTIMISTIC);
			
			if(turno != null)
				res = turno.toTransfer();
			
			em.getTransaction().commit();
		}
		catch(Exception exception)
		{
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			res = null;
		}
		finally
		{
			em.close();
		}
		
		return res;
	}

	@Override
	public int modificar(TTurno tTurno) {
		int id = tTurno.getId();
		
		if(id <= 0)
			return -1;
		
		if(tTurno.getHorario().equals(""))
			return -1;
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try
		{
			em.getTransaction().begin();
			Turno leido = em.find(Turno.class, id);
			
			TypedQuery<Turno> tq = em.createNamedQuery("Turno.findByHorario", Turno.class);
			tq.setParameter("horario", tTurno.getHorario());
			List<Turno> turno =tq.getResultList();
			
			if(leido != null && leido.getActivo() && turno.isEmpty())
			{
				leido.setHorario(tTurno.getHorario());
				
				em.getTransaction().commit();
			}
			else
			{
				id = -1;
				
				em.getTransaction().rollback();
			}
		}
		catch(Exception exception)
		{
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

	@Override
	public int eliminar(int id) {
		int res = -1;
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try
		{
			em.getTransaction().begin();
			
			Turno leido = em.find(Turno.class, id, LockModeType.OPTIMISTIC);
			
			Collection<Empleado> empleados = leido.getEmpleados();
			
			boolean empleadosNoActivos = true;
			for(Empleado e:empleados)
			{
				em.lock(e, LockModeType.OPTIMISTIC);
				empleadosNoActivos = empleadosNoActivos && !e.isActivo();
			}
			
			if(leido != null && leido.getActivo() && empleadosNoActivos)
			{
				leido.setActivo(false);
				res = leido.getId();
				
				em.getTransaction().commit();
			}
			else
				em.getTransaction().rollback();
		}
		catch(Exception exception)
		{	
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			res = -1;
		}
		finally
		{
			em.close();
		}
		
		return res;
	}

	@Override
	public Collection<TTurno> mostrarTurnos() {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		
		List<TTurno> resultado = new ArrayList<TTurno>();
		
		try{
			em.getTransaction().begin();
			TypedQuery<Turno> tq = em.createNamedQuery("Turno.findAll", Turno.class);
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Turno> leido = tq.getResultList();
			
			for(Turno t : leido)
				resultado.add(t.toTransfer());
		
			em.getTransaction().commit();
		}
		catch(Exception exception)
		{
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			resultado = new ArrayList<TTurno>();
		}
		finally
		{
			em.close();			
		}
		
		return resultado;
	}

	@Override
	public int calcularNomina(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		int nomina = -1;
		
		try
		{
			em.getTransaction().begin();
			
			Turno leido = em.find(Turno.class, id, LockModeType.OPTIMISTIC);
			
			if(leido != null && leido.getActivo())
			{
				nomina = 0;
				
				Collection<Empleado> empleados = leido.getEmpleados();
				
				for(Empleado empleado : empleados)
				{
					em.lock(empleado, LockModeType.OPTIMISTIC);
					
					if(empleado.isActivo())
						nomina += empleado.calcularSueldo();
				}
			}
			
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			nomina = -1;
		}
		finally
		{
			em.close();
		}
		
		return nomina;
	}
	
	public void bajaFisica(int id){
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try
		{
			em.getTransaction().begin();
			
			Turno turno = em.find(Turno.class, id);
			
			if(turno != null)
			{
				em.remove(turno);
				
				em.getTransaction().commit();	
			}
			else
				em.getTransaction().rollback();
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
