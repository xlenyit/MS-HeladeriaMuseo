package negocio.temporada.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.actividad.DAOActividad;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.temporada.DAOTemporada;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;

public class SATemporadaImp implements SATemporada {

	public int create(TTemporada t) {
		int id = -1;

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {
			transaccion.start();

			if (t.getNumTemporada() <= 0 || t.getCalificacion() < 0 || t.getCalificacion() > 10
					|| t.getFechaFin().before(t.getFechaInicio())) {
				transaccion.rollback(); // Fallo logico de negocio
				return id;
			}
			DAOTemporada daoTemporada = FactoriaAbstractaIntegracion.getInstance().createDAOTemporada();
			if (t != null) { // acceso a la implementación del DAO
				TTemporada leido = daoTemporada.readByName(t.getNumTemporada());
				if (leido == null)
					id = daoTemporada.create(t);
				else if (!leido.getActivo()) {
					t.setId(leido.getId());
					id = daoTemporada.update(t);
				}
			}

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de
										// sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}
		return id;
	}

	public TTemporada read(int idTemporada) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		TTemporada temporada = null;

		try {
			transaccion.start();

			temporada = FactoriaAbstractaIntegracion.getInstance().createDAOTemporada().read(idTemporada);
			if (temporada == null)
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return null;
		}

		return temporada;
	}

	public int update(TTemporada t) {
		int id = -1;
		DAOTemporada daoTemporada = FactoriaAbstractaIntegracion.getInstance().createDAOTemporada();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {
			transaccion.start();

			if (t != null) { // acceso a la implementación del DAO
				TTemporada leido = daoTemporada.read(t.getId());
				TTemporada leidoNombre = daoTemporada.readByName(t.getNumTemporada());
				if (leido != null && (leidoNombre == null || leidoNombre != null && leidoNombre.getId() == t.getId()))
					if (leido.getActivo())
						id = daoTemporada.update(t);
			}
			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de
										// sql
			else
				transaccion.commit();
		} catch (Exception e) {
			return -1;
		}
		return id;
	}

	public int delete(int idTemporada) {
		int id = -1;
		DAOTemporada daoTemporada = FactoriaAbstractaIntegracion.getInstance().createDAOTemporada();
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

			if (daoTemporada.read(idTemporada) != null && daoActividad.readByTemporada(idTemporada).isEmpty())
				id = daoTemporada.delete(idTemporada);

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de
										// sql
			else
				transaccion.commit();
		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public Collection<TTemporada> readAll() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		Collection<TTemporada> temporadas = null;

		try {
			transaccion.start();

			temporadas = FactoriaAbstractaIntegracion.getInstance().createDAOTemporada().readAll();

			if (temporadas.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return new ArrayList<>();
		}

		return temporadas;
	}

	public int deleteFisico(int idTemporada) {
		int id = -1;
		DAOTemporada daoTemporada = FactoriaAbstractaIntegracion.getInstance().createDAOTemporada();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {
			transaccion.start();

			id = daoTemporada.deleteFisico(idTemporada);

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de
										// sql
			else
				transaccion.commit();
		} catch (Exception e) {
			return -1;
		}

		return id;
	}

}