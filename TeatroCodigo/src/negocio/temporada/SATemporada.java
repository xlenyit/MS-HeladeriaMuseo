package negocio.temporada;

import java.util.Collection;

public interface SATemporada {

	public int create(TTemporada t);

	public TTemporada read(int idTemporada);

	public int update(TTemporada t);

	public int delete(int idTemporada);

	public Collection<TTemporada> readAll();

	public int deleteFisico(int id);
}