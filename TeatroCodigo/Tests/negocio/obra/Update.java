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

import integración.factoria.FactoriaIntegracion;
import negocio.factoria.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;

@RunWith(value = Parameterized.class)
public class Update {
	private static final int anio = 2020;
	private static final int anio2 = 2021;
	private static final String sinopsis = "TEST_OBRA_UPDATE";
	private static final String sinopsis2 = "TEST_OBRA_UPDATE2";
	private static final String genero = "TEST_OBRA_UPDATE";
	private static final String genero2 = "TEST_OBRA_UPDATE2";
	private static final String autor = "TEST_OBRA_UPDATE";
	private static final String autor2 = "TEST2_OBRA_UPDATE";
	private static final String titulo = "TEST_OBRA_UPDATE";
	private static final String titulo2 = "TEST_OBRA_UPDATE2";
	public static int idObra, idObraRep;
	private static TObra o, oC, oI;
	private static SAObra sa;

	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays
				.asList(new Object[][] { { new TObra(0, genero2, sinopsis2, titulo2, autor2, anio2, true), null } });
	}

	public Update(TObra corr, TObra incorr) {
		oC = corr;
		oI = incorr;
	}

	@BeforeClass
	public static void initClass() {
		sa = FactoriaNegocio.getInstance().createSAObra();
		o = new TObra(0, genero, sinopsis, titulo, autor, anio, true);
		idObra = sa.create(o);
		if (idObra == -1) {
			TObra aux = FactoriaIntegracion.getInstance().createDAOObra().readByName(titulo);
			idObra = aux.getId();
			sa.deleteFisico(idObra);
		}
		while (idObra != -1) {
			TObra aux = FactoriaIntegracion.getInstance().createDAOObra().readByName(titulo);
			if (aux != null) {
				idObra = aux.getId();
				sa.deleteFisico(idObra);
			} else {
				idObra = -1;
			}

		}
	}

	@Before
	public void initTest() {
		sa.deleteFisico(idObra);
		o = new TObra(0, genero, sinopsis, titulo, autor, anio, true);
		idObra = sa.create(o);
	}

	@AfterClass
	public static void destroyClass() {
		sa.deleteFisico(idObra);
	}
	
	@Test
	public void correcto() {
		oC.setId(idObra);
		int res;
		res = sa.update(oC);
		assertTrue(res != -1);
	}

	@Test
	public void TestDatosNoActivo() {
		int res;
		sa.delete(idObra);
		oC.setId(idObra);
		res = sa.update(oC);
		assertTrue(res == -1);
	}

	@Test
	public void TestDatosIncorrecto() {
		int res = -1;
		res = sa.update(oI);
		assertTrue(res == -1);
	}

}
