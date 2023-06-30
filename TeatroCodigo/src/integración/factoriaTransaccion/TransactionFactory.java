package integración.factoriaTransaccion;

import integración.factoriaTransaccion.imp.TransactionFactoryImp;
import integración.transacciones.Transaction;

public abstract class TransactionFactory {
	private static TransactionFactory instance;

	public static TransactionFactory getInstance() {
		if (instance == null)
			instance = new TransactionFactoryImp();
		return instance;
	}
	
	public abstract Transaction newTransaction();
}
