
package negocio.proveedor.imp;

import negocio.producto.entidad.Producto;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TLineaProveedorProducto;
import negocio.proveedor.TProveedor;
import negocio.proveedor.entity.Proveedor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integración.factoriaEntityManager.FactoriaEntityManagerSingleton;

public class SAProveedorImp implements SAProveedor {

	@Override
	public int alta(TProveedor tproveedor) {
		int id = -1;
		if (tproveedor.getNIF().length() != 9 || tproveedor.getTelefono().equals("")|| tproveedor.getDireccion().equals(""))
			return id;

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {

			em.getTransaction().begin();

			Proveedor proveedor = new Proveedor(tproveedor);

			TypedQuery<Proveedor> tq = em.createNamedQuery("negocio.proveedor.entity.Proveedor.findByNIF", Proveedor.class);
			tq.setParameter("NIF", tproveedor.getNIF());
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Proveedor> leido = tq.getResultList();

			if (leido.isEmpty()) {
				em.persist(proveedor);
				em.flush();
				id = proveedor.getId();
				em.getTransaction().commit();
			
			} else if (!leido.get(0).getActivo()) {
				proveedor = leido.get(0);
				proveedor.setActivo(true);
				id = proveedor.getId();
				em.getTransaction().commit();
			}
			
			else{
				em.getTransaction().rollback();
			}
			
			
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			id = -1;
		} finally {
			em.close();
		}

		return id;
	}

	@Override
	public TProveedor buscar(int id) {

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		TProveedor res = null;
		try {
			em.getTransaction().begin();

			Proveedor prov = em.find(Proveedor.class, id, LockModeType.OPTIMISTIC);

			if (prov != null)
			{
				res = prov.toTransfer();
				em.getTransaction().commit();
			}
			
			else{
				em.getTransaction().rollback();
			}
			
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();

			res = null;
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int eliminar(int id) {
		int res = -1;

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();

			Proveedor leido = em.find(Proveedor.class, id, LockModeType.OPTIMISTIC);
			Collection<Producto> productos = leido.getProductos();
			boolean productosNoActivos = true;
			
			for (Producto p : productos) {
				em.lock(p, LockModeType.OPTIMISTIC);
				productosNoActivos = productosNoActivos && !p.getActivo();
			}

			if (leido != null && leido.getActivo() && productosNoActivos) {
				leido.setActivo(false);
				res = leido.getId();
			}
			em.getTransaction().commit();
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			res = -1;
		} finally {
			em.close();
		}

		return res;
	}

	@Override
	public int modificar(TProveedor tproveedor) {

		int id = tproveedor.getId();

		if (id <= 0)
			return -1;

		if (tproveedor.getNIF().length() != 9 || tproveedor.getTelefono().equals("")|| tproveedor.getDireccion().equals(""))
			return id;

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			Proveedor leido = em.find(Proveedor.class, id);

			TypedQuery<Proveedor> tq = em.createNamedQuery("negocio.proveedor.entity.Proveedor.findByNIF", Proveedor.class);
			tq.setParameter("NIF", tproveedor.getNIF());
			List<Proveedor> prov = tq.getResultList();

			if (leido != null && leido.getActivo() && prov.isEmpty())
			{
				leido.setNIF(tproveedor.getNIF());
				leido.setTelefono(tproveedor.getTelefono());
				leido.setDireccion(tproveedor.getDireccion());
				em.getTransaction().commit();
			}
			
			else
			{
				id = -1;
				em.getTransaction().rollback();
			}
			
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();

			id = -1;
		} finally {
			em.close();
		}

		return id;
	}

	@Override
	public Collection<TProveedor> readAll() {

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();

		List<TProveedor> resultado = new ArrayList<TProveedor>();

		try {
			em.getTransaction().begin();
			TypedQuery<Proveedor> tq = em.createNamedQuery("negocio.proveedor.entity.Proveedor.findAll", Proveedor.class);
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<Proveedor> leido = tq.getResultList();

			for (Proveedor p : leido)
				resultado.add(p.toTransfer());

			em.getTransaction().commit();
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();

			resultado = new ArrayList<TProveedor>();
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public Collection<TProveedor> mostrarProvPorProd(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		List<TProveedor> resultado = new ArrayList<TProveedor>();

		try {
			em.getTransaction().begin();
			Producto aux = em.find(Producto.class, id, LockModeType.OPTIMISTIC);

			if (aux == null) {
				em.getTransaction().rollback();
				return resultado;
			}
			
			TypedQuery<Proveedor> tq = em.createNamedQuery("negocio.proveedor.entity.Proveedor.findByproducto", Proveedor.class);
			tq.setParameter("producto", aux);
			
			List<Proveedor> leido = tq.getResultList();
			
			for (Proveedor t : leido)
				resultado.add(t.toTransfer());
			
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			resultado = new ArrayList<TProveedor>();
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public TLineaProveedorProducto asignarProveedorAProducto(TLineaProveedorProducto tlinea) {

		TLineaProveedorProducto res = new TLineaProveedorProducto(-1, -1);
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		
		try {
			em.getTransaction().begin();
			Proveedor prov = em.find(Proveedor.class, tlinea.getIdProveedor(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Producto prod = em.find(Producto.class, tlinea.getIdProducto(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			if (prov != null && prod != null && prod.getActivo() && prov.getActivo()) {
				Collection<Producto> lista = prov.getProductos();
				if (!lista.contains(prod)) {
					prov.addProducto(prod);
					prod.addProveedor(prov);
					res.setIdProducto(prod.getId());
					res.setIdProveedor(prov.getId());
					em.getTransaction().commit();
				}
				
				else{
					em.getTransaction().rollback();
				}
			}
			
			else{
				em.getTransaction().rollback();
			}
		}
		catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

		finally {
			em.close();
		}

		return res;
	}

	@Override
	public TLineaProveedorProducto desasignarProveedorDeProducto(TLineaProveedorProducto tlinea) {
		TLineaProveedorProducto res = new TLineaProveedorProducto(-1, -1);

		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			Proveedor prov = em.find(Proveedor.class, tlinea.getIdProveedor(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Producto prod = em.find(Producto.class, tlinea.getIdProducto(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			if (prov != null && prod != null && prod.getActivo() && prov.getActivo()) {
				Collection<Producto> lista = prov.getProductos();
				if (lista.contains(prod)) {
					prov.removeProducto(prod);
					prod.removeProveedor(prov);
					res.setIdProducto(prod.getId());
					res.setIdProveedor(prov.getId());
					em.getTransaction().commit();
				}
				
				else{
					em.getTransaction().rollback();
				}
			}
			
			else{
				em.getTransaction().rollback();
			}
		}

		catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

		finally {
			em.close();
		}

		return res;
	}
	
	public void bajaFisica(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		
		try	{
			em.getTransaction().begin();
			
			Proveedor proveedor = em.find(Proveedor.class, id);
			if(proveedor != null) {
				em.remove(proveedor);
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