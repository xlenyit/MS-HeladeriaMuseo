package integraci�n.compa�ia;

import java.util.Collection;

import negocio.compa�ia.TCompa�ia;

public interface DAOCompa�ia {

	public int create(TCompa�ia tCompa�ia);

	public int delete(int id);

	public TCompa�ia read(int id);

	public Collection<TCompa�ia> readAll();

	public TCompa�ia readByName(String Nombre);

	public int update(TCompa�ia tCompa�ia);

	public int deleteFisico(int id);
}