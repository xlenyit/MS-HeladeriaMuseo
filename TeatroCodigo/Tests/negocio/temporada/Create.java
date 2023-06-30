package negocio.temporada;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Create {
	private TTemporada temporada_correct;
	private TTemporada temporada_incorrect;
	private static SATemporada saTemporada;

	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] {
				{ new TTemporada(-1, 1, 5, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-16"), true),
						new TTemporada(-1, 1, 5, Date.valueOf("2022-06-11"), Date.valueOf("2021-06-09"), true) } });
	}

	public Create(TTemporada temporada_correct, TTemporada temporada_incorrect) {

		this.temporada_correct = temporada_correct;
		this.temporada_incorrect = temporada_incorrect;

	}

	@BeforeClass
	public static void inicio() {
		saTemporada = FactoriaNegocio.getInstance().createSATemporada();
	}

	@Test
	public void create_correct() {
		int id_producido = saTemporada.create(temporada_correct);
		try {
			assertTrue(id_producido > 0);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
		saTemporada.deleteFisico(id_producido);
	}

	@Test
	public void create_repetido() {
		int id = saTemporada.create(temporada_correct);
		int id_producido = saTemporada.create(temporada_correct);
		saTemporada.deleteFisico(id);
		try {
			assertTrue(id_producido == -1);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
	}

	@Test
	public void create_incorrect() {
		int id_producido = saTemporada.create(temporada_incorrect);
		try {
			assertTrue(id_producido == -1);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
	}
}
