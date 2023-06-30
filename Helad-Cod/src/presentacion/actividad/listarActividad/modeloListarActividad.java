package presentacion.actividad.listarActividad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.actividad.TActividad;
import negocio.actividad.TCharla;
import negocio.actividad.TTaller;
import negocio.exposicion.TExposicion;
import negocio.seccion.TSeccion;


public class modeloListarActividad extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	
	private static List<TActividad> acti;
	private String[] cols = { "ID","Tipo", "Nombre", "Activo", "Codigo", "Fecha", "Utensilio", "Nivel"};
	
	public modeloListarActividad() {
		acti = new ArrayList<TActividad>();
	}
	
	public void update(ArrayList<TActividad> listaE2) {
		acti = new ArrayList<TActividad>();
		Iterator<TActividad> it = listaE2.iterator();
		while (it.hasNext()) {
			TActividad sa = it.next();
			acti.add(sa);
		}
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return acti.size();
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
			return acti.get(rowIndex).getId();
		case 1:
			return (acti.get(rowIndex) instanceof TTaller)?"Taller":"Charla";
		case 2:
			return acti.get(rowIndex).getNombre();
		case 3:
			return acti.get(rowIndex).getActivo() ? "ACTIVO" : "INACTIVO";
		case 4:
			return acti.get(rowIndex).getCodigo();
		case 5:
			return acti.get(rowIndex).getFecha();
		case 6:
			if(acti.get(rowIndex) instanceof TTaller) return ((TTaller) acti.get(rowIndex)).getUtensilios();
			else return "-";
		case 7:
			if(acti.get(rowIndex) instanceof TCharla) return ((TCharla) acti.get(rowIndex)).getNivel();
			else return "-";
		default:
			assert (false);
			return "ERROR";
		}
	}
}
