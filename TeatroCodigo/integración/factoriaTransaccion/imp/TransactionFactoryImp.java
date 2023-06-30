package integraci�n.factoriaTransaccion.imp;

import integraci�n.factoriaTransaccion.TransactionFactory;
import integraci�n.transacciones.Transaction;
import integraci�n.transacciones.imp.TransactionMySQL;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction newTransaction() {
		return new TransactionMySQL();
	}

}
