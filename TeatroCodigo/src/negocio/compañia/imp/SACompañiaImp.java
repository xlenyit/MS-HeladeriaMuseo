package negocio.compa�ia.imp;

import java.util.ArrayList;
import java.util.Collection;

import integraci�n.actividad.DAOActividad;
import integraci�n.compa�ia.DAOCompa�ia;
import integraci�n.factoria.FactoriaAbstractaIntegracion;
import integraci�n.miembrosdecompa�ia.DAOLineaMiembros;
import integraci�n.transacciones.Transaction;
import integraci�n.transacciones.TransactionManager;
import negocio.compa�ia.SACompa�ia;
import negocio.compa�ia.TCompa�ia;

public class SACompa�iaImp implements SACompa�ia {

	public int create(TCompa�ia tCompa�ia) {
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

			DAOCompa�ia daoCompa�ia = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia();
			if (tCompa�ia != null) { // acceso a la implementaci�n del DAO
				TCompa�ia leido = daoCompa�ia.readByName(tCompa�ia.getNombre());
				if (leido == null)
					id = daoCompa�ia.create(tCompa�ia);
				else if (!leido.getActivo()) {
					tCompa�ia.setId(leido.getId());
					id = daoCompa�ia.update(tCompa�ia);
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

	public TCompa�ia read(int id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		TCompa�ia tCompa�ia = null;

		try {
			transaccion.start();
			tCompa�ia = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia().read(id);
			if (tCompa�ia == null)
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return null;
		}

		return tCompa�ia;
	}

	public int update(TCompa�ia tCompa�ia) {
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
			DAOCompa�ia daoCompa�ia = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia();
			if (tCompa�ia != null) { // acceso a la implementaci�n del DAO
				TCompa�ia leido = daoCompa�ia.read(tCompa�ia.getId());
				TCompa�ia leidoN = daoCompa�ia.readByName(tCompa�ia.getNombre());
				if (leido != null && (leidoN == null || leidoN != null && leido.getId() == leidoN.getId()))
					if (leido.getActivo())
						id = daoCompa�ia.update(tCompa�ia);
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

	public int delete(int idCompa�ia) {
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

			DAOCompa�ia daoCompa�ia = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia();
			DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();
			DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();

			if (daoCompa�ia.read(idCompa�ia) != null && daoActividad.readByCompa�ia(idCompa�ia).isEmpty()) {
				if (!daoLineaMiembros.readByCompa�ia(idCompa�ia).isEmpty()) {
					daoLineaMiembros.deleteFisicoCompa�ia(idCompa�ia);
				}
				id = daoCompa�ia.delete(idCompa�ia);
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

	public Collection<TCompa�ia> readAll() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		Collection<TCompa�ia> compa�ias = null;

		try {
			transaccion.start();

			compa�ias = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia().readAll();

			if (compa�ias.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<>();
		}

		return compa�ias;
	}

	@Override
	public int deleteFisico(int idCompa�ia) {
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
			DAOCompa�ia daoCompa�ia = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia();
			id = daoCompa�ia.deleteFisico(idCompa�ia);

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