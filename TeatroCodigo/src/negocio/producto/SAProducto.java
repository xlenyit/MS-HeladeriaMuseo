package negocio.producto;

import java.util.Collection;

public interface SAProducto {

	public int alta(TProducto tProducto);

	public int eliminar(int id);

	public TProducto buscar(int id);

	public int modificar(TProducto data);

	public Collection<TProducto> mostrar();

	public Collection<TProducto> mostrarProdPorProveedor(int idProv);
	
	public void bajaFisica(int id);
}