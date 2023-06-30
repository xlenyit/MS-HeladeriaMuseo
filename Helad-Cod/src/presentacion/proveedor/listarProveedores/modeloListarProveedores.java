package presentacion.proveedor.listarProveedores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.proveedor.TProveedor;

public class modeloListarProveedores extends AbstractTableModel{	
	private static final long serialVersionUID = 1L;
	private static List<TProveedor> prov;
	private String[] cols = { "ID", "NIF", "Nombre", "Numero de Cuenta", "Activo"};
	
	public modeloListarProveedores() {
		prov = new ArrayList<TProveedor>();
	}
	
	public void update(Collection<TProveedor> productos) {
		prov = new ArrayList<TProveedor>();
		Iterator<TProveedor> it = productos.iterator();
		while (it.hasNext()) {
			TProveedor pa = it.next();
			prov.add(pa);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return prov.size();
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
			return prov.get(rowIndex).getId();
		case 1:
			return prov.get(rowIndex).getNIF();
		case 2:
			return prov.get(rowIndex).getNombre();
		case 3:
			return prov.get(rowIndex).getNumCuenta();
		case 4:
			return prov.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
