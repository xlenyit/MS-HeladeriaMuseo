package negocio.turno;

import java.util.Collection;

public interface SATurno {
	public int alta(TTurno tTurno);

	public TTurno buscar(int id);

	public int modificar(TTurno tTurno);

	public int eliminar(int id);

	public Collection<TTurno> mostrarTurnos();

	public int calcularNomina(int id);

	public void bajaFisica(int id);
}
