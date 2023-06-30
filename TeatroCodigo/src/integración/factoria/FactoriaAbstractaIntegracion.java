package integración.factoria;

import integración.actividad.DAOActividad;
import integración.cliente.DAOCliente;
import integración.compañia.DAOCompañia;
import integración.factura.DAOFactura;
import integración.factura.DAOLineaFactura;
import integración.miembrosdecompañia.DAOLineaMiembros;
import integración.miembrosdecompañia.DAOMiembrosDeCompañia;
import integración.obra.DAOObra;
import integración.temporada.DAOTemporada;

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

	public abstract DAOMiembrosDeCompañia createDAOMiembrosDeCompañia();

	public abstract DAOCompañia createDAOCompañia();

	public abstract DAOObra createDAOObra();

	public abstract DAOTemporada createDAOTemporada();

	public abstract DAOCliente createDAOCliente();

	public abstract DAOActividad createDAOActividad();

	public abstract DAOFactura createDAOFactura();

	public abstract DAOLineaMiembros createDAOLineaMiembros();

	public abstract DAOLineaFactura createDAOLineaFactura();
}