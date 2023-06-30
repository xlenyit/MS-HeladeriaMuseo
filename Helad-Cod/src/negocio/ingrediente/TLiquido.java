package negocio.ingrediente;

public class TLiquido extends TIngrediente{
	private Integer espesor;
	
	public TLiquido(int id, Integer espesor, String nombre, Integer cantidad, String codigo,Boolean activo,Integer idProducto) {
		super(nombre,cantidad,codigo,"liquido",activo,id, idProducto);
		this.espesor = espesor;
	}

	public Integer getEspesor() {
		return espesor;
	}

	public void setEspesor(Integer espesor) {
		this.espesor = espesor;
	}

	

}