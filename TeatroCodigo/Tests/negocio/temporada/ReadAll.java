package negocio.temporada;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class ReadAll {
	private static TTemporada tTemporada = new TTemporada(-1, 37, 4, Date.valueOf("2022-08-11"),
			Date.valueOf("2022-08-20"), true);

	private static SATemporada saTemporada;

	@BeforeClass
	public static void inicio() {
		saTemporada = FactoriaAbstractaNegocio.getInstance().createSATemporada();

		tTemporada.setId(saTemporada.create(tTemporada));
	}

	@Test
	public void buscarTemporadaCorrecto() {
		Collection<TTemporada> res = saTemporada.readAll();

		try {
			assertFalse(res.isEmpty());
		} catch (Exception ae) {
			fail(ae.getMessage());
		}
	}

	@AfterClass
	public static void destroyClass() {
		saTemporada.deleteFisico(tTemporada.getId());
	}

}
