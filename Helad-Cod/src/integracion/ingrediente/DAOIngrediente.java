package integracion.ingrediente;

import java.util.List;

import negocio.ingrediente.TIngrediente;

public interface DAOIngrediente {
	public int create(TIngrediente tingrediente);

	public int delete(int id);

	public List<TIngrediente> readAll();

	public int update(TIngrediente tingrediente);

	public TIngrediente readById(int id);
	
	public TIngrediente readByCodigo(String codigo);

}