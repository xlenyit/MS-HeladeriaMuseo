package integraci�n.factoria;

import integraci�n.actividad.DAOActividad;
import integraci�n.cliente.DAOCliente;
import integraci�n.compa�ia.DAOCompa�ia;
import integraci�n.factura.DAOFactura;
import integraci�n.factura.DAOLineaFactura;
import integraci�n.miembrosdecompa�ia.DAOLineaMiembros;
import integraci�n.miembrosdecompa�ia.DAOMiembrosDeCompa�ia;
import integraci�n.obra.DAOObra;
import integraci�n.temporada.DAOTemporada;

public abstract class FactoriaAbstractaIntegracion {

	private static FactoriaAbstractaIntegracion instance;

	public static FactoriaAbstractaIntegracion getInstance() {
		if (instance == null)
			instance = new FactoriaIntegracion();
		return instance;
	}

	/** 
	* @param instance the instance to set
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public static void setInstance(FactoriaAbstractaIntegracion instance) {
		// begin-user-code
		FactoriaAbstractaIntegracion.instance = instance;
		// end-user-code
	}

	public abstract DAOMiembrosDeCompa�ia createDAOMiembrosDeCompa�ia();

	public abstract DAOCompa�ia createDAOCompa�ia();

	public abstract DAOObra createDAOObra();

	public abstract DAOTemporada createDAOTemporada();

	public abstract DAOCliente createDAOCliente();

	public abstract DAOActividad createDAOActividad();

	public abstract DAOFactura createDAOFactura();

	public abstract DAOLineaMiembros createDAOLineaMiembros();

	public abstract DAOLineaFactura createDAOLineaFactura();
}