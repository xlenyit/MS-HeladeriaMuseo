package integración.factoriaTransaccion.imp;

import integración.factoriaTransaccion.TransactionFactory;
import integración.transacciones.Transaction;
import integración.transacciones.imp.TransactionMySQL;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction newTransaction() {
		return new TransactionMySQL();
	}

}
