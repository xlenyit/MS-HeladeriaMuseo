package negocio.miembrosdecompañia;

import java.util.Collection;
import java.util.Iterator;

import negocio.compañia.TCompañia;

public class TMiembroEnCompañia {

	private Collection<TMiembrosDeCompañia> tMiembrosDeCompañia; //HassMap
	
	private TCompañia tCompañia;
	
	private Collection<TLineaMiembro> tLineaMiembro;

	public TMiembroEnCompañia(Collection<TMiembrosDeCompañia> tMiembrosDeCompañia, TCompañia tCompañia, Collection<TLineaMiembro> tLineaMiembro) {
		this.tMiembrosDeCompañia = tMiembrosDeCompañia;
		this.tCompañia = tCompañia;
		this.tLineaMiembro = tLineaMiembro;
	}
	
	public void setTMiembrosDeCompañia (Collection<TMiembrosDeCompañia> tMiembrosDeCompañia) {
		this.tMiembrosDeCompañia = tMiembrosDeCompañia;
	}
	
	public Collection<TMiembrosDeCompañia> getTMiembrosDeCompañia() {
		return this.tMiembrosDeCompañia;
	}
	
	public void setTCompañia (TCompañia tCompañia) {
		this.tCompañia = tCompañia;
	}
	
	public TCompañia getTCompañia () {
		return this.tCompañia;
	}
	
	public void setTLineaMiembro (Collection<TLineaMiembro> tLineaMiembro) {
		this.tLineaMiembro = tLineaMiembro;
	}
	
	public Collection<TLineaMiembro> getTLineaMiembro() {
		return this.tLineaMiembro;
	}
	
	public String toString() {
		String info = "";
		
		for(TMiembrosDeCompañia miembro : tMiembrosDeCompañia) {
			info += miembro.toString();
			TLineaMiembro linea = null;
			Iterator <TLineaMiembro> it = tLineaMiembro.iterator();
			boolean encontrado = false;
			while (!encontrado) {
				linea = it.next();
				if (linea.getIdMiembro() == miembro.getId()) encontrado = true;
			}
			info += "Número de meses asignados: " + linea.getNMeses() + '\n';
		}
		
		info += '\n';
		
		return info;
		
	}

}