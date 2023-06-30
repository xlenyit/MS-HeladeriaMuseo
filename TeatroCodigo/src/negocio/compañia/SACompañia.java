package negocio.compa�ia;

import java.util.Collection;

public interface SACompa�ia {
	public int create(TCompa�ia tCompa�ia);

	public TCompa�ia read(int id);

	public int update(TCompa�ia tCompa�ia);

	public int delete(int id);

	public Collection<TCompa�ia> readAll();

	public int deleteFisico(int id);

}