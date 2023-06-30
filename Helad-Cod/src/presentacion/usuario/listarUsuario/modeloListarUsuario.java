package presentacion.usuario.listarUsuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.usuario.TUsuario;


public class modeloListarUsuario extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private static List<TUsuario> usua;
	private String[] cols = { "ID", "DNI", "Nombre", "Guia", "Activo"};
	
	public modeloListarUsuario() {
		usua = new ArrayList<TUsuario>();
	}
	
	public void update(Collection<TUsuario> usuarios) {
		usua = new ArrayList<TUsuario>();
		Iterator<TUsuario> it = usuarios.iterator();
		while (it.hasNext()) {
			TUsuario us = it.next();
			usua.add(us);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return usua.size();
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
			return usua.get(rowIndex).getId();
		case 1:
			return usua.get(rowIndex).getDni();
		case 2:
			return usua.get(rowIndex).getNombre();
		case 3:
			return usua.get(rowIndex).getIdGuia();
		case 4:
			return usua.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
