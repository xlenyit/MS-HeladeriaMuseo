
package negocio.miembrosdecompañia.imp;

import java.util.ArrayList;
import java.util.Collection;

import integración.compañia.DAOCompañia;
import integración.factoria.FactoriaAbstractaIntegracion;
import integración.miembrosdecompañia.DAOLineaMiembros;
import integración.miembrosdecompañia.DAOMiembrosDeCompañia;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;
import negocio.compañia.TCompañia;
import negocio.miembrosdecompañia.SAMiembrosDeCompañia;
import negocio.miembrosdecompañia.TLineaMiembro;
import negocio.miembrosdecompañia.TMiembrosDeCompañia;

public class SAMiembrosDeCompañiaImp implements SAMiembrosDeCompañia {

	public int create(TMiembrosDeCompañia tMiembrosDeCompañia) {

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

			if (tMiembrosDeCompañia.getDNI().length() != 9) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			DAOMiembrosDeCompañia daoMiembrosDeCompañia = FactoriaAbstractaIntegracion.getInstance()
					.createDAOMiembrosDeCompañia();
			if (tMiembrosDeCompañia != null) { // acceso a la implementación del DAO
				TMiembrosDeCompañia leido = daoMiembrosDeCompañia.readByName(tMiembrosDeCompañia.getDNI());
				if (leido == null) {
					id = daoMiembrosDeCompañia.create(tMiembrosDeCompañia);
				}

				else {
					tMiembrosDeCompañia.setId(leido.getId());
					id = daoMiembrosDeCompañia.update(tMiembrosDeCompañia);
				}

			}

			if (id == -1)
				transaccion.rollback();//Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public TMiembrosDeCompañia read(int id) {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		TMiembrosDeCompañia m = null;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			m = FactoriaAbstractaIntegracion.getInstance().createDAOMiembrosDeCompañia().read(id);

			if (m == null)
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return null;
		}

		return m;
	}

	public int update(TMiembrosDeCompañia tMiembrosDeCompañia) {

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

			if (tMiembrosDeCompañia.getDNI().length() != 9) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			DAOMiembrosDeCompañia daoMiembrosDeCompañia = FactoriaAbstractaIntegracion.getInstance()
					.createDAOMiembrosDeCompañia();
			if (tMiembrosDeCompañia != null) { // acceso a la implementación del DAO

				TMiembrosDeCompañia leido = daoMiembrosDeCompañia.read(tMiembrosDeCompañia.getId());
				TMiembrosDeCompañia leidoN = daoMiembrosDeCompañia.readByName(tMiembrosDeCompañia.getDNI());
				if (leido != null && (leidoN == null || leidoN != null && leido.getId() == leidoN.getId()))
					if (leido.getActivo())
						id = daoMiembrosDeCompañia.update(tMiembrosDeCompañia);

			}

			if (id == -1)
				transaccion.rollback();//Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		}

		catch (Exception e) {
			return -1;
		}

		return id;
	}

	public int delete(int id_miembro) {

		int idM = -1;

		DAOMiembrosDeCompañia daoMiembrosDeCompañia = FactoriaAbstractaIntegracion.getInstance()
				.createDAOMiembrosDeCompañia();
		DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			//Existe el miembro
			if (daoMiembrosDeCompañia.read(id_miembro) != null) {
				//Y además esta asignado a mas de una compañía
				if (!daoLineaMiembros.readByMiembro(id_miembro).isEmpty()) {
					//delete físico de linea-miembro
					daoLineaMiembros.deleteFisicoMiembro(id_miembro);
				}

				//delete logico
				idM = daoMiembrosDeCompañia.delete(id_miembro);
			}

			if (idM == -1)
				transaccion.rollback(); //Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return idM;
	}

	public Collection<TMiembrosDeCompañia> readAll() {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		Collection<TMiembrosDeCompañia> c = null;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			c = FactoriaAbstractaIntegracion.getInstance().createDAOMiembrosDeCompañia().readAll();

			if (c.isEmpty())
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return new ArrayList<>();
		}

		return c;
	}

	/** 
	* (non-Javadoc)
	* @see SAMiembrosDeCompañia#addToCompany(int idMiembro, int idCompañia)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompañia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAMiembrosDeCompañia#removeFromCompany(int idCompañia, int idMiembro)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idCompañia, int idMiembro) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAMiembrosDeCompañia#updateMeses(int idMiembro, int idCompañia, int numMeses)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idMiembro, int idCompañia, int numMeses) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	public int addToCompany(TLineaMiembro tLineaMiembro) {

		int id = -1;

		DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();
		DAOMiembrosDeCompañia daoMiembrosDeCompañia = FactoriaAbstractaIntegracion.getInstance()
				.createDAOMiembrosDeCompañia();
		DAOCompañia daoCompañia = FactoriaAbstractaIntegracion.getInstance().createDAOCompañia();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			TMiembrosDeCompañia tMiembroDeCompañia = daoMiembrosDeCompañia.read(tLineaMiembro.getIdMiembro());
			TCompañia tCompañia = daoCompañia.read(tLineaMiembro.getIdCompañia());

			if (tLineaMiembro.getNMeses() < 1) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			//El cliente y la compañia existen y estan activos
			if (tLineaMiembro != null && tCompañia != null && tMiembroDeCompañia != null) {
				TLineaMiembro leido = daoLineaMiembros.read(tLineaMiembro.getIdMiembro(),
						tLineaMiembro.getIdCompañia());

				//El cliente no estaba asignado a la compañia por tanso se le asigna
				if (leido == null)
					id = daoLineaMiembros.addToCompany(tLineaMiembro);

				//El cliente estaba ya asignado a la compañia por tanto se actualizan los meses
				else
					id = daoLineaMiembros.updateMeses(tLineaMiembro);
			}

			if (id == -1)
				transaccion.rollback();//Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public int removeFromCompany(TLineaMiembro tLineaMiembro) {

		int id = -1;

		DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			if (tLineaMiembro != null) {
				TLineaMiembro leido = daoLineaMiembros.read(tLineaMiembro.getIdMiembro(),
						tLineaMiembro.getIdCompañia());
				if (leido != null) {
					id = daoLineaMiembros.removeFromCompany(tLineaMiembro);
				}
			}

			if (id == -1)
				transaccion.rollback();//Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	public int updateMeses(TLineaMiembro tLineaMiembro) {

		int id = -1;

		DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			if (tLineaMiembro.getNMeses() < 1) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			if (tLineaMiembro != null) {
				TLineaMiembro leido = daoLineaMiembros.read(tLineaMiembro.getIdMiembro(),
						tLineaMiembro.getIdCompañia());
				if (leido != null)
					id = daoLineaMiembros.updateMeses(tLineaMiembro);
			}

			if (id == -1)
				transaccion.rollback();//Fallo logico de negocio o fallo de sql
			else
				transaccion.commit();

		} catch (Exception e) {
			return -1;
		}

		return id;
	}

	@Override
	public int deleteFisico(int idMiembro) {

		int id = -1;
		DAOMiembrosDeCompañia daoMiembrosDeCompañia = FactoriaAbstractaIntegracion.getInstance()
				.createDAOMiembrosDeCompañia();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}
		try {
			transaccion.start();
			DAOLineaMiembros daoLineaMiembro = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();

			//Existe el miembro
			//Y además esta asignado a mas de una compañía
			if (!daoLineaMiembro.readByMiembro(idMiembro).isEmpty()) {
				id = daoLineaMiembro.deleteFisicoMiembro(idMiembro);
			}

			id = daoMiembrosDeCompañia.deleteFisico(idMiembro);

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