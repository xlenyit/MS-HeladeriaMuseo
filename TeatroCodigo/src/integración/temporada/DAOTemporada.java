package integración.temporada;

import java.util.Collection;

import negocio.temporada.TTemporada;

public interface DAOTemporada {

	public int create(TTemporada tTemporada);

	public int delete(int id);

	public TTemporada read(int id);

	public Collection<TTemporada> readAll();

	public int update(TTemporada tTemporada);

	public TTemporada readByName(int num);

	public int deleteFisico(int id);
}