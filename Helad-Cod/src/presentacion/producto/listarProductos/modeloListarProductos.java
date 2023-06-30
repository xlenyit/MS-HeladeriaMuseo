package presentacion.producto.listarProductos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.producto.TBatido;
import negocio.producto.THelado;
import negocio.producto.TProducto;


public class modeloListarProductos extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static List<TProducto> secc;
	private String[] cols = { "ID", "Nombre", "Sabor", "Tipo", "Id proveedor", "Activo", "Envase", "Tamanio", "Stock"};
	
	public modeloListarProductos() {
		secc = new ArrayList<TProducto>();
	}
	
	public void update(Collection<TProducto> productos) {
		secc = new ArrayList<TProducto>();
		Iterator<TProducto> it = productos.iterator();
		while (it.hasNext()) {
			TProducto pa = it.next();
			secc.add(pa);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return secc.size();
	}
	
	@Override
	public int getColumnCount() {
		return cols.length;
	}
	
	public String getColumnName(int columnIndex) {
		return cols[columnIndex];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return secc.get(rowIndex).getId();
		case 1:
			return secc.get(rowIndex).getNombre();
		case 2:
			return secc.get(rowIndex).getSabor();
		case 3:
			return secc.get(rowIndex).getTipo();
		case 4:
			return secc.get(rowIndex).getIdProveedor();
		case 5:
			return secc.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		case 6:
			if(secc.get(rowIndex).getTipo().equalsIgnoreCase("helado")) return ((THelado) secc.get(rowIndex)).getEnvase();
			else return "-";
		case 7:
			if(secc.get(rowIndex).getTipo().equalsIgnoreCase("batido")) return ((TBatido) secc.get(rowIndex)).getTamanio();
			else return "-";
		case 8:
			int i = secc.get(rowIndex).getStock();
			if(i<0) i = 0;
			return i;
		default:
			assert (false);
			return "ERROR";
		}
	}
}
