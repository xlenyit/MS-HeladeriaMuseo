package negocio.cliente.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.cliente.DAOCliente;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.factura.DAOFactura;
import integración.query.FactoriaQuery;
import integración.query.Query;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.cliente.SACliente;
import negocio.cliente.TCliente;

public class SAClienteImp implements SACliente {

	public int create(TCliente cl) {
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

			if (cl.getDni().length() != 9) {
				transaccion.rollback();
				return id;
			}
			DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();
			if (cl != null) {
				TCliente leido = daoCliente.readByName(cl.getDni());
				if (leido == null)
					id = daoCliente.create(cl);
				else if (!leido.getActivo()) {
					cl.setId(leido.getId());
					id = daoCliente.update(cl);
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

	public TCliente read(int id) {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		TCliente cliente = null;

		try {
			transaccion.start();

			cliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente().read(id);
			if (cliente == null)
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return null;
		}

		return cliente;
	}

	public int update(TCliente cl) {
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

			if (cl.getDni().length() != 9) {
				transaccion.rollback();
				return id;
			}
			DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();
			if (cl != null) {
				TCliente leido = daoCliente.read(cl.getId());
				TCliente leidoN = daoCliente.readByName(cl.getDni());
				if (leido != null && (leidoN == null || leidoN != null && leido.getId() == leidoN.getId()))
					if (leido.getActivo())
						id = daoCliente.update(cl);
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

	public int delete(int idCliente) {
		int id = -1;

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
		try {
			transaccion.start();

			if (daoCliente.read(idCliente) != null && daoFactura.readByCliente(idCliente).isEmpty())
				id = daoCliente.delete(idCliente);
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

	public Collection<TCliente> readAll() {
		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		Collection<TCliente> clientes = null;

		try {
			transaccion.start();

			clientes = FactoriaAbstractaIntegracion.getInstance().createDAOCliente().readAll();

			if (clientes.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return new ArrayList<TCliente>();
		}

		return clientes;
	}

	public int deleteFisico(int idCliente) {
		int id = -1;

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();

		try {
			transaccion.start();

			id = daoCliente.deleteFisico(idCliente);
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

	@Override
	public Collection<TCliente> clienteConMasFacturacion() {
		FactoriaQuery Factory = FactoriaQuery.getInstance();
		Query query = Factory.clienteConMasFacturacion();
		TransactionManager transactionManager = TransactionManager.getInstance();
		Transaction transaccion;
		try {
			transaccion = transactionManager.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = transactionManager.getTransaccion();
		}
		Collection<TCliente> cliente;
		try {
			transaccion.start();

			cliente = (Collection<TCliente>) query.execute();

			if (cliente.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();
		} catch (Exception e) {
			return new ArrayList<TCliente>();
		}
		return cliente;
	}
}