package integraci�n.factoriaTransaccion;

import integraci�n.factoriaTransaccion.imp.TransactionFactoryImp;
import integraci�n.transacciones.Transaction;

public abstract class TransactionFactory {
	private static TransactionFactory instance;

	public static TransactionFactory getInstance() {
		if (instance == null)
			instance = new TransactionFactoryImp();
		return instance;
	}
	
	public abstract Transaction newTransaction();
}
