package negocio.obra;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.factoria.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;

@RunWith(value = Parameterized.class)
public class Create {
	private static final int anio = 2020;
	private static final String sinopsis = "TEST_OBRA_CREATE";
	private static final String genero = "TEST_OBRA_CREATE";
	private static final String autor = "TEST_OBRA_CREATE";
	private static final String titulo = "TEST_OBRA_CREATE";
	public static int idObra;
	private TObra correct, incorrect;
	private static SAObra sa;

	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] { { new TObra(0, genero, sinopsis, titulo, autor, anio, true), null } });
	}

	public Create(TObra corr, TObra incorr) {
		correct = corr;
		incorrect = incorr;
	}

	@BeforeClass
	public static void initClass() {
		sa = FactoriaNegocio.getInstance().createSAObra();
	}

	@Before
	public void initTest() {
		sa.deleteFisico(idObra);
	}

	@AfterClass
	public static void destroyClass() {
		sa.deleteFisico(idObra);
	}

	@Test
	public void testCorrecto() {
		int resultado = sa.create(correct);
		assertTrue(resultado != -1);
		correct.setId(resultado);
		idObra = resultado;
	}

	@Test
	public void testDatosIncorrectos() {
		int resultado = -1;
		resultado = sa.create(incorrect);
		assertTrue(resultado == -1);
	}

	@Test
	public void testDatosExiste() {
		int resultado;
		idObra = sa.create(correct);
		resultado = sa.create(correct);

		assertTrue(resultado == -1);
	}

}
