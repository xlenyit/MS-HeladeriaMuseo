package presentacion.venta.altaVenta;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TablaDatosModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	protected class CarroDataType {
		private int idProducto;
		private int unidades;
		
		public CarroDataType(int idProducto, int unidades) {
			this.idProducto = idProducto;
			this.unidades = unidades;
		}
		
		public CarroDataType (int idProducto) {
			this.idProducto = idProducto;
		}
		
		public int getIDProducto() {
			return idProducto;
		}
		
		public int getUnidades() {
			return unidades;
		}
	}
	
	public static final int COLUMNS = 2;
	
	private List<CarroDataType> productos;
	
	public TablaDatosModel() {
		productos = new ArrayList<CarroDataType>();
	}
	
	@Override
	public int getColumnCount() {
		return COLUMNS;
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column) {
			case 0:
				return "ID";
			case 1:
				return "Unidades";
			default:
				return "";
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return productos.get(rowIndex).getIDProducto();
			case 1:
				return productos.get(rowIndex).getUnidades();
		}
		return null;
	}
	
	public void addProduct(int id, int unidades) {
		productos.add(new CarroDataType(id, unidades));
		fireTableDataChanged();
	}
	
	public void removeProducts(int id) {
		for(CarroDataType v : productos) {
			if (id == v.getIDProducto()) { 
				productos.remove(v);
				fireTableDataChanged();
				return;
			}
		}
	}
}
