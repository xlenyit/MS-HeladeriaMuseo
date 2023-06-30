package negocio.compañia;

import java.util.Collection;

public interface SACompañia {
	public int create(TCompañia tCompañia);

	public TCompañia read(int id);

	public int update(TCompañia tCompañia);

	public int delete(int id);

	public Collection<TCompañia> readAll();

	public int deleteFisico(int id);

}