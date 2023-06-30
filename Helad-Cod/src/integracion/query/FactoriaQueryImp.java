package integracion.query;

public class FactoriaQueryImp extends FactoriaQuery {
	
	
	@Override
	public Query getQuery(String tipo) {
		switch(tipo)
		{
		case "trabajadorConMasVentas":
			return new QueryTrabajadorConMasVentas();
		case "productoMasVendido":
	        return new QueryProductoMasVendido();
	    default: 
	    	return null;
		}
	}

}