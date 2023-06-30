package integración.miembrosdecompañia;

import java.util.Collection;

import negocio.miembrosdecompañia.TLineaMiembro;

public interface DAOLineaMiembros {
	public int addToCompany(TLineaMiembro tLineaMiembro);

	public int removeFromCompany(TLineaMiembro tLineaMiembro);

	public int updateMeses(TLineaMiembro tLineaMiembro);
	
	public TLineaMiembro read(int idMiembro, int idCompañia);
	
	public Collection<TLineaMiembro> readByCompañia(int idCompañia);
	
	public Collection<TLineaMiembro> readByMiembro(int idMiembro);

	public int deleteFisicoMiembro(int idMiembro);
	
	public int deleteFisicoCompañia(int idCompañia);
}
