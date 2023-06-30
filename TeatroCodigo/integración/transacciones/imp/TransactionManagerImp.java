package integración.transacciones.imp;

import java.util.concurrent.ConcurrentHashMap;

import integración.factoriaTransaccion.TransactionFactory;
import integración.transacciones.Transaction;
import integración.transacciones.TransactionManager;

public class TransactionManagerImp extends TransactionManager {
	private static ConcurrentHashMap<Thread, Transaction> transacciones;

	/** 
	* @return the transacciones
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public ConcurrentHashMap getTransacciones() {
		// begin-user-code
		return transacciones;
		// end-user-code
	}

	/** 
	* @param transacciones the transacciones to set
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setTransacciones(ConcurrentHashMap transacciones) {
		// begin-user-code
		this.transacciones = transacciones;
		// end-user-code
	}

	public TransactionManagerImp() {
		transacciones = new ConcurrentHashMap<Thread, Transaction>();
	}

	public Transaction nuevaTransaccion() throws Exception {

		Thread thread = Thread.currentThread();
		Transaction t = null;

		if (transacciones != null) {
			t = transacciones.get(thread);

			if (t == null) {
				t = TransactionFactory.getInstance().newTransaction();
				transacciones.put(thread, t);
			} else {
				throw new Exception("Ya existe una transaccion asociada a esta hebra");
			}
		}

		return t;
	}

	public void eliminaTransaccion() {
		Thread thread = Thread.currentThread();

		if (transacciones != null) {
			Transaction t = transacciones.get(thread);

			if (t != null) {
				transacciones.remove(thread);
				t = null;
			}
		}
	}

	public Transaction getTransaccion() {

		Thread thread = Thread.currentThread();

		if (transacciones != null && transacciones.get(thread) == null) {
			try {
				nuevaTransaccion();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return transacciones.get(thread);
	}
}
