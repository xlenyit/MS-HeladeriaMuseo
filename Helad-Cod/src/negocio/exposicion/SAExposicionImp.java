package negocio.exposicion;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import negocio.guia.LineaGuia;
import negocio.obra.Obra;
import integracion.factoriaEntityManager.FactoriaEntityManager;


public class SAExposicionImp implements SAExposicion {
	
	public Integer altaExposicion(TExposicion tExposicion) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Exposicion exp = new Exposicion(tExposicion);
		int respuesta = -1;
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			TypedQuery<Exposicion> query = entityManager.createNamedQuery("negocio.exposicion.Exposicion.findBynombre", Exposicion.class);
//			TypedQuery<Exposicion> query = exp.findByNombre(tExposicion);
//			entityManager.createNamedQuery("negocio.exposicion.Exposicion.findBynombre", Exposicion.class);
			query.setParameter("nombre", tExposicion.getNombre());

			List<Exposicion> lista = query.getResultList();
			if (tExposicion != null) {
				if (lista.isEmpty()) {
					entityManager.persist(exp);
					transaction.commit();
					respuesta = exp.getId();//CREACION
				} else if (lista.get(0) != null && !lista.get(0).getActivo()) { 
					lista.get(0).setActivo(true);
					lista.get(0).setGenero(tExposicion.getGenero());
					transaction.commit();
					respuesta = lista.get(0).getId(); //REACTIVACION
				} else {
					transaction.rollback();
					respuesta = -8;//NOMBRE_YA_EXISTENTE
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

	public Integer bajaExposicion(Integer id) {
	        EntityManager entityManager = null;
	        EntityTransaction transaction = null;
	        Exposicion expExiste = null;
	        int respuesta = -1;

	        entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
	        transaction = entityManager.getTransaction();
	        transaction.begin();
	        expExiste = entityManager.find(Exposicion.class, id);
	        if (expExiste != null) {
	            if (expExiste.getActivo()) {
	    	        List<Obra> lista = expExiste.getObras();
	    	        List<LineaGuia> listaDeGuia = expExiste.getGuias();
	    	        
	    	        int i = 0;
	    	        boolean continuar = true;
	    	        if(!lista.isEmpty()){
		                while (i < lista.size() && continuar) {
		                	entityManager.lock(lista.get(i), LockModeType.OPTIMISTIC);
		                	if(lista.get(i).getActivo()) {
		                		transaction.rollback();
		    					respuesta = -27; //EXPOSICION_CON_OBRAS
		    					continuar = false;
		                	}
		                	i++;
		                }
	            	}
	            	if(!listaDeGuia.isEmpty()){
	            		while (i < listaDeGuia.size() && continuar) {
	            			entityManager.lock(listaDeGuia.get(i), LockModeType.OPTIMISTIC);
	            			if(listaDeGuia.get(i).getGuia().getActivo()) {
	            				transaction.rollback();
	            				respuesta = -26; //EXPOSICION_CON_GUIAS
	            				continuar = false;
	            			}
	            			i++;
	            		}
	            	}
	            	else {
	            		expExiste.setActivo(false);
	            		transaction.commit();
	            		respuesta = id;
	            	}
	            }
	            else {
	                transaction.rollback();
	                respuesta = -25; //ENTIDAD_YA_INACTIVA
	            }
	        } else {
	            transaction.rollback();
	            respuesta = -2 ; //ID_NO_EXISTENTE
	        }

	        entityManager.close();

	        return respuesta;
	}

	public Integer modificarExposicion(TExposicion tExposicion) {
		EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Exposicion expExiste = null;

        int respuesta = -1;

        entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        expExiste = entityManager.find(Exposicion.class, tExposicion.getId());
        
        if (expExiste != null) {
        	if(expExiste.getActivo()) {
        		TypedQuery<Exposicion> query = entityManager.createNamedQuery("negocio.exposicion.Exposicion.findBynombre", Exposicion.class);
    			query.setParameter("nombre", tExposicion.getNombre());
    			List<Exposicion> lista = query.getResultList();
    			
        		if(lista.size() != 0) {
        			if(lista.get(0).getId()==tExposicion.getId()){
        				expExiste.setNombre(tExposicion.getNombre());
        				expExiste.setGenero(tExposicion.getGenero());
	                	transaction.commit();
	                	respuesta = expExiste.getId();
    				}
        			else{
        				transaction.rollback();
        				respuesta = -8;//NOMBRE_EXISTENTE
        			}
        		} else {
        			expExiste.setActivo(tExposicion.getActivo());
                	expExiste.setGenero(tExposicion.getGenero());
                	expExiste.setNombre(tExposicion.getNombre());
                	transaction.commit();
                	respuesta = expExiste.getId();
        		}
        	} else {
                transaction.rollback();
                respuesta = -6;//ENTIDAD_NO_ACTIVA;
        	}
        } else {
            transaction.rollback();
            respuesta = -2;//ID_NO_EXISTENTE
        }
        entityManager.close();
        return respuesta;
	}

	public TExposicion mostrarExposicion(Integer id) {
		EntityManager entityManager = null;
		EntityTransaction transaction =null;
		Exposicion expExiste = null;
		try{
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			expExiste = entityManager.find(Exposicion.class, id);
			
			if(expExiste == null){
				transaction.rollback();
				return null;
			}
		} catch (Exception e){
			transaction.rollback();
			return null;
		}finally{
			entityManager.close();
		}
		return expExiste.toTransfer();
	}
	
	public List<TExposicion> listarExposicion() {
		EntityManager entityManager =  null;
		EntityTransaction transaction = null;
		List<TExposicion> lista = new ArrayList<TExposicion>();
		
		
		try{
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			TypedQuery<Exposicion> query = entityManager.createNamedQuery("negocio.exposicion.Exposicion.findAll", Exposicion.class);
			query.setLockMode(LockModeType.OPTIMISTIC);
			List<Exposicion> leido = query.getResultList();
			
			for (Exposicion e: leido){
				lista.add(e.toTransfer());
			}
			transaction.commit();
		} catch (Exception e){
			if(entityManager.getTransaction().isActive())
				transaction.rollback();
			
			lista= new ArrayList<TExposicion>();
			
		} finally{
			entityManager.close();
		}
		return lista;
		
	}

	@Override
	public double calcularCoste(int id) {
		EntityManager entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();		
		Exposicion expo = null;
		
		try {
			transaction.begin();
			expo = entityManager.find(Exposicion.class, id);
			
			if (expo == null || !expo.getActivo()) {
				transaction.rollback();
				return -28;
			}
			
			double coste = 0;
			
			for (Obra o : expo.getObras()) {
				entityManager.lock(o, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if(o.getActivo())	
					coste += o.calcularCoste();
			}
			
			entityManager.close();
			
			return coste;
		} catch (Exception e) {
			transaction.rollback();
		}
		
		return -1;
	}
}