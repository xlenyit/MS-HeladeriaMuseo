
package negocio.miembrosdecompa�ia.imp;

import java.util.ArrayList;
import java.util.Collection;

import integraci�n.compa�ia.DAOCompa�ia;
import integraci�n.factoria.FactoriaAbstractaIntegracion;
import integraci�n.miembrosdecompa�ia.DAOLineaMiembros;
import integraci�n.miembrosdecompa�ia.DAOMiembrosDeCompa�ia;
import integraci�n.transacciones.Transaction;
import integraci�n.transacciones.TransactionManager;
import negocio.compa�ia.TCompa�ia;
import negocio.miembrosdecompa�ia.SAMiembrosDeCompa�ia;
import negocio.miembrosdecompa�ia.TLineaMiembro;
import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;

public class SAMiembrosDeCompa�iaImp implements SAMiembrosDeCompa�ia {

	public int create(TMiembrosDeCompa�ia tMiembrosDeCompa�ia) {

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

			if (tMiembrosDeCompa�ia.getDNI().length() != 9) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			DAOMiembrosDeCompa�ia daoMiembrosDeCompa�ia = FactoriaAbstractaIntegracion.getInstance()
					.createDAOMiembrosDeCompa�ia();
			if (tMiembrosDeCompa�ia != null) { // acceso a la implementaci�n del DAO
				TMiembrosDeCompa�ia leido = daoMiembrosDeCompa�ia.readByName(tMiembrosDeCompa�ia.getDNI());
				if (leido == null) {
					id = daoMiembrosDeCompa�ia.create(tMiembrosDeCompa�ia);
				}

				else {
					tMiembrosDeCompa�ia.setId(leido.getId());
					id = daoMiembrosDeCompa�ia.update(tMiembrosDeCompa�ia);
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

	public TMiembrosDeCompa�ia read(int id) {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		TMiembrosDeCompa�ia m = null;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			m = FactoriaAbstractaIntegracion.getInstance().createDAOMiembrosDeCompa�ia().read(id);

			if (m == null)
				transaccion.rollback();
			else
				transaccion.commit();

		} catch (Exception e) {
			return null;
		}

		return m;
	}

	public int update(TMiembrosDeCompa�ia tMiembrosDeCompa�ia) {

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

			if (tMiembrosDeCompa�ia.getDNI().length() != 9) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			DAOMiembrosDeCompa�ia daoMiembrosDeCompa�ia = FactoriaAbstractaIntegracion.getInstance()
					.createDAOMiembrosDeCompa�ia();
			if (tMiembrosDeCompa�ia != null) { // acceso a la implementaci�n del DAO

				TMiembrosDeCompa�ia leido = daoMiembrosDeCompa�ia.read(tMiembrosDeCompa�ia.getId());
				TMiembrosDeCompa�ia leidoN = daoMiembrosDeCompa�ia.readByName(tMiembrosDeCompa�ia.getDNI());
				if (leido != null && (leidoN == null || leidoN != null && leido.getId() == leidoN.getId()))
					if (leido.getActivo())
						id = daoMiembrosDeCompa�ia.update(tMiembrosDeCompa�ia);

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

		DAOMiembrosDeCompa�ia daoMiembrosDeCompa�ia = FactoriaAbstractaIntegracion.getInstance()
				.createDAOMiembrosDeCompa�ia();
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
			if (daoMiembrosDeCompa�ia.read(id_miembro) != null) {
				//Y adem�s esta asignado a mas de una compa��a
				if (!daoLineaMiembros.readByMiembro(id_miembro).isEmpty()) {
					//delete f�sico de linea-miembro
					daoLineaMiembros.deleteFisicoMiembro(id_miembro);
				}

				//delete logico
				idM = daoMiembrosDeCompa�ia.delete(id_miembro);
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

	public Collection<TMiembrosDeCompa�ia> readAll() {

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;
		Collection<TMiembrosDeCompa�ia> c = null;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			c = FactoriaAbstractaIntegracion.getInstance().createDAOMiembrosDeCompa�ia().readAll();

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
	* @see SAMiembrosDeCompa�ia#addToCompany(int idMiembro, int idCompa�ia)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int addToCompany(int idMiembro, int idCompa�ia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAMiembrosDeCompa�ia#removeFromCompany(int idCompa�ia, int idMiembro)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int removeFromCompany(int idCompa�ia, int idMiembro) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAMiembrosDeCompa�ia#updateMeses(int idMiembro, int idCompa�ia, int numMeses)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateMeses(int idMiembro, int idCompa�ia, int numMeses) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	public int addToCompany(TLineaMiembro tLineaMiembro) {

		int id = -1;

		DAOLineaMiembros daoLineaMiembros = FactoriaAbstractaIntegracion.getInstance().createDAOLineaMiembros();
		DAOMiembrosDeCompa�ia daoMiembrosDeCompa�ia = FactoriaAbstractaIntegracion.getInstance()
				.createDAOMiembrosDeCompa�ia();
		DAOCompa�ia daoCompa�ia = FactoriaAbstractaIntegracion.getInstance().createDAOCompa�ia();

		TransactionManager tm = TransactionManager.getInstance();
		Transaction transaccion;

		try {
			transaccion = tm.nuevaTransaccion();
		} catch (Exception e) {
			transaccion = tm.getTransaccion();
		}

		try {

			transaccion.start();

			TMiembrosDeCompa�ia tMiembroDeCompa�ia = daoMiembrosDeCompa�ia.read(tLineaMiembro.getIdMiembro());
			TCompa�ia tCompa�ia = daoCompa�ia.read(tLineaMiembro.getIdCompa�ia());

			if (tLineaMiembro.getNMeses() < 1) {
				transaccion.rollback();//Fallo logico de negocio
				return id;
			}

			//El cliente y la compa�ia existen y estan activos
			if (tLineaMiembro != null && tCompa�ia != null && tMiembroDeCompa�ia != null) {
				TLineaMiembro leido = daoLineaMiembros.read(tLineaMiembro.getIdMiembro(),
						tLineaMiembro.getIdCompa�ia());

				//El cliente no estaba asignado a la compa�ia por tanso se le asigna
				if (leido == null)
					id = daoLineaMiembros.addToCompany(tLineaMiembro);

				//El cliente estaba ya asignado a la compa�ia por tanto se actualizan los meses
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
						tLineaMiembro.getIdCompa�ia());
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
						tLineaMiembro.getIdCompa�ia());
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
		DAOMiembrosDeCompa�ia daoMiembrosDeCompa�ia = FactoriaAbstractaIntegracion.getInstance()
				.createDAOMiembrosDeCompa�ia();

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
			//Y adem�s esta asignado a mas de una compa��a
			if (!daoLineaMiembro.readByMiembro(idMiembro).isEmpty()) {
				id = daoLineaMiembro.deleteFisicoMiembro(idMiembro);
			}

			id = daoMiembrosDeCompa�ia.deleteFisico(idMiembro);

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