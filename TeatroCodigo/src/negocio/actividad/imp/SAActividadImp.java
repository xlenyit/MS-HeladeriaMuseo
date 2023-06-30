package negocio.actividad.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.actividad.DAOActividad;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.factura.DAOLineaFactura;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.actividad.TRepresentacion;
import negocio.factura.TLineaFactura;

public class SAActividadImp implements SAActividad {

	public int create(TActividad tActividad) {
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

			if (tActividad.getDuracion() < 0 || tActividad.getEntradasDisponibles() < 0 || tActividad.getPrecio() < 0
					|| tActividad.getFechaFin().before(tActividad.getFechaInicio())) {
				transaccion.rollback();
				return id;
			}
			DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();
			if (tActividad != null) { // acceso a la implementación del DAO
				TActividad leido = daoActividad.readByName(tActividad.getFechaInicio(), tActividad.getHora());
				if (leido == null)
					id = daoActividad.create(tActividad);
				else if (!leido.getActivo()) {
					tActividad.setId(leido.getId());
					id = daoActividad.update(tActividad);
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

	public TActividad read(int id) {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		TActividad tActividad = null;
		try {
			transaccion.start();

			tActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().read(id);
			if (tActividad == null)
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return null;
		}

		return tActividad;
	}

	public int update(TActividad tActividad) {
		int id = -1;

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

			if (tActividad.getDuracion() < 0 || tActividad.getEntradasDisponibles() < 0 || tActividad.getPrecio() < 0
					|| tActividad.getFechaFin().before(tActividad.getFechaInicio())) {
				transaccion.rollback();
				return id;
			}
			if (tActividad != null) { // acceso a la implementación del DAO
				TActividad leido = daoActividad.read(tActividad.getId());
				TActividad Nleido = daoActividad.readByName(tActividad.getFechaInicio(), tActividad.getHora());
				if (leido != null && (Nleido == null || Nleido != null && Nleido.getId() == tActividad.getId()))
					if (leido.getTipo() == tActividad.getTipo())
						if (leido.getActivo())
							id = daoActividad.update(tActividad);
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

	public int delete(int idActividad) {
		int id = -1;
		DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();
		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {
			transaccion.start();

			if (daoActividad.read(idActividad) != null && daoLineaFactura.readByActividad(idActividad).isEmpty())
				id = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().delete(idActividad);

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

	public Collection<TActividad> readAll() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		Collection<TActividad> actividades = new ArrayList<>();
		try {
			transaccion.start();

			actividades = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().readAll();

			if (actividades.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<>();
		}

		return actividades;
	}

	@Override
	public Collection<TRepresentacion> readByCompañia(int id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		Collection<TRepresentacion> representaciones = new ArrayList<>();
		try {
			transaccion.start();
			representaciones = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().readByCompañia(id);
			if (representaciones.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<>();
		}

		return representaciones;
	}

	@Override
	public Collection<TRepresentacion> readByObra(int id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		Collection<TRepresentacion> obras = new ArrayList<>();
		try {
			transaccion.start();
			obras = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().readByObra(id);

			if (obras.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<>();
		}

		return obras;
	}

	@Override
	public Collection<TRepresentacion> readByTemporada(int id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		Collection<TRepresentacion> temporadas = new ArrayList<>();
		try {
			transaccion.start();
			temporadas = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().readByTemporada(id);

			if (temporadas.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<>();
		}

		return temporadas;
	}

	@Override
	public int deleteFisico(int idActividad) {
		int id = -1;
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();
			Collection<TLineaFactura> tLineaFactura = daoLineaFactura.readByActividad(idActividad);

			for (TLineaFactura linea : tLineaFactura) {
				daoLineaFactura.deleteFisico(linea.getIdFactura(), linea.getIdActividad());
			}

			id = FactoriaAbstractaIntegracion.getInstance().createDAOActividad().deleteFisico(idActividad);

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