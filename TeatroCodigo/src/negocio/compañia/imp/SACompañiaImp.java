package negocio.compañia.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.actividad.DAOActividad;
import integración.compañia.DAOCompañia;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.miembrosdecompañia.DAOLineaMiembros;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;

public class SACompañiaImp implements SACompañia {

	public int create(TCompañia tCompañia) {
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

			DAOCompañia daoCompañia = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia();
			if (tCompañia != null) { // acceso a la implementación del DAO
				TCompañia leido = daoCompañia.readByName(tCompañia.getNombre());
				if (leido == null)
					id = daoCompañia.create(tCompañia);
				else if (!leido.getActivo()) {
					tCompañia.setId(leido.getId());
					id = daoCompañia.update(tCompañia);
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

	public TCompañia read(int id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		TCompañia tCompañia = null;

		try {
			transaccion.start();
			tCompañia = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia().read(id);
			if (tCompañia == null)
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return null;
		}

		return tCompañia;
	}

	public int update(TCompañia tCompañia) {
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
			DAOCompañia daoCompañia = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia();
			if (tCompañia != null) { // acceso a la implementación del DAO
				TCompañia leido = daoCompañia.read(tCompañia.getId());
				TCompañia leidoN = daoCompañia.readByName(tCompañia.getNombre());
				if (leido != null && (leidoN == null || leidoN != null && leido.getId() == leidoN.getId()))
					if (leido.getActivo())
						id = daoCompañia.update(tCompañia);
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

	public int delete(int idCompañia) {
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

			DAOCompañia daoCompañia = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia();
			DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();
			DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();

			if (daoCompañia.read(idCompañia) != null && daoActividad.readByCompañia(idCompañia).isEmpty()) {
				if (!daoLineaMiembros.readByCompañia(idCompañia).isEmpty()) {
					daoLineaMiembros.deleteFisicoCompañia(idCompañia);
				}
				id = daoCompañia.delete(idCompañia);
			}

			if (id == -1)
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public Collection<TCompañia> readAll() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		Collection<TCompañia> compañias = null;

		try {
			transaccion.start();

			compañias = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia().readAll();

			if (compañias.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<>();
		}

		return compañias;
	}

	@Override
	public int deleteFisico(int idCompañia) {
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
			DAOCompañia daoCompañia = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia();
			id = daoCompañia.deleteFisico(idCompañia);

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();
		} catch (Exception e) {
			return -1;
		}

		return id;
	}

}