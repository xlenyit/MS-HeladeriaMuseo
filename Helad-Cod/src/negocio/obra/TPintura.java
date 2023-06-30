package negocio.obra;

public class TPintura extends TObra{

	private String tipo;
	public TPintura(Integer id, String tipo, Boolean activo, String nombre, String autor, Double coste, Integer idExposicion) {
		super(id, activo, nombre, autor, coste, idExposicion);
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
//	public Double getPrecio(){
//		return precio;
//	}

}