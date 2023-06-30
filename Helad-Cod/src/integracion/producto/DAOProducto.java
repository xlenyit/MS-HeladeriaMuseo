package integracion.producto;

import java.util.List;

import negocio.producto.TProducto;

public interface DAOProducto {
	public Integer create(TProducto tProducto);
	public Integer delete(Integer Id);
	public List<TProducto> readAll();
	public Integer update(TProducto Producto);
	public TProducto readById(Integer Id);
	public List<TProducto> readByProveedor(Integer IdProveedor);
	public TProducto readByNombre(String Nombre);
	public int restarStock(int id, int cantidad);
	public int sumarStock(int id, int cantidad);
}