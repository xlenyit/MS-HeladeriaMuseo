package presentacion.exposicion.listarExposicion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.exposicion.TExposicion;
import negocio.seccion.TSeccion;


public class modeloListarExposicion extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	
	private static List<TExposicion> expo;
	private String[] cols = { "ID", "Nombre", "Genero", "Activo"};
	
	public modeloListarExposicion() {
		expo = new ArrayList<TExposicion>();
	}
	
	public void update(ArrayList<TExposicion> listaE2) {
		expo = new ArrayList<TExposicion>();
		Iterator<TExposicion> it = listaE2.iterator();
		while (it.hasNext()) {
			TExposicion sa = it.next();
			expo.add(sa);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return expo.size();
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
			return expo.get(rowIndex).getId();
		case 1:
			return expo.get(rowIndex).getNombre();
		case 2:
			return expo.get(rowIndex).getGenero();
		case 3:
			return expo.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
