package integracion.transacciones;

import java.util.concurrent.ConcurrentHashMap;

import integracion.factoriaTransaccion.TransactionFactory;

public class TransactionManagerImp extends TransactionManager {
	private static ConcurrentHashMap<Thread, Transaction> transacciones;

	public ConcurrentHashMap<Thread,Transaction> getTransacciones() {
		return transacciones;
	}
	public static void setTransacciones(ConcurrentHashMap<Thread,Transaction> transaccion) {
		transacciones = transaccion;
	}

	public TransactionManagerImp() {
		transacciones = new ConcurrentHashMap<Thread, Transaction>();
	}

	public Transaction nuevaTransaccion() {

		Thread thread = Thread.currentThread();
		Transaction t = null;

		if (!transacciones.contains(thread)) {
			t = TransactionFactory.getInstance().newTransaction();
			transacciones.put(thread, t);
		}else {
			t = transacciones.get(thread);
		}

		return t;
	}

	public void eliminaTransaccion() {
		Thread thread = Thread.currentThread();

		if (transacciones != null) {
			Transaction t = transacciones.get(thread);

			if (t != null) {
				transacciones.remove(thread, t);
			}
		}
	}

	public Transaction getTransaccion() {		
		Thread thread = Thread.currentThread();
		Transaction trans = transacciones.get(thread);
		
		if(trans == null) 
			System.out.println("TransactionManagerImp: No existe una transacción activa"); 
		
		return trans;
	}
}
