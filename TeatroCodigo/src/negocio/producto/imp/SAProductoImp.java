 package negocio.producto.imp;

import negocio.marca.entidad.Marca;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import negocio.producto.TProductoConsumible;
import negocio.producto.TProductoDurable;
import negocio.producto.entidad.Producto;
import negocio.producto.entidad.ProductoConsumible;
import negocio.producto.entidad.ProductoDurable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integraci�n.factoriaEntityManager.FactoriaEntityManagerSingleton;
import negocio.proveedor.entity.Proveedor;

public class SAProductoImp implements SAProducto {
	
	public int alta(TProducto tProducto) {
		int id = -1;
		
		if (tProducto.getPrecio() < 0) return id;
		if (tProducto.getStock() < 0) return id;
		if (tProducto.getIdMarca() < 0) return id;
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			
			Marca marca = em.find(Marca.class, tProducto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (marca == null) return id; 
			
			Producto producto;
			boolean es_consumible = tProducto instanceof TProductoConsumible;
			if (es_consumible) producto = new ProductoConsumible((TProductoConsumible) tProducto); 
			else producto = new ProductoDurable((TProductoDurable) tProducto);
			
			TypedQuery<Producto> tq = em.createNamedQuery("Producto.findBymarcaAndNombre", Producto.class);
			tq.setParameter("marca", marca);
			tq.setParameter("nombre", tProducto.getNombre());
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Producto> leido = tq.getResultList();
			producto.setMarca(marca);
			if (leido.isEmpty()) {
				em.persist(producto);
				em.flush();
				id = producto.getId();
			} else if (!leido.get(0).getActivo()) {
				producto = leido.get(0);
				producto.setActivo(true);
				producto.setNombre(producto.getNombre());
				producto.setPrecio(producto.getPrecio());
				producto.setStock(producto.getStock());
				
				if (es_consumible) {
					((ProductoConsumible) producto).setTiempoCaducidad(((TProductoConsumible) tProducto).getTiempoCaducidad());
				} else {
					((ProductoDurable) producto).setTipoDurable(((TProductoDurable) tProducto).getTipo());
				}
				id = producto.getId();
			}
		
			em.getTransaction().commit();
		} 
		catch (Exception exception) {
			if(em.getTransaction().isActive()) em.getTransaction().rollback();
			id = -1;
		} 
		finally {
			em.close();
		}

		return id;
	}

	public int eliminar(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		int res = -1;
		
		try {
			em.getTransaction().begin();
			Producto leido = em.find(Producto.class, id, LockModeType.OPTIMISTIC);
			Collection<Proveedor> proveedores = leido.getProveedor();
			
			boolean proveedoresNoActivos = true;
			for (Proveedor proveedor: proveedores) {
				em.lock(proveedor, LockModeType.OPTIMISTIC);
				proveedoresNoActivos = proveedoresNoActivos && !proveedor.getActivo();
			}
			
			if(leido != null && leido.getActivo() && proveedoresNoActivos) {
				leido.setActivo(false);
				res = leido.getId();
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}
			
		}
		catch(Exception exception){	
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			res = -1;
		}
		finally{
			em.close();
		}
		return res;
	}

	public TProducto buscar(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		TProducto res = null;
		
		try {
			em.getTransaction().begin();
			Producto producto = em.find(Producto.class, id, LockModeType.OPTIMISTIC);
			
			if(producto != null)
				res = producto.toTransfer();
	
			em.getTransaction().commit();
		}
		catch(Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			res = null;
		}
		finally {
			em.close();
		}
		return res;
	}

	public int modificar(TProducto tProducto) {
		int id = -1;
		
		if (tProducto.getPrecio() < 0) return id;
		if (tProducto.getStock() < 0) return id;
		if (tProducto.getIdMarca() < 0) return id;
		
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			
			Marca aux = em.find(Marca.class, tProducto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (aux == null) 
				return id;
			
			boolean es_consumible = tProducto instanceof TProductoConsumible;
			
			Producto producto = em.find(Producto.class, tProducto.getId());

			if (producto != null && producto.getActivo()){
				TypedQuery<Producto> tq = em.createNamedQuery("Producto.findBymarcaAndNombre", Producto.class);
				tq.setParameter("marca", aux);
				tq.setParameter("nombre", tProducto.getNombre());
				List<Producto> repetido = tq.getResultList();
				
				
				if(repetido.isEmpty() || repetido.get(0).getId() == tProducto.getId()){
					producto.setNombre(tProducto.getNombre());
					producto.setPrecio(tProducto.getPrecio());
					producto.setStock(tProducto.getStock());
					producto.setMarca(aux);
					
					if (es_consumible) {
						((ProductoConsumible) producto).setTiempoCaducidad(((TProductoConsumible) tProducto).getTiempoCaducidad());
					} 
					else {
						((ProductoDurable) producto).setTipoDurable(((TProductoDurable) tProducto).getTipo());
					}
					
					id = producto.getId();	
				}
			}
			
			em.getTransaction().commit();
		} catch (Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			id = -1;
		} finally {
			em.close();
		}

		return id;
	}

	public Collection<TProducto> mostrar() {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		List<TProducto> resultado = new ArrayList<TProducto>();
		
		try {
			em.getTransaction().begin();
			TypedQuery<Producto> tq = em.createNamedQuery("Producto.findAll", Producto.class);
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Producto> leido = tq.getResultList();
			
			for(Producto t : leido)
				resultado.add(t.toTransfer());
			em.getTransaction().commit();
		}
		catch(Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			resultado = new ArrayList<TProducto>();
		}
		em.close();
		return resultado;
	}

	public Collection<TProducto> mostrarProdPorProveedor(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		List<TProducto> resultado = new ArrayList<TProducto>();
		
		try {
			em.getTransaction().begin();
			
			Proveedor aux = em.find(Proveedor.class, id, LockModeType.OPTIMISTIC);
			if (aux == null) return resultado;

			TypedQuery<Producto> tq = em.createNamedQuery("Producto.findByproveedor", Producto.class);
			tq.setParameter("proveedor", aux);
			Collection<Producto> leido = aux.getProductos();
			
			for (Producto t : leido)
				resultado.add(t.toTransfer());
			em.getTransaction().commit();
			
		} 
		catch (Exception exception) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			
			resultado = new ArrayList<TProducto>();
		} 
		finally {
			em.close();
		}
		
		return resultado;
	}
	
	public void bajaFisica(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		
		try	{
			em.getTransaction().begin();
			
			Producto producto = em.find(Producto.class, id);
			if(producto != null) {
				em.remove(producto);
			}
			em.getTransaction().commit();
		}
		catch(Exception exception) {	
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		finally	{
			em.close();
		}
	}
}