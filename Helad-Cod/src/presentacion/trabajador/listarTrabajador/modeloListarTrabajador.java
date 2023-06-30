package presentacion.trabajador.listarTrabajador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.trabajador.TTrabajador;


public class modeloListarTrabajador extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<TTrabajador> trab;
	private String[] cols = { "ID", "DNI", "Nombre", "Telefono", "Seccion", "Activo"};//aniadir seccion?
	
	public modeloListarTrabajador() {
		trab = new ArrayList<TTrabajador>();
	}
	
	public void update(Collection<TTrabajador> productos) {
		trab = new ArrayList<TTrabajador>();
		Iterator<TTrabajador> it = productos.iterator();
		while (it.hasNext()) {
			TTrabajador pa = it.next();
			trab.add(pa);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return trab.size();
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
			return trab.get(rowIndex).getId();
		case 1:
			return trab.get(rowIndex).getDNI();
		case 2:
			return trab.get(rowIndex).getNombre();
		case 3:
			return trab.get(rowIndex).getTelefono();
		case 4:
			return trab.get(rowIndex).getIdSeccion();
		case 5:
			return trab.get(rowIndex).getActive() ? "ACTIVO" : "INACTIVO";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
