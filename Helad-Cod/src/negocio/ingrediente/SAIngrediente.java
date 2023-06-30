package negocio.ingrediente;

import java.util.List;

public interface SAIngrediente {
	public Integer altaIngrediente(TIngrediente tIngrediente, String tipo);

	public TIngrediente mostrarIngrediente(Integer Id);

	public Integer bajaIngrediente(Integer Id);

	public List<TIngrediente> listarIngredientes();

	public Integer actualizarIngrediente(TIngrediente Ingrediente);

}