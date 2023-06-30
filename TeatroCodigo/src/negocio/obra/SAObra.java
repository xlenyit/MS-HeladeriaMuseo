package negocio.obra;

import java.util.Collection;

public interface SAObra {
	public int create(TObra t);

	public TObra read(int idObra);

	public int update(TObra t);

	public int delete(int idObra);

	public Collection<TObra> readAll();

	public void deleteFisico(int idObra);

	public Collection<TObra> obraConRepresentacionMasVista();
}