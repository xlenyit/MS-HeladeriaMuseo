package integración.transacciones;

import integración.transacciones.imp.TransactionManagerImp;

public abstract class TransactionManager {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private TransactionManager instance;
	private static TransactionManager instancia = null;

	public synchronized static TransactionManager getInstance() {
		if (instancia == null) {
			instancia = new TransactionManagerImp();
		}

		return instancia;
	}

	/** 
	* @param instance the instance to set
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setInstance(TransactionManager instance) {
		// begin-user-code
		this.instance = instance;
		// end-user-code
	}

	public abstract Transaction nuevaTransaccion() throws Exception;

	public abstract void eliminaTransaccion();

	public abstract Transaction getTransaccion();
}
