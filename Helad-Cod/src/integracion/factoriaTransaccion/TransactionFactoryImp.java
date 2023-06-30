package integracion.factoriaTransaccion;

import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionMySQL;

public class TransactionFactoryImp extends TransactionFactory {

	@Override
	public Transaction newTransaction() {
		return new TransactionMySQL();
	}

}
