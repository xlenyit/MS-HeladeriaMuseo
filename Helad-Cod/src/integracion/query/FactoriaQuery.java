package integracion.query;

public abstract class FactoriaQuery {

	private static FactoriaQuery instance;
	
	public static synchronized FactoriaQuery getInstance(){
		if(instance == null){
			instance = new FactoriaQueryImp();
		}
		return instance;
	}
	
	public abstract Query getQuery(String tipo);
	
	//public abstract QueryTrabajadorConMasVentas trabajadorConMasVentas();
	//public abstract QueryProductoMasVendido productoMasVendido();
}