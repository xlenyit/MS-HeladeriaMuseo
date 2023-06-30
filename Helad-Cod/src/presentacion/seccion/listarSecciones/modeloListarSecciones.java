package presentacion.seccion.listarSecciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.seccion.TSeccion;


public class modeloListarSecciones extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	
	private static List<TSeccion> secc;
	private String[] cols = { "ID", "Nombre", "Sueldo", "Activo"};
	
	public modeloListarSecciones() {
		secc = new ArrayList<TSeccion>();
	}
	
	public void update(Collection<TSeccion> salas) {
		secc = new ArrayList<TSeccion>();
		Iterator<TSeccion> it = salas.iterator();
		while (it.hasNext()) {
			TSeccion sa = it.next();
			secc.add(sa);
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
			return secc.get(rowIndex).getSueldo();
		case 3:
			return secc.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
