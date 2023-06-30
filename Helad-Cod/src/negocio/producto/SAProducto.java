package negocio.producto;

import java.util.ArrayList;
import java.util.List;

public interface SAProducto {
	public Integer altaProducto(TProducto tProducto);
	public TProducto mostrarProducto(Integer Id);
	public Integer bajaProducto(Integer Id);
	public List<TProducto> listarProductos();
	public Integer actualizarProducto(TProducto Producto);
	public List<TProducto> listarProductosPorProveedor(Integer Id);
	public ArrayList<TProducto> productoMasVendido(String ini, String fin);

}