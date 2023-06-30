package integracion.transacciones;

public abstract class TransactionManager {
	private static TransactionManager instancia = null;

	public synchronized static TransactionManager getInstance() {
		if (instancia == null) {
			instancia = new TransactionManagerImp();
		}
		return instancia;
	}
	
	public static void setInstance(TransactionManager instance) {
		instancia = instance;
	}

	public abstract Transaction nuevaTransaccion();

	public abstract void eliminaTransaccion();

	public abstract Transaction getTransaccion();
}
