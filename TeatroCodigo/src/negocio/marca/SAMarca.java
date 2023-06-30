package negocio.marca;

import java.util.List;

public interface SAMarca {

	public int alta(TMarca tMarca);

	public TMarca buscar(int idMarca);

	public int modificar(TMarca tMarca);

	public int eliminar(int idMarca);

	public List<TMarca> mostrarMarcas();
	
	public void bajaFisica(int id);
	
}