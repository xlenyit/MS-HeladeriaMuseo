package integración.query;

public class FactoriaQueryImp extends FactoriaQuery {

	@Override
	public QueryObraRepresentacionMasVista obraConRepresentacionMasVista() {
		return new QueryObraRepresentacionMasVista();
	}

	@Override
	public QueryClienteMasFacturacion clienteConMasFacturacion() {
		return new QueryClienteMasFacturacion();
	}

}
