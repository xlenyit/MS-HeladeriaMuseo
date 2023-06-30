package negocio.marca.imp;

import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.marca.entidad.Marca;
import negocio.producto.entidad.Producto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integración.factoriaEntityManager.FactoriaEntityManagerSingleton;

public class SAMarcaImp implements SAMarca {

	public int alta(TMarca tMarca) {
		int id = -1;
		if (tMarca.getNombre().equals(""))
			return id;
		
		EntityManager entityManager = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			Marca marca = new Marca(tMarca);
			
			TypedQuery<Marca> typedQuery = entityManager.createNamedQuery("Marca.findByNombre", Marca.class);
			typedQuery.setParameter("nombre", marca.getNombre());
			typedQuery.setLockMode(LockModeType.OPTIMISTIC);
			List<Marca> leido = typedQuery.getResultList();
			
			if (leido.isEmpty()) {
				
				entityManager.persist(marca);
				entityManager.flush();
				
				id = marca.getId();
			}
			else if (!leido.get(0).getActivo()) {
				marca = leido.get(0);
				marca.setActivo(true);
				
				id = marca.getId();
			}
			
			if(id > 0)
				entityManager.getTransaction().commit();
			else
				entityManager.getTransaction().rollback();
		}
		catch (Exception exception) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			
			id = -1;
		}
		finally {
			entityManager.close();
		}
		return id;
	}

	public TMarca buscar(int idMarca) {
		TMarca result = null;
		EntityManager entityManager = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			Marca marca = entityManager.find(Marca.class, idMarca, LockModeType.OPTIMISTIC);
			
			if (marca != null)
				result = marca.toTransfer();
			
			entityManager.getTransaction().commit();				
		}
		catch (Exception exception) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			
			result = null;
		}
		finally {
			entityManager.close();
		}
		return result;
	}

	public int modificar(TMarca tMarca) {
		int id = tMarca.getId();
		if (id <= 0)
			return -1;
		if (tMarca.getNombre().equals(""))
			return -1;
		
		EntityManager entityManager = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Marca leido = entityManager.find(Marca.class, id);
			
			TypedQuery<Marca> typedQuery = entityManager.createNamedQuery("Marca.findByNombre", Marca.class);
			typedQuery.setParameter("nombre", tMarca.getNombre());
			List<Marca> marca = typedQuery.getResultList();
			
			if (leido != null && leido.getActivo() && marca.isEmpty())
			{
				leido.setNombre(tMarca.getNombre());
				entityManager.getTransaction().commit();
			}
			else
			{
				entityManager.getTransaction().rollback();
				id = -1;
			}
			
		}
		catch (Exception exception) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			id = -1;
		}
		finally {
			entityManager.close();
		}
		return id;
	}

	public int eliminar(int idMarca) {
		int result = -1;
		EntityManager entityManager = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Marca leido = entityManager.find(Marca.class, idMarca, LockModeType.OPTIMISTIC);
			Collection<Producto> productos = leido.getProducto();
			
			boolean productosNoActivos = true;
			for (Producto producto: productos) {
				entityManager.lock(producto, LockModeType.OPTIMISTIC);
				productosNoActivos = productosNoActivos && !producto.getActivo();
			}
			if (leido != null && leido.getActivo() && productosNoActivos) 
			{
				leido.setActivo(false);
				result = leido.getId();
				
				entityManager.getTransaction().commit();				
			}
			else
			{
				entityManager.getTransaction().rollback();
			}
		}
		catch (Exception exception) {	
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			result = -1;
		}
		finally {
			entityManager.close();
		}
		return result;
	}

	public List<TMarca> mostrarMarcas() {
		EntityManager entityManager = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		List<TMarca> result = new ArrayList<TMarca>();
		try {
			entityManager.getTransaction().begin();
			
			TypedQuery<Marca> typedQuery = entityManager.createNamedQuery("Marca.findAll", Marca.class);
			typedQuery.setLockMode(LockModeType.OPTIMISTIC);
			
			List<Marca> leido = typedQuery.getResultList();
			
			for (Marca marca : leido)
				result.add(marca.toTransfer());
			entityManager.getTransaction().commit();
		}
		catch (Exception exception) {
			if(entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			
			result = new ArrayList<TMarca>();
		}
		finally {
			entityManager.close();			
		}
		return result;
	}

	@Override
	public void bajaFisica(int id) {

		EntityManager entityManager = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Marca marca = entityManager.find(Marca.class, id);
			if (marca != null) {
				entityManager.remove(marca);
				entityManager.getTransaction().commit();
			}
			else
				entityManager.getTransaction().rollback();
		}
		catch (Exception exception) {	
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
		}
		finally {
			entityManager.close();
		}
	}
	
}