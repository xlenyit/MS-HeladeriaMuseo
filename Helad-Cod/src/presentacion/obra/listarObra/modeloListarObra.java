package presentacion.obra.listarObra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.obra.TEscultura;
import negocio.obra.TObra;
import negocio.obra.TPintura;


public class modeloListarObra extends AbstractTableModel{
private static final long serialVersionUID = 1L;
	
	private static List<TObra> lista;
	private String[] cols = { "ID", "Nombre", "Autor","Coste", "Tipo", "Activo", "TipoPintura", "Material", "Exposicion"};
	
	public modeloListarObra() {
		lista = new ArrayList<TObra>();
	}
	
	public void update(Collection<TObra> obras) {
		lista = new ArrayList<TObra>();
		Iterator<TObra> it = obras.iterator();
		while (it.hasNext()) {
			TObra pa = it.next();
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
			return lista.get(rowIndex).getAutor();
		case 3:
			return lista.get(rowIndex).getCoste();
		case 4:
			return (lista.get(rowIndex) instanceof TPintura)? "Pintura" : "Escultura";
		case 5:
			return lista.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		case 6:
			if(lista.get(rowIndex) instanceof TPintura) return ((TPintura) lista.get(rowIndex)).getTipo();
			else return "-";
		case 7:
			if(lista.get(rowIndex) instanceof TEscultura) return ((TEscultura) lista.get(rowIndex)).getMaterial();
			else return "-";
		case 8:
				return lista.get(rowIndex).getIdExposicion();
		default:
			assert (false);
			return "ERROR";
		}
	}
}
