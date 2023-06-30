package integración.compañia;

import java.util.Collection;

import negocio.compañia.TCompañia;

public interface DAOCompañia {

	public int create(TCompañia tCompañia);

	public int delete(int id);

	public TCompañia read(int id);

	public Collection<TCompañia> readAll();

	public TCompañia readByName(String Nombre);

	public int update(TCompañia tCompañia);

	public int deleteFisico(int id);
}