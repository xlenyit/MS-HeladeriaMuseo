package negocio.miembrosdecompa�ia;

import java.util.Collection;
import java.util.Iterator;

import negocio.compa�ia.TCompa�ia;

public class TMiembroEnCompa�ia {

	private Collection<TMiembrosDeCompa�ia> tMiembrosDeCompa�ia; //HassMap
	
	private TCompa�ia tCompa�ia;
	
	private Collection<TLineaMiembro> tLineaMiembro;

	public TMiembroEnCompa�ia(Collection<TMiembrosDeCompa�ia> tMiembrosDeCompa�ia, TCompa�ia tCompa�ia, Collection<TLineaMiembro> tLineaMiembro) {
		this.tMiembrosDeCompa�ia = tMiembrosDeCompa�ia;
		this.tCompa�ia = tCompa�ia;
		this.tLineaMiembro = tLineaMiembro;
	}
	
	public void setTMiembrosDeCompa�ia (Collection<TMiembrosDeCompa�ia> tMiembrosDeCompa�ia) {
		this.tMiembrosDeCompa�ia = tMiembrosDeCompa�ia;
	}
	
	public Collection<TMiembrosDeCompa�ia> getTMiembrosDeCompa�ia() {
		return this.tMiembrosDeCompa�ia;
	}
	
	public void setTCompa�ia (TCompa�ia tCompa�ia) {
		this.tCompa�ia = tCompa�ia;
	}
	
	public TCompa�ia getTCompa�ia () {
		return this.tCompa�ia;
	}
	
	public void setTLineaMiembro (Collection<TLineaMiembro> tLineaMiembro) {
		this.tLineaMiembro = tLineaMiembro;
	}
	
	public Collection<TLineaMiembro> getTLineaMiembro() {
		return this.tLineaMiembro;
	}
	
	public String toString() {
		String info = "";
		
		for(TMiembrosDeCompa�ia miembro : tMiembrosDeCompa�ia) {
			info += miembro.toString();
			TLineaMiembro linea = null;
			Iterator <TLineaMiembro> it = tLineaMiembro.iterator();
			boolean encontrado = false;
			while (!encontrado) {
				linea = it.next();
				if (linea.getIdMiembro() == miembro.getId()) encontrado = true;
			}
			info += "N�mero de meses asignados: " + linea.getNMeses() + '\n';
		}
		
		info += '\n';
		
		return info;
		
	}

}