package integracion.transacciones;

public interface Transaction {
	public void start();

	public void commit() throws Exception;

	public void rollback() throws Exception;

	public Object getResource();
}
