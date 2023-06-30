package negocio.obra;

public class TEscultura extends TObra {

	private String material;
	public TEscultura(Integer id, String material, Boolean activo, String nombre, String autor, Double coste, Integer idExposicion) {
		super(id, activo, nombre, autor, coste, idExposicion);
		this.material = material;
	}

	public String getMaterial() {
		
		return material;
	}
	public void setMaterial(String material) {
		
		this.material = material;
	}
	
//	public Double getPrecio(){
//		return precio;
//	}

}
