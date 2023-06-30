package presentacion.ingrediente.listarIngredientes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.ingrediente.TIngrediente;
import negocio.ingrediente.TLiquido;
import negocio.ingrediente.TSolido;


public class modeloListarIngredientes extends AbstractTableModel{
private static final long serialVersionUID = 1L;
	
	private static List<TIngrediente> secc;
	private String[] cols = { "ID", "Nombre", "Codigo", "Tipo", "Activo", "Espesor", "Tamanio"};
	
	public modeloListarIngredientes() {
		secc = new ArrayList<TIngrediente>();
	}
	
	public void update(Collection<TIngrediente> ingredientes) {
		secc = new ArrayList<TIngrediente>();
		Iterator<TIngrediente> it = ingredientes.iterator();
		while (it.hasNext()) {
			TIngrediente pa = it.next();
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
			return secc.get(rowIndex).getCodigo();
		case 3:
			return secc.get(rowIndex).getTipo();
		case 4:
			return secc.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		case 5:
			if(secc.get(rowIndex).getTipo().equalsIgnoreCase("solido")) return ((TSolido) secc.get(rowIndex)).getTamanio();
			else return "-";
		case 6:
			if(secc.get(rowIndex).getTipo().equalsIgnoreCase("liquido")) return ((TLiquido) secc.get(rowIndex)).getEspesor();
			else return "-";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
