package integración.query;

public abstract class FactoriaQuery {

	private static FactoriaQuery instance;
	
	public static FactoriaQuery getInstance(){
		if(instance == null){
			instance = new FactoriaQueryImp();
		}
		return instance;
	}
	
	public abstract QueryObraRepresentacionMasVista obraConRepresentacionMasVista();
	
	public abstract QueryClienteMasFacturacion clienteConMasFacturacion();
}
