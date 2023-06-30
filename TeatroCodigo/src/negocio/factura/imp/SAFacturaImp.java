
package negocio.factura.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.actividad.DAOActividad;
import integración.cliente.DAOCliente;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.factura.DAOFactura;
import integración.factura.DAOLineaFactura;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.actividad.TActividad;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.factura.TOAFacturaConActividad;
import negocio.factura.TLineaFactura;

public class SAFacturaImp implements SAFactura {

	public int create(TOAFacturaConActividad tFacturaConActividad) {

		int idFactura = -1;

		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
		DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();

		Collection<TLineaFactura> tLineaFactura = new ArrayList<TLineaFactura>();
		Collection<TActividad> tActividades = new ArrayList<TActividad>();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();

			int precio = 0;
			int nActividades = 0;

			if (daoCliente.read(tFacturaConActividad.getTFactura().getIdCliente()) != null) {

				for (TLineaFactura linea : tFacturaConActividad.getTLineaFactura()) {
					TActividad actividad = daoActividad.read(linea.getIdActividad());
					int cantidad = 0;

					if (actividad != null && linea.getCantidad() > 0 && actividad.getEntradasDisponibles() > 0) {
						tLineaFactura.add(linea);
						if (linea.getCantidad() <= actividad.getEntradasDisponibles()) {
							cantidad += linea.getCantidad();
							precio += cantidad * actividad.getPrecio();
							actividad.setEntradasDisponibles(actividad.getEntradasDisponibles() - linea.getCantidad());
						} else {
							cantidad += actividad.getEntradasDisponibles();
							precio += cantidad * actividad.getPrecio();
							actividad.setEntradasDisponibles(0);
						}
						nActividades++;
						tActividades.add(actividad);
						daoActividad.update(actividad);
					}

				}

				if (!tLineaFactura.isEmpty() && !tActividades.isEmpty()) {
					tFacturaConActividad.getTFactura().setNumActividades(nActividades);
					tFacturaConActividad.getTFactura().setPrecioTotal(precio);
					idFactura = daoFactura.create(tFacturaConActividad.getTFactura());
					if (idFactura > 0) {
						for (TLineaFactura linea : tFacturaConActividad.getTLineaFactura()) {
							linea.setIdFactura(idFactura);
							daoLineaFactura.create(linea);
						}
					}
				}
			}

			if (idFactura == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();
		} catch (Exception exception) {
			return -1;
		}
		return idFactura;
	}

	public TFactura read(int id) {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		TFactura factura = null;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();

			factura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura().read(id);

			if (factura == null)
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception exception) {
			return null;
		}
		return factura;
	}

	public int update(TOAFacturaConActividad tFacturaConActividad) {

		int id = -1;

		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
		DAOActividad daoActividad = FactoriaAbstractaIntegracion.getInstance().createDAOActividad();

		Collection<TLineaFactura> tLineaFactura = new ArrayList<TLineaFactura>();
		Collection<TActividad> tActividades = new ArrayList<TActividad>();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();

			int nActividades = 0;

			for (TLineaFactura linea : tFacturaConActividad.getTLineaFactura()) {
				TActividad actividad = daoActividad.read(linea.getIdActividad());
				if (actividad != null && linea.getCantidad() > 0)
					tLineaFactura.add(linea);
			}

			if (tLineaFactura != null) { // Procedemos con la actualización
				tFacturaConActividad.setTFactura(daoFactura.read(tFacturaConActividad.getTFactura().getId()));
				Collection<TLineaFactura> tLineaFacturaDev = daoLineaFactura
						.readByFactura(tFacturaConActividad.getTFactura().getId());

				for (TLineaFactura linea : tLineaFacturaDev) { // Devolvemos las
																	// anteriores
																// entradas
					TActividad actividad = daoActividad.read(linea.getIdActividad());
					if (actividad != null) {
						actividad.setEntradasDisponibles(actividad.getEntradasDisponibles() + linea.getCantidad());
					}
					daoActividad.update(actividad);
					daoLineaFactura.deleteFisico(linea.getIdFactura(), linea.getIdActividad());
				}

				tFacturaConActividad.setTLineaFactura(tLineaFactura);

				int precio = 0;

				for (TLineaFactura linea : tFacturaConActividad.getTLineaFactura()) { // Cargamos
																							// las
																						// nuevas
																						// entradas
					int cantidad = 0;
					TActividad actividad = daoActividad.read(linea.getIdActividad());
					if (linea.getCantidad() <= actividad.getEntradasDisponibles()) {
						cantidad += linea.getCantidad();
						actividad.setEntradasDisponibles(actividad.getEntradasDisponibles() - linea.getCantidad());
					} else {
						cantidad += actividad.getEntradasDisponibles();
						actividad.setEntradasDisponibles(0);
					}

					precio += cantidad * actividad.getPrecio();
					nActividades++;
					tActividades.add(actividad);
					daoActividad.update(actividad);
				}

				tFacturaConActividad.getTFactura().setNumActividades(nActividades);
				tFacturaConActividad.getTFactura().setPrecioTotal(precio);
				id = daoFactura.update(tFacturaConActividad.getTFactura());
				if (id > 0) {
					for (TLineaFactura linea : tFacturaConActividad.getTLineaFactura()) {
						linea.setIdFactura(id);
						daoLineaFactura.create(linea);
					}
				}

			}

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();
		} catch (Exception exception) {
			return -1;
		}
		return id;
	}

	public int delete(int idFactura) {

		int id = -1;

		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
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

			Collection<TLineaFactura> tLineaFactura = daoLineaFactura.readByFactura(idFactura);

			for (TLineaFactura linea : tLineaFactura) {
				TActividad actividad = daoActividad.read(linea.getIdActividad());
				if (actividad != null) {
					actividad.setEntradasDisponibles(actividad.getEntradasDisponibles() + linea.getCantidad());
					daoActividad.update(actividad);
				}
				daoLineaFactura.deleteFisico(linea.getIdFactura(), linea.getIdActividad());
			}
			if (daoFactura.read(idFactura) != null)
				id = daoFactura.delete(idFactura);

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();
		} catch (Exception exception) {
			return -1;
		}
		return id;
	}

	public Collection<TFactura> readAll() {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		Collection<TFactura> factura = new ArrayList<>();

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();

			DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
			factura = daoFactura.readAll();

			if (factura.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception exception) {
			return new ArrayList<>();
		}
		return factura;
	}

	public Collection<TFactura> readByCliente(int id) {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		Collection<TFactura> factura = new ArrayList<>();

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();

			DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
			factura = daoFactura.readByCliente(id);

			if (factura.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception exception) {
			return new ArrayList<>();
		}
		return factura;
	}

	public int deleteFisico(int idFactura) {

		int id = -1;

		DAOLineaFactura daoLineaFactura = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();

			Collection<TLineaFactura> tLineaFactura = daoLineaFactura.readByFactura(idFactura);

			for (TLineaFactura linea : tLineaFactura) {
				daoLineaFactura.deleteFisico(linea.getIdFactura(), linea.getIdActividad());
			}

			id = daoFactura.deleteFisico(idFactura);

			if (id == -1)
				transaccion.rollback(); // Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();
		} catch (Exception exception) {
			return -1;
		}
		return id;
	}
}