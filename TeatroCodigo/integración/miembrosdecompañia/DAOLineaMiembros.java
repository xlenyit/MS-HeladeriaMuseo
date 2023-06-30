package integraci�n.miembrosdecompa�ia;

import java.util.Collection;

import negocio.miembrosdecompa�ia.TLineaMiembro;

public interface DAOLineaMiembros {
	public int addToCompany(TLineaMiembro tLineaMiembro);

	public int removeFromCompany(TLineaMiembro tLineaMiembro);

	public int updateMeses(TLineaMiembro tLineaMiembro);
	
	public TLineaMiembro read(int idMiembro, int idCompa�ia);
	
	public Collection<TLineaMiembro> readByCompa�ia(int idCompa�ia);
	
	public Collection<TLineaMiembro> readByMiembro(int idMiembro);

	public int deleteFisicoMiembro(int idMiembro);
	
	public int deleteFisicoCompa�ia(int idCompa�ia);
}
