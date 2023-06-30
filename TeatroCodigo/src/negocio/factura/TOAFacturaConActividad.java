package negocio.factura;

import java.util.Collection;

import negocio.actividad.TActividad;

public class TOAFacturaConActividad {

	private Collection<TLineaFactura> tLineaFactura;

	private Collection<TActividad> tActividad;

	private TFactura tFactura;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tLineaFactura
	* @param tActividad
	* @param tFactura
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TOAFacturaConActividad TFacturaConActividad(Collection<TLineaFactura> tLineaFactura,
			Collection<TActividad> tActividad, TFactura tFactura) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param carrito
	* @param idCliente
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TOAFacturaConActividad TFacturaConActividad(Collection<TLineaFactura> carrito, int idCliente) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tLineaFactura
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void addLineaFactura(TLineaFactura tLineaFactura) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idActividad
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void deleteLineaFactura(int idActividad) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tFactura
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void addFactura(TFactura tFactura) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public TOAFacturaConActividad(Collection<TLineaFactura> carrito, int idCliente, int idFactura) {
		this.tLineaFactura = carrito;
		this.tActividad = null;
		this.tFactura = new TFactura(idFactura, 0, 0, idCliente);
	}

	public Collection<TLineaFactura> getTLineaFactura() {
		return this.tLineaFactura;
	}

	public void setTLineaFactura(Collection<TLineaFactura> tLineaFactura) {
		this.tLineaFactura = tLineaFactura;
	}

	public Collection<TActividad> getTActividad() {
		return this.tActividad;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tActividad
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setTActividad(TActividad... tActividad) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public String toString() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public void setTActividad(Collection<TActividad> tActividad) {
		this.tActividad = tActividad;
	}

	public TFactura getTFactura() {
		return this.tFactura;
	}

	public void setTFactura(TFactura tFactura) {
		this.tFactura = tFactura;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idActividad
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Collection<TLineaFactura> getTLineaFactura(int idActividad) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tLineaFactura
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setTLineaFactura(TLineaFactura... tLineaFactura) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}