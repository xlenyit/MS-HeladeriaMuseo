package negocio.obra;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.LockModeType;

import integracion.factoriaEntityManager.FactoriaEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import negocio.exposicion.Exposicion;

public class SAObraImp implements SAObra {
	
	public Integer AltaObra(TObra tObra) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Obra obra = null;
		int respuesta = -1;
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Exposicion ex=entityManager.find(Exposicion.class,tObra.getIdExposicion(),  LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if(ex==null){
				respuesta = -29;//ENTIDAD_NO_EXISTE
				transaction.rollback();
			}
			else if(!ex.getActivo()){
				respuesta = -28;//ENTIDAD_NO_ACTIVA
				transaction.rollback();
			} else {
				
				//Diferenciamos que tipo de obra es
				boolean esPintura= tObra instanceof TPintura;
				if(esPintura) obra = new Pintura((TPintura) tObra);
				else obra=new Escultura((TEscultura) tObra);
				
				TypedQuery<Obra> query = entityManager.createNamedQuery("negocio.obra.Obra.findBynombreYautor", Obra.class);
				query.setParameter("nombre", tObra.getNombre());
				query.setParameter("autor", tObra.getAutor());
				
				obra.setExposicion(ex);
				List<Obra> lista = query.getResultList();
				if (tObra != null) {
					if (lista.isEmpty()) {
						if(esPintura) ((Pintura) obra).setTipo(((TPintura)tObra).getTipo());
						else ((Escultura) obra).setMaterial(((TEscultura)tObra).getMaterial());
						
						entityManager.persist(obra);
						transaction.commit();
						respuesta = obra.getId();//CREACION
					} else if (lista.get(0) != null && !lista.get(0).getActivo()) { 
						if((esPintura && lista.get(0) instanceof Escultura) || (!esPintura && lista.get(0) instanceof Pintura))
						{
							transaction.rollback();
							respuesta=-33; //TIPO INCORRECTO
						}
						else
						{
							lista.get(0).setActivo(true);
							lista.get(0).setAutor(tObra.getAutor());
							lista.get(0).setNombre(tObra.getNombre());
							lista.get(0).setCoste(tObra.getCoste());
							
							if(esPintura) ((Pintura) obra).setTipo(((TPintura)tObra).getTipo());
							else ((Escultura) obra).setMaterial(((TEscultura)tObra).getMaterial());
							transaction.commit();
							//respuesta = -4; //REACTIVACION
							respuesta = lista.get(0).getId();
						}
						
					} else {
						transaction.rollback();
						respuesta = -8;//NOMBRE_YA_EXISTENTE
					}
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
	
	public Integer BajaObra(Integer Id) {
		EntityManager entityManager = null;
        EntityTransaction transaction = null;
        int respuesta = -1;

        entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        Obra o = entityManager.find(Obra.class, Id, LockModeType.OPTIMISTIC);
        if (o != null) {
            if (o.getActivo()) {
            	o.setActivo(false);
            	respuesta = Id;
            	transaction.commit();
            }
            else {
                transaction.rollback();
                respuesta = -6; //ENTIDAD_YA_INACTIVA
            }
        } else {
            transaction.rollback();
            respuesta = -2 ; //ID_NO_EXISTENTE
        }

        entityManager.close();

        return respuesta;
	}

	
public Integer ModificarObra(TObra tObra) {
		EntityManager entityManager = null;
        EntityTransaction transaction = null;
        
        
        boolean esPintura= tObra instanceof TPintura;

        int respuesta = -1;
        try{
        entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        Obra o = entityManager.find(Obra.class, tObra.getId());
        
        if (o != null) {
        	
        		TypedQuery<Obra> query = entityManager.createNamedQuery("negocio.obra.Obra.findBynombreYautor", Obra.class);
    			query.setParameter("nombre", tObra.getNombre());
    			query.setParameter("autor", tObra.getAutor());
    			List<Obra> lista = query.getResultList();
    			
    			Exposicion expAux= entityManager.find(Exposicion.class, tObra.getIdExposicion(),LockModeType.OPTIMISTIC);
    			if(expAux==null){
    				transaction.rollback();
    				respuesta = -29; //ENTIDAD_NO_EXISTE
    			}
    			else if(!expAux.getActivo()){
    				respuesta = -28;//ENTIDAD_NO_ACTIVA
    				transaction.rollback();
    			}
    			else if (o instanceof Pintura && tObra instanceof TEscultura) {
					respuesta = -33;//TIPO_INCORRECTO
					transaction.rollback();
				}
				else if (o instanceof Escultura && tObra instanceof TPintura) {
					respuesta = -33;//TIPO_INCORRECTO
					transaction.rollback();
				}
				else if(lista.isEmpty()) //No hay obras con ese autor y nombre
				{
	                	o.setAutor(tObra.getAutor());
	                	o.setExposicion(expAux);
	                	o.setCoste(tObra.getCoste());
	                	o.setNombre(tObra.getNombre());
	                	
	                	
	                	if(esPintura) ((Pintura) o).setTipo(((TPintura)tObra).getTipo());
	                	else ((Escultura) o).setMaterial(((TEscultura)tObra).getMaterial());
	                	respuesta = o.getId();
	                	transaction.commit();
				}
    			else { //hay obras con ese autor y nombre
    				if(lista.get(0).getId()==tObra.getId()){
	                	o.setNombre(tObra.getNombre());
	                	o.setExposicion(expAux);
	                	o.setCoste(tObra.getCoste());
	                	o.setNombre(tObra.getNombre());
	                	if(esPintura) ((Pintura) o).setTipo(((TPintura)tObra).getTipo());
	                	else ((Escultura) o).setMaterial(((TEscultura)tObra).getMaterial());
	                	transaction.commit();
	                	respuesta = o.getId();
    				}
    				else{
    					transaction.rollback();
    					respuesta = -35;//OBRA_EX_AUTOR_Y_NOMBRE
    				}
        		} 
        	
        } else {
            transaction.rollback();
            respuesta = -2;//ID_NO_EXISTENTE
        }
	
	} catch (Exception e) {
		e.getMessage();
		transaction.rollback();
	} finally {
		entityManager.close();
	}
        return respuesta;
}

	
	public TObra MostrarObra(Integer Id) {
		EntityManager entityManager = null;
		EntityTransaction transaction =null;
		Obra o = null;
		TObra tObra=null;
		
		try{
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			o = entityManager.find(Obra.class, Id,LockModeType.OPTIMISTIC);
			
			if(o == null){
				transaction.rollback();
				return null;
			}
			

			
			if(o instanceof Pintura)
				{
					tObra= ((Pintura) o).toTransfer();
					//tObra.setTipo("pintura");
					tObra.setPrecio(((Pintura) o).calcularCoste());
				}
			else {
				tObra= ((Escultura) o).toTransfer();
				//tObra.setTipo("escultura");
				tObra.setPrecio(((Escultura) o).calcularCoste());
			}
			transaction.commit();
		} catch (Exception e){
			transaction.rollback();
			return null;
		}finally{
			entityManager.close();
		}
		return tObra;
	}

	
	public List<TObra> ListarObra() {
		EntityManager entityManager =  null;
		EntityTransaction transaction = null;
		List<TObra> lista = new ArrayList<TObra>();
		
		
		try{
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			TypedQuery<Obra> query = entityManager.createNamedQuery("negocio.obra.Obra.findAll", Obra.class);
			query.setLockMode(LockModeType.OPTIMISTIC);
			List<Obra> leido = query.getResultList();
			
			for (Obra e: leido){
				if(e instanceof Pintura) lista.add(((Pintura)e).toTransfer());
				else lista.add(((Escultura)e).toTransfer());
			}
			transaction.commit();
		} catch (Exception e){
			if(entityManager.getTransaction().isActive())
				transaction.rollback();
			
			lista= null;
			
		} finally{
			entityManager.close();
		}
		return lista;
	}


	@Override
	public List<TObra> ListarObraPorExposicion(Integer idExpo) {
		EntityManager entityManager =  null;
		EntityTransaction transaction = null;
		List<TObra> lista = new ArrayList<TObra>();
		
		
		try{
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Exposicion ex= entityManager.find(Exposicion.class, idExpo, LockModeType.OPTIMISTIC);
			
			TypedQuery<Obra> query = entityManager.createNamedQuery("negocio.obra.Obra.findByexposicion", Obra.class);
			query.setParameter("exposicion", ex);
			List<Obra> leido = query.getResultList();
			
			for (Obra e: leido){
				if(e instanceof Pintura) lista.add(((Pintura)e).toTransfer());
				else lista.add(((Escultura)e).toTransfer());
			}
			transaction.commit();
		} catch (Exception e){
			if(entityManager.getTransaction().isActive())
				transaction.rollback();
			
			lista= new ArrayList<TObra>();
			
		} finally{
			entityManager.close();
		}
		return lista;
	}

	
}