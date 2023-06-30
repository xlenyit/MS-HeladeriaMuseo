package negocio.facturaTienda.imp;

import negocio.empleado.entidad.Empleado;
import negocio.facturaTienda.SAFacturaTienda;
import negocio.facturaTienda.TFacturaTienda;
import negocio.facturaTienda.entidad.FacturaTienda;
import negocio.facturaTienda.entidad.LineaFacturaTienda;
import negocio.facturaTienda.entidad.LineaFacturaTiendaId;
import negocio.producto.entidad.Producto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import integración.factoriaEntityManager.FactoriaEntityManagerSingleton;

public class SAFacturaTiendaImp implements SAFacturaTienda {

	public int cerrarVenta(TFacturaConProducto tFacturaConProducto) {
		int id = -1;
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();

			TFacturaTienda tFactura = tFacturaConProducto.getTFacturaTienda();

			Empleado empleado = null;
			empleado = em.find(Empleado.class, tFactura.getIdEmpleado(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			double precioTot = 0;

			if (empleado != null && empleado.isActivo()) {
				Collection<TLineaFacturaTienda> listaTLineaFactura = tFacturaConProducto.getTLineaFacturaTienda();
				Iterator<TLineaFacturaTienda> it = listaTLineaFactura.iterator();
				Collection<LineaFacturaTienda> listaLineaFactura = new ArrayList<LineaFacturaTienda>();
				FacturaTienda factura = new FacturaTienda(tFactura);

				while (it.hasNext()) {
					TLineaFacturaTienda tLineaFactura = it.next();
					Producto producto = null;
					producto = em.find(Producto.class, tLineaFactura.getIdProducto());

					if (producto != null && tLineaFactura.getCantidad() > 0 && producto.getStock() > 0
							&& producto.getActivo()) {
						LineaFacturaTienda lineaFactura = new LineaFacturaTienda(tLineaFactura);
						lineaFactura.setProducto(producto);
						lineaFactura.setFactura(factura);
						if (producto.getStock() >= tLineaFactura.getCantidad()) {
							precioTot += tLineaFactura.getCantidad() * producto.getPrecio();
							producto.setStock(producto.getStock() - tLineaFactura.getCantidad());
						} else {
							precioTot += producto.getStock() * producto.getPrecio();
							producto.setStock(0);
						}
						listaLineaFactura.add(lineaFactura);
					}
				}

				if (!listaLineaFactura.isEmpty()) {
					factura.setNProductos(listaLineaFactura.size());
					factura.setPrecioTot(precioTot);
					factura.setEmpleado(empleado);
					factura.setLineaFactura(listaLineaFactura);
					em.persist(factura);
					em.flush();
					id = factura.getId();
				}
			}
			if (precioTot > 0)
				em.getTransaction().commit();
			else
				em.getTransaction().rollback();
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			id = -1;
		} finally {
			em.close();
		}
		return id;
	}

	public TFacturaConProducto buscar(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		TFacturaConProducto res = new TFacturaConProducto();	//TODO a lo mejor hay que incializarlo a null para la comprobación del comando
		try {
			em.getTransaction().begin();

			FacturaTienda factura = em.find(FacturaTienda.class, id, LockModeType.OPTIMISTIC);

			if (factura != null)
				res.añadirTFacturaTienda(factura.toTransfer());

			Iterator<LineaFacturaTienda> it = factura.getLineaFactura().iterator();

			Collection<TLineaFacturaTienda> listaTLineaFactura = new ArrayList<TLineaFacturaTienda>();

			while (it.hasNext()) {
				listaTLineaFactura.add(it.next().toTransfer());
			}

			res.setTLineaFacturaTienda(listaTLineaFactura);

			em.getTransaction().commit();
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			res = null;
		} finally {
			em.close();
		}
		return res;
	}

	public int modificar(TFacturaTienda tFactura) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		int id = -1;
		try {
			em.getTransaction().begin();
			FacturaTienda leido = null;
			leido = em.find(FacturaTienda.class, tFactura.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Empleado empleadoNuevo = em.find(Empleado.class, tFactura.getIdEmpleado());
			if (leido != null && leido.getActivo() && empleadoNuevo != null && empleadoNuevo.isActivo()) {
				leido.setFecha(tFactura.getFecha());
				leido.setEmpleado(empleadoNuevo);
				id = leido.getId();
			}
			if (leido.getEmpleado().getId() == tFactura.getIdEmpleado())
				em.getTransaction().commit();
			else
				em.getTransaction().rollback();
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			id = -1;
		} finally {
			em.close();
		}
		return id;
	}

	public Collection<TFacturaConProducto> mostrar() {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();

		Collection<TFacturaConProducto> resultado = new ArrayList<TFacturaConProducto>();

		try {
			em.getTransaction().begin();
			TypedQuery<FacturaTienda> tq = em.createNamedQuery("FacturaTienda.findAll", FacturaTienda.class);
			tq.setLockMode(LockModeType.OPTIMISTIC);
			List<FacturaTienda> leido = tq.getResultList();

			for (FacturaTienda ft : leido) {
				TFacturaConProducto factura = new TFacturaConProducto();
				factura.añadirTFacturaTienda(ft.toTransfer());

				for (LineaFacturaTienda lft : ft.getLineaFactura()) {
					factura.añadirLineaFacturaTienda(lft.toTransfer());
				}

				resultado.add(factura);
			}

			if (!resultado.isEmpty())
				em.getTransaction().commit();
			else
				em.getTransaction().rollback();

		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();

			resultado = new ArrayList<TFacturaConProducto>();
		} finally {
			em.close();
		}

		return resultado;
	}

	public Collection<TFacturaConProducto> buscarPorEmpleado(int idEmpleado) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		Collection<TFacturaConProducto> res = new ArrayList<TFacturaConProducto>();
		try {
			em.getTransaction().begin();
			Empleado empleado = em.find(Empleado.class, idEmpleado, LockModeType.OPTIMISTIC);

			Collection<FacturaTienda> leido = empleado.getFacturaTienda();

			for (FacturaTienda ft : leido) {
				TFacturaConProducto factura = new TFacturaConProducto();
				factura.añadirTFacturaTienda(ft.toTransfer());

				for (LineaFacturaTienda lft : ft.getLineaFactura()) {
					factura.añadirLineaFacturaTienda(lft.toTransfer());
				}

				res.add(factura);
			}

			if (!res.isEmpty())
				em.getTransaction().commit();
			else
				em.getTransaction().rollback();

		} catch (Exception ex) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();

			res = null;

		} finally {
			em.close();
		}

		return res;
	}

	public int devolverProducto(TLineaFacturaTienda tLineaFactura) {
		int id = -1;
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();

		try {
			em.getTransaction().begin();
			LineaFacturaTienda leido = null;
			Producto producto = null;
			leido = em.find(LineaFacturaTienda.class,
					new LineaFacturaTiendaId(tLineaFactura.getIdFactura(), tLineaFactura.getIdProducto()));
			producto = em.find(Producto.class, tLineaFactura.getIdProducto());
			if (leido != null && producto != null) {
				if (leido.getCantidad() > tLineaFactura.getCantidad()) {
					leido.setCantidad(leido.getCantidad() - tLineaFactura.getCantidad());
					leido.getFactura().setPrecioTot(
							leido.getFactura().getPrecioTot() - leido.getPrecio() * tLineaFactura.getCantidad());
					producto.setStock(producto.getStock() + tLineaFactura.getCantidad());
				}

				else if (leido.getCantidad() <= tLineaFactura.getCantidad()) {
					if (leido.getFactura().getLineaFactura().size() == 1)
						leido.getFactura().setActivo(false);

					producto.setStock(producto.getStock() + leido.getCantidad());
					leido.getFactura()
							.setPrecioTot(leido.getFactura().getPrecioTot() - leido.getPrecio() * leido.getCantidad());
					leido.getFactura().setNProductos(leido.getFactura().getNProductos() - 1);

					em.remove(leido);
				}
			}

			if (leido != null && producto != null) {
				id = tLineaFactura.getIdFactura();
				em.getTransaction().commit();
			} else {
				id = -1;
				em.getTransaction().rollback();
			}

		} catch (Exception ex) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			id = -1;
		} finally {
			em.close();
		}

		return id;
	}

	public void bajaFisica(int id) {
		EntityManager em = FactoriaEntityManagerSingleton.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();

			FacturaTienda factura = em.find(FacturaTienda.class, id);
			if (factura != null) {
				for (LineaFacturaTienda linea : factura.getLineaFactura()) {
					em.remove(linea);
				}
				em.remove(factura);
			}

			em.getTransaction().commit();
		} catch (Exception exception) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}