package negocio.actividad;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import negocio.usuario.Usuario;
import integracion.factoriaEntityManager.FactoriaEntityManager;

public class SAActividadImp implements SAActividad {
	
	Date currentDate = new java.sql.Date(System.currentTimeMillis());
	
	public Integer altaActividad(TActividad tActividad) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		int respuesta = -1;
		
		try {
			em = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			TypedQuery<Actividad> query = em.createNamedQuery("negocio.actividad.Actividad.findBycodigo", Actividad.class);
			query.setParameter("codigo", tActividad.getCodigo());
			List<Actividad> lista = query.getResultList();
			
			if (lista.isEmpty()) {
				if (!tActividad.getFecha().after(currentDate)){
					respuesta = -34;
					transaction.rollback();
				}
				else if (tActividad instanceof TTaller) {
					TTaller t = (TTaller) tActividad;
					Taller act = new Taller(t);
					
					em.persist(act);
					transaction.commit();
					respuesta = act.getId();
				} else if (tActividad instanceof TCharla) {
					TCharla t = (TCharla) tActividad;
					Charla act = new Charla(t);
					
					em.persist(act);
					transaction.commit();
					respuesta = act.getId();
				}
				
			} else if (lista.get(0) != null && !lista.get(0).getActivo()) { // REACTIVACION
				if((tActividad instanceof TTaller && lista.get(0) instanceof Charla) || (tActividad instanceof TCharla && lista.get(0) instanceof Taller))
				{
					transaction.rollback();
					respuesta=-33; //TIPO INCORRECTO
				}
				else
				{
					lista.get(0).setActivo(true);
					lista.get(0).setCodigo(tActividad.getCodigo());
					lista.get(0).setFecha(tActividad.getFecha());
					lista.get(0).setNombre(tActividad.getNombre());
					
					respuesta = lista.get(0).getId();
					transaction.commit();
				}
			} else { // EXISTENTES Y ACTIVOS
				transaction.rollback();
				respuesta = -22;
			}
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
			em.close();
		}
		return respuesta;
	}

	public Integer bajaActividad(Integer id) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		int respuesta = -1;
		
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Actividad act = entityManager.find(Actividad.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (act != null && act.getActivo()) {
				TypedQuery<Usuario> query = entityManager.createNamedQuery("negocio.usuario.Usuario.findByactividad",Usuario.class);
            	query.setParameter("actividades", act);
            	boolean vinculados=false;
    	        List<Usuario> lista = query.getResultList();
	    	      if(!lista.isEmpty()){
	    	    	  for(Usuario u: lista){//No se dara de baja si tiene usuarios activos vinculados
	                	if(u.getActivo()) {
	                		transaction.rollback();
	    					respuesta = -36; //USUARIO_CON_ACTIVIDADES
	    					vinculados=true;
	    					break;
	                	}
	                }
	    	      }
	    	      if(!vinculados)
	    	      {
	    	    	  act.setActivo(false);
	    	    	  transaction.commit();
	    	    	  respuesta = id;
	    	      }
			} 
			else if(act != null && !act.getActivo()){
				respuesta = -6;
				transaction.rollback();
			}
			else { // ya existe y no esta activo, o no existe
				respuesta = -2;
				transaction.rollback();
			}
			
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		
		return respuesta;
	}

	public Integer modificarActividad(TActividad tActividad) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		
		int respuesta = -1;

		try {
			em = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			Actividad aux = em.find(Actividad.class, tActividad.getId());
			Actividad aux2=em.find(Actividad.class, tActividad.getCodigo());
			if (aux != null) {
				if (aux instanceof Taller && tActividad instanceof TCharla) {
					respuesta = -33;
					transaction.rollback();
				}
				else if (aux instanceof Charla && tActividad instanceof TTaller) {
					respuesta = -33;
					transaction.rollback();
				}
				else if (!tActividad.getFecha().after(currentDate)){
					respuesta = -34;
					transaction.rollback();
				}
				
				else if (aux2 == null) {
					
					if (tActividad instanceof TTaller) {
						Taller act = (Taller) em.find(Actividad.class, tActividad.getId());
						TTaller t = (TTaller) tActividad;
						
						act.setCodigo(t.getCodigo());
						act.setFecha(t.getFecha());
						act.setNombre(t.getNombre());
						act.setUtensilios(t.getUtensilios());
						
						em.persist(act);
						transaction.commit();
						respuesta = act.getId();
					} else if (tActividad instanceof TCharla) {
						Charla act = (Charla) em.find(Actividad.class, tActividad.getId());
						TCharla t = (TCharla) tActividad;
						
						act.setCodigo(t.getCodigo());
						act.setFecha(t.getFecha());
						act.setNombre(t.getNombre());
						act.setNivel(t.getNivel());
						
						em.persist(act);
						transaction.commit();
						respuesta = act.getId();
					}
				} else {
					if(aux2.getId()==tActividad.getId()){
	                	aux.setNombre(tActividad.getNombre());
	                	aux.setCodigo(tActividad.getCodigo());
	                	aux.setFecha(tActividad.getFecha());
	                	if(tActividad instanceof TCharla) ((Charla) aux).setNivel(((TCharla)tActividad).getNivel());
	                	else ((Taller) aux).setUtensilios(((TTaller)tActividad).getUtensilios());
	                	transaction.commit();
	                	respuesta = aux.getId();
    				}
					else
					{
						respuesta = -32;
						transaction.rollback();
					}
				}
			} else {
				respuesta = -2;
				transaction.rollback();
			}
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
			em.close();
		}

		return respuesta;
	}

	public TActividad mostrarActividad(Integer id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		Actividad act = null;
		
		try {
			em = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			
			act = em.find(Actividad.class, id, LockModeType.OPTIMISTIC);
			
			if (act == null) {
				transaction.rollback();
				return null;
			}
			
			transaction.commit();
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
			return null;
		} finally {
			em.close();
		}
		
		return act.toTransfer();
	}
	
	public List<TActividad> listarActividades() {
		
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		List<Actividad> lista = null;
		List<TActividad> tLista = new ArrayList<TActividad>();

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			lista = entityManager.createNamedQuery("negocio.actividad.Actividad.findAll", Actividad.class)
				.getResultList();
			
			for (Actividad a : lista) {
				tLista.add(a.toTransfer());
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		} finally {
			entityManager.close();
		}

		return tLista;
	}

	public Integer anyadirUsuario(TLineaActividad tLineaAct) {
		int res = -1;

		EntityManager entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Actividad act = entityManager.find(Actividad.class, tLineaAct.getActividad(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Usuario usua = entityManager.find(Usuario.class, tLineaAct.getUsuario(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		
		if (act != null && usua != null && act.getActivo() && usua.getActivo()){
			for (Usuario u : act.getUsuarios())
				if (u.getId() == tLineaAct.getUsuario())
					res = -38; //USUARIO YA VINCULADO
			
			if (res != 38) { 
				act.getUsuarios().add(usua);
				usua.getActividades().add(act);
				entityManager.persist(act);
				entityManager.getTransaction().commit();
				res = 14; //VINCULADO_OK
			}else
				entityManager.getTransaction().rollback();
		}
		else {
			entityManager.getTransaction().rollback();
			if(usua==null) res= -29 ; //ENTIDAD NO EXISTENTE
			else if(!usua.getActivo()) res=-28; //ENTIDAD NO ACTIVO
			else if(act == null) res = -32; //ACTIVIDAD NO EXISTE
			else if(!act.getActivo()) res=-39; //ACTIVIDAD NO ACTIVA
			else res=-1;//ERROR GENERICO
		}

		entityManager.close();

		return res;
		
	}

	public Integer eliminarUsuario(TLineaActividad tLineaAct) {
			int res = -1;

			EntityManager entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();

			Actividad act = entityManager.find(Actividad.class, tLineaAct.getActividad(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Usuario usua = entityManager.find(Usuario.class, tLineaAct.getUsuario(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (act != null && usua != null && act.getActivo() && usua.getActivo()){
				/*boolean existe = false;
				int i = 0;
				
				while (existe == false && i < act.getUsuarios().size()) {
					if (act.getUsuarios().get(i).getId() == tLineaAct.getUsuario()) existe=true;
					i++;
				}*/
				
				if (act.getUsuarios().contains(usua)) { 
					act.getUsuarios().remove(usua);
					entityManager.persist(act);
					entityManager.getTransaction().commit();
					res = 16; //DESVINCULADO_OK
				}else{
					entityManager.getTransaction().rollback();
					res = -40; //USUARIO YA DESVINCULADO
				}
			}
			else {
				entityManager.getTransaction().rollback();
				if(usua==null) res= -29 ; //ENTIDAD NO EXISTENTE
				else if(!usua.getActivo()) res=-28; //ENTIDAD NO ACTIVO
				else if(act == null) res = -32; //ACTIVIDAD NO EXISTE
				else if(!act.getActivo()) res=-39; //ACTIVIDAD NO ACTIVA
				else res=-1;//ERROR GENERICO
			}
			entityManager.close();
			return res;

		
	}

	public List<TActividad> listarUsuarios(Integer id) {

		EntityManager entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		List<TActividad> actividades = new ArrayList<TActividad>();
		Usuario usuario = entityManager.find(Usuario.class, id, LockModeType.OPTIMISTIC);

		if (usuario == null || !usuario.getActivo()) {
			entityManager.getTransaction().rollback();
			return null;
		}
		
		for (Actividad a : usuario.getActividades()) {
			actividades.add(a.toTransfer());
		}
		
		entityManager.getTransaction().commit();
		return actividades;
	}

}