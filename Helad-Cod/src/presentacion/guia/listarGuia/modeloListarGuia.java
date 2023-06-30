package presentacion.guia.listarGuia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import negocio.guia.TGuia;


public class modeloListarGuia extends AbstractTableModel{
private static final long serialVersionUID = 1L;
	
	private static List<TGuia> lista;
	private String[] cols = { "ID", "Nombre", "ID Exposicion"};
	
	public modeloListarGuia() {
		lista = new ArrayList<TGuia>();
	}
	
	public void update(Collection<TGuia> obras) {
		lista = new ArrayList<TGuia>();
		Iterator<TGuia> it = obras.iterator();
		while (it.hasNext()) {
			TGuia pa = it.next();
			lista.add(pa);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
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
			return lista.get(rowIndex).getId();
		case 1:
			return lista.get(rowIndex).getNombre();
		case 2: 
			return lista.get(rowIndex).idExpoToString();
		default:
			assert (false);
			return "ERROR";
		}
	}
}
