package negocio.ingrediente;

public class TSolido extends TIngrediente {
	private String tamanio;
	
	public TSolido(int id, String tamanio, String nombre, Integer cantidad, String codigo, Boolean activo,Integer idProducto) {
		super(nombre,cantidad,codigo,"solido",activo,id, idProducto);
		this.tamanio = tamanio;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	
}