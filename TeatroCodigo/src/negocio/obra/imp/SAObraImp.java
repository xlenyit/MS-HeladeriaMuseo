package negocio.obra.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.actividad.DAOActividad;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.obra.DAOObra;
import integración.query.FactoriaQuery;
import integración.query.Query;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.obra.SAObra;
import negocio.obra.TObra;

public class SAObraImp implements SAObra {

	public int create(TObra t) {

		int id = -1;

		DAOObra daoObra = FactoriaAbstractaIntegracion.getInstance().createDAOObra();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			if (t != null) {
				TObra leido = daoObra.readByName(t.getTitulo());
				if (leido == null) {
					id = daoObra.create(t);
				} else if (!leido.getActivo()) {
					t.setId(leido.getId());
					id = daoObra.update(t);
				}
			}

			if (id == -1)
				transaccion.rollback();// Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();
		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public TObra read(int idObra) {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		TObra o = null;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			o = FactoriaAbstractaIntegracion.getInstance().createDAOObra().read(idObra);

			if (o == null)
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return null;
		}

		return o;
	}

	public int update(TObra t) {

		int id = -1;

		DAOObra daoObra = FactoriaAbstractaIntegracion.getInstance().createDAOObra();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			if (t != null) {
				TObra leido = daoObra.read(t.getId());
				TObra leidoN = daoObra.readByName(t.getTitulo());
				if (leido != null && (leidoN == null || leidoN != null && leido.getId() == leidoN.getId())) {
					if (leido.getActivo()) {
						id = daoObra.update(t);
					}

				}
			}

			if (id == -1)
				transaccion.rollback();// Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public int delete(int idObra) {

		int id = -1;

		DAOObra daoObra = FactoriaAbstractaIntegracion.getInstance().createDAOObra();
		DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			if (daoObra.read(idObra) != null && daoActividad.readByObra(idObra).isEmpty())
				id = daoObra.delete(idObra);

			if (id == -1)
				transaccion.rollback();// Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public Collection<TObra> readAll() {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		Collection<TObra> c;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			c = FactoriaAbstractaIntegracion.getInstance().createDAOObra().readAll();

			if (c.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return new ArrayList<>();
		}

		return c;
	}

	public void deleteFisico(int idObra) {
		int id = -1;
		DAOObra daoObra = FactoriaAbstractaIntegracion.getInstance().createDAOObra();
		DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			if (daoActividad.readByObra(idObra).isEmpty()) {
				id = daoObra.deleteFisico(idObra);
			}

			if (id == -1)
				transaccion.rollback();// Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
		}

	}

	public Collection<TObra> obraConRepresentacionMasVista() {
		FactoriaQuery factory = FactoriaQuery.getInstance();
		Query query = factory.obraConRepresentacionMasVista();
		TransactionManager transactionManager = TransactionManager.getInstance();
		Transaction transaccion;
		try {
			transaccion = transactionManager.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = transactionManager.getTransaccion();
		}
		Collection<TObra> obra;
		try {

			transaccion.start();
			obra = (Collection<TObra>) query.execute();

			if (obra.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<TObra>();
		}
		return obra;
	}
}