
package negocio.guia;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import integracion.factoriaEntityManager.FactoriaEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import negocio.exposicion.Exposicion;

public class SAGuiaImp implements SAGuia {

	public Integer altaGuia(TGuia tGuia) {
		EntityManager em = null;
		EntityTransaction transaction = null;

		int id = -1;

		try {
			// Creamos en entityManager
			em = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = em.getTransaction();

			// Comenzamos la transaccion
			transaction.begin();

			TypedQuery<Guia> query = em.createNamedQuery("negocio.guia.Guia.findBynombre", Guia.class);
			query.setParameter("nombre", tGuia.getNombre());
			List<Guia> lista = query.getResultList();
			
			if (lista.isEmpty()) {
				Guia g = new Guia(tGuia);
				em.persist(g);
				transaction.commit();
				id = g.getId();// CREACION
			} else if (lista.get(0) != null && !lista.get(0).getActivo()) {
				lista.get(0).setActivo(true);
				lista.get(0).setNombre(tGuia.getNombre());
				transaction.commit();
				id = -4; // REACTIVACI�N
			}

			else {
				transaction.rollback();
				id = -8; // NOMBRE YA EXISTENTE
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			transaction.rollback();
			return id;
		} finally {
			em.close();
		}
		return id;
	}

	public Integer bajaGuia(Integer id) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		int respuesta = -1;

		try {
			em = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Guia mGuia = em.find(Guia.class, id, LockModeType.OPTIMISTIC);

			if (mGuia == null) {
				transaction.rollback();
				respuesta = -2; // id no existente
			} else if (mGuia != null && !mGuia.getActivo()) {
				transaction.rollback();
				respuesta = -6; // -25 entidad ya inactiva
			} else if (mGuia != null && mGuia.getActivo()) {
				TypedQuery<LineaGuia> query = em.createNamedQuery("negocio.guia.LineaGuia.findAll", LineaGuia.class);
				query.setLockMode(LockModeType.OPTIMISTIC);
				List<LineaGuia> lista = query.getResultList();
				boolean vinculados = false;
				if (!lista.isEmpty()) {
					for (LineaGuia u : lista) {// No se dara de baja si tiene
												// usuarios activos vinculados
						if (u.getGuia().equals(mGuia) && u.getGuia().getActivo()) {
							transaction.rollback();
							respuesta = -36; // USUARIO_CON_ACTIVIDADES
							vinculados = true;
							break;
						}
					}
				} else if (!vinculados) {

					mGuia.setActivo(false);
					transaction.commit();
					respuesta = id;
				}

			} else { // error
				transaction.rollback();
				respuesta = -1;
			}

		} catch (PersistenceException pe) {
			JOptionPane.showMessageDialog(null, "Error persistence", "ERROR", JOptionPane.ERROR_MESSAGE); // opt
			pe.printStackTrace();
			transaction.rollback();
			return respuesta;
		} finally {
			em.close();
		}

		return respuesta;
	}

	public Integer modificarGuia(TGuia tGuia) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		int respuesta = -1;
		Guia mGuia = null;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			mGuia = entityManager.find(Guia.class, tGuia.getId());

			if (mGuia != null) {

				TypedQuery<Guia> query = entityManager.createNamedQuery("negocio.guia.Guia.findBynombre", Guia.class);
				query.setParameter("nombre", tGuia.getNombre());
				List<Guia> lista = query.getResultList();

				if (lista.isEmpty() || (lista.size() == 1 && mGuia.getNombre() == tGuia.getNombre())) {
					mGuia.setNombre(tGuia.getNombre());
					transaction.commit();
					respuesta = mGuia.getId();
				} else {
					if (lista.size() == 1 && mGuia.getNombre() != tGuia.getNombre())
					transaction.rollback();
					respuesta = -8; // NOMBRE YA EXISTENTE
				}

			} else { // mGuia == null
				transaction.rollback();
				respuesta = -2; // ID NO EXISTENTE
			}

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			return respuesta;
		} finally {
			entityManager.close();
		}

		return respuesta;
	}

	public List<TGuia> listarGuia() {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		List<Guia> lista = null;
		List<LineaGuia> listaLg = null;
		List<TGuia> tLista = new ArrayList<TGuia>();
		TypedQuery<Guia> query;
		TypedQuery<LineaGuia> queryLg;
		TGuia tGuiaAux = null;
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			query = entityManager.createNamedQuery("negocio.guia.Guia.findAll", Guia.class);
			queryLg = entityManager.createNamedQuery("negocio.guia.LineaGuia.findAll", LineaGuia.class);
			query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			lista = query.getResultList();
			listaLg = queryLg.getResultList();

			for (Guia u : lista) {
				List<Integer> listaExpos = new ArrayList<Integer>();
				for (LineaGuia lg : listaLg) {
					if (u.getId() == lg.getGuia().getId()) {
						listaExpos.add(lg.getExposicion().getId());
					}
				}
				tGuiaAux = u.toTransfer();
				tGuiaAux.setIdExposicion(listaExpos);
				tLista.add(tGuiaAux);
			}
			transaction.commit();

		} catch (PersistenceException pe) {
			if (entityManager.getTransaction().isActive())
				transaction.rollback();

			tLista = new ArrayList<TGuia>();
		} finally {
			entityManager.close();
		}

		return tLista;
	}

	public TGuia mostrarGuia(Integer id) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Guia mGuia = null;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			mGuia = entityManager.find(Guia.class, id, LockModeType.OPTIMISTIC);

			if (mGuia == null) {
				transaction.rollback();
				return null;
			}

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			return null;
		} finally {
			entityManager.close();
		}

		return mGuia.toTransfer();
	}

	public Integer anyadirExposicion(TLineaGuia tLineaGuia) {

		Integer res = -1;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Guia guia = null;
		Exposicion ex = null;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			guia = entityManager.find(Guia.class, tLineaGuia.getIdGuia(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);// .OptimisticForceIncrement?
																									// SI
			ex = entityManager.find(Exposicion.class, tLineaGuia.getIdExpo(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);

			if (guia != null && guia.getActivo() && ex != null && ex.getActivo()) {
				LineaGuiaID lgid = new LineaGuiaID(ex.getId(), guia.getId(), tLineaGuia.getHoraIni());
				List<LineaGuia> lista = entityManager.createNamedQuery("negocio.guia.LineaGuia.findByguia", LineaGuia.class)
						.setParameter("guia", guia)
						.setLockMode(LockModeType.OPTIMISTIC)
						.getResultList();
				
				int i = 0;
				boolean continuar = true;
				while (i < lista.size() && continuar) {
					if (lista.get(i).getLgid().getHoraIni().equals(tLineaGuia.getHoraIni())) {
						res = -37;// GUIA OCUPADO
						continuar = false;
					}
					i++;
				}
				
				if (res != -37) {
					LineaGuia lg = new LineaGuia();
					// Un mismo guia no puede tener distintas expos a misma hora
					lg.setExposicion(ex);
					lg.setGuia(guia);
					lg.setLgid(lgid);
					entityManager.persist(lg);
					transaction.commit();
					res = 14; // VINCULADO OK
				}

				else {
					entityManager.getTransaction().rollback();
					res = -37; // GUIA_OCUPADO
				}
			} else {
				entityManager.getTransaction().rollback();
				if (guia == null)
					res = -30; // GUIA NO EXISTENTE
				else if (!guia.getActivo())
					res = -31; // GUIA NO ACTIVO
				else if (ex == null)
					res = -29; // ENTIDAD NO EXISTE
				else if (!ex.getActivo())
					res = -28; // ENTIDAD NO ACTIVA
				else
					res = -1;
			}
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			return res;
		} finally {
			entityManager.close();
		}

		return res;
	}

	public Integer eliminarExposicion(TLineaGuia tLineaGuia) {

		Integer res = -1;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		Guia guia = null;
		Exposicion ex = null;

		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			guia = entityManager.find(Guia.class, tLineaGuia.getIdGuia(), LockModeType.OPTIMISTIC);// .OptimisticForceIncrement?
			ex = entityManager.find(Exposicion.class, tLineaGuia.getIdExpo(), LockModeType.OPTIMISTIC);

			if (guia != null && guia.getActivo() && ex != null && ex.getActivo()) {
				List<LineaGuia> lista = entityManager.createNamedQuery("negocio.guia.LineaGuia.findByguia", LineaGuia.class)
						.setParameter("guia", guia)
						.getResultList();
				LineaGuia aux = null;

				for (LineaGuia l : lista) {
					if (l.getLgid().getHoraIni().equals(tLineaGuia.getHoraIni())
							&& l.getLgid().getIdExposicion().equals(tLineaGuia.getIdExpo()))
						aux = l;
				}

				if (aux != null) {
					entityManager.remove(aux);
					transaction.commit();
					res = 16; // DESVINCULADO OK
				}
			} else {
				entityManager.getTransaction().rollback();
				if (guia == null)
					res = -30; // GUIA NO EXISTENTE
				else if (!guia.getActivo())
					res = -31; // GUIA NO ACTIVO
				else if (ex == null)
					res = -29; // ENTIDAD NO EXISTE
				else if (!ex.getActivo())
					res = -28; // ENTIDAD NO ACTIVA
				else
					res = -1;
			}
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
			return res;
		} finally {
			entityManager.close();
		}

		return res;
	}

	public List<TGuia> listarPorExposicion(Integer id) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		List<Guia> lista = null;
		List<LineaGuia> listaLg = null;
		List<TGuia> tLista = new ArrayList<TGuia>();
		TypedQuery<Guia> query;
		TypedQuery<LineaGuia> queryLg;
		TGuia tGuiaAux = null;
		try {
			entityManager = FactoriaEntityManager.getInstance().getEntityManagerFactory().createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			query = entityManager.createNamedQuery("negocio.guia.Guia.findAll", Guia.class);
			queryLg = entityManager.createNamedQuery("negocio.guia.LineaGuia.findAll", LineaGuia.class);
			query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			lista = query.getResultList();
			listaLg = queryLg.getResultList();
			boolean anyadir = false;
			for (Guia u : lista) {
				anyadir = false;
				List<Integer> listaExpos = new ArrayList<Integer>();
				for (LineaGuia lg : listaLg) {
					if (u.getId() == lg.getGuia().getId()) {
						if(lg.getExposicion().getId() == id) anyadir = true;
						listaExpos.add(lg.getExposicion().getId());
					}
				}
				if (anyadir) {
					tGuiaAux = u.toTransfer();
					tGuiaAux.setIdExposicion(listaExpos);
					tLista.add(tGuiaAux);
				}
			}
			transaction.commit();

		} catch (PersistenceException pe) {
			if (entityManager.getTransaction().isActive())
				transaction.rollback();

			tLista = new ArrayList<TGuia>();
		} finally {
			entityManager.close();
		}

		return tLista;
	}
}