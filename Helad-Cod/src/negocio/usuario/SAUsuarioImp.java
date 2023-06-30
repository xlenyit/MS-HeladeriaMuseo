/**
 * 
 */
package negocio.usuario;




import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.LockModeType;

import java.util.ArrayList;
import java.util.List;

import integracion.factoriaEntityManager.FactoriaEntityManager;
import negocio.actividad.Actividad;
import negocio.guia.Guia;

public class SAUsuarioImp implements SAUsuario {
	
	public Integer altaUsuario(TUsuario tUsuario) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Usuario usua= new Usuario(tUsuario);
		int respuesta = -1;
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Guia guia = entityManager.find(Guia.class, tUsuario.getIdGuia(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if(guia == null){
				return -30;
			}else if(!guia.getActivo()) return -31;
			
			TypedQuery<Usuario> query = entityManager.createNamedQuery("negocio.usuario.Usuario.findBydni", Usuario.class);
			query.setParameter("dni", tUsuario.getDni());
			
			usua.setGuia(guia);
			
			List<Usuario> lista = query.getResultList();
			if(tUsuario!=null)
			{
				if(lista.isEmpty()) //Creacion
				{
					entityManager.persist(usua);
					transaction.commit();
					respuesta= usua.getId();
				}
				else if(lista.get(0)!=null && !lista.get(0).getActivo())
				{
					lista.get(0).setActivo(true);
					lista.get(0).setDni(tUsuario.getDni());
					lista.get(0).setGuia(guia);
					lista.get(0).setNombre(tUsuario.getNombre());
					transaction.commit();
					respuesta= lista.get(0).getId();
				}
				else 
				{
					transaction.rollback();
					respuesta=-3; //DNI_YA_EXISTENTE
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return respuesta;
		} finally {
			entityManager.close();
		}
		return respuesta;
	}

	public Integer bajaUsuario(Integer id) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		int respuesta = -1;
		
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Usuario usuario = entityManager.find(Usuario.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if(usuario!=null){
				if(usuario.getActivo()){
					TypedQuery<Actividad> query = entityManager.createNamedQuery("negocio.actividad.Actividad.findByusuario",Actividad.class);
	            	query.setParameter("usuario", usuario);
	    	        List<Actividad> lista = query.getResultList();
		    	      if(!lista.isEmpty()){
		    	    	  for(Actividad a: lista){
		                	if(a.getActivo()) {
		                		transaction.rollback();
		    					respuesta = -36; //USUARIO_CON_ACTIVIDADES
		    					break;
		                	}
		                }
		    	      }
		    	      else {
		    	    	usuario.setActivo(false);
						transaction.commit();
						respuesta =id;
		    	      }
				}
				else{
					transaction.rollback();
					respuesta = -25; //entidad ya inactiva
				}
			}
			else{
				transaction.rollback();
				respuesta = -2; //id no existente 
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return respuesta;
		} finally {
			entityManager.close();
		}
		return respuesta;
		
	}

	public Integer modificarUsuario(TUsuario tUsuario) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		int respuesta = -1;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Usuario usuario= entityManager.find(Usuario.class, tUsuario.getId()) ;
			if (usuario!= null) {
				
				Guia guia = entityManager.find(Guia.class, tUsuario.getIdGuia(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if(guia == null){
					return -30;
				}else if(!guia.getActivo()) return -31;
				
			
				TypedQuery<Usuario> query = entityManager.createNamedQuery("negocio.usuario.Usuario.findBydni", Usuario.class);
    			query.setParameter("dni", tUsuario.getDni());
    			List<Usuario> lista = query.getResultList();
    			if(!lista.isEmpty()){
    				if(lista.get(0).getId()==tUsuario.getId()){
	                	usuario.setNombre(tUsuario.getNombre());
	                	usuario.setGuia(guia);
	                	transaction.commit();
	                	respuesta = usuario.getId();
    				}
    				else{
    					transaction.rollback();
    					respuesta=-3; //DNI_YA_EXISTENTE
    				}
    				
    			}
    			else{
                	usuario.setNombre(tUsuario.getNombre());
                	usuario.setDni(tUsuario.getDni());
                	usuario.setGuia(guia);
                	transaction.commit();
                	respuesta = usuario.getId();
    			}
			
			} else { 
				transaction.rollback();
				respuesta=-2;//ID_NO_EXISTENTE
			
			}
			
			//transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return -1;
		} finally {
			entityManager.close();
		}

		return respuesta;
	}

	public TUsuario mostrarUsuario(Integer id) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Usuario usua= null;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			usua = entityManager.find(Usuario.class, id, LockModeType.OPTIMISTIC);

			if (usua == null) {
				transaction.rollback();
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		} finally {
			entityManager.close();
		}

		return usua.toTransfer();
	}


	public List<TUsuario> listarUsuario() {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		List<Usuario> lista = null;
		List<TUsuario> tLista = new ArrayList<TUsuario>();
		TypedQuery<Usuario> query;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			query = entityManager.createNamedQuery("negocio.usuario.Usuario.findAll", Usuario.class);
			query.setLockMode(LockModeType.OPTIMISTIC);
			lista = query.getResultList();
			
			for (Usuario u : lista) {
				//entityManager.lock(u,  LockModeType.OPTIMISTIC);
				tLista.add(u.toTransfer());
			}
			transaction.commit();

		} catch (Exception e) {
//			e.printStackTrace();
//			transaction.rollback();
//			return null;
			
			if(entityManager.getTransaction().isActive())
				transaction.rollback();
			
			tLista = new ArrayList<TUsuario>();
		} finally {
			entityManager.close();
		}

		return tLista;
	}

	public List<TUsuario> listarUsuarioPorGuia(Integer idGuia) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		List<Usuario> lista = null;
		List<TUsuario> tLista = new ArrayList<TUsuario>();
		TypedQuery<Usuario> query;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Guia guia = entityManager.find(Guia.class, idGuia, LockModeType.OPTIMISTIC);
			query = entityManager.createNamedQuery("negocio.usuario.Usuario.findByguia", Usuario.class);
			query.setLockMode(LockModeType.OPTIMISTIC);
			query.setParameter("guia", guia);
			lista = query.getResultList();
			
			for (Usuario u : lista) {
				//entityManager.lock(u,  LockModeType.OPTIMISTIC);
				tLista.add(u.toTransfer());
			}

		} catch (Exception e) {
//			e.printStackTrace();
//			transaction.rollback();
//			return null;
			
			if(entityManager.getTransaction().isActive())
				transaction.rollback();
			
			tLista= new ArrayList<TUsuario>();
		} finally {
			entityManager.close();
		}

		return tLista;
	}
}