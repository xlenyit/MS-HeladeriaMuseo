package negocio.obra;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
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
public class Read {
	private static final int anio = 2020;
	private static final String sinopsis = "TEST_OBRA_READ";
	private static final String genero = "TEST_OBRA_READ";
	private static final String autor = "TEST_OBRA_READ";
	private static final String titulo = "TEST_OBRA_READ";
	public static int idObra, idCorr, idIncorr, idNoEncontrado;
	private static TObra o;
	private static SAObra sa;
	
	@Parameters
	public static Iterable<Integer[]> getData() {
		return Arrays.asList(new Integer[][]{{-1 , 500}});
	}
	
	public Read(int idIncorrecto, int idNoEncontrado) {
		Read.idIncorr = idIncorrecto;
		Read.idNoEncontrado = idNoEncontrado; 
	}
	
	@BeforeClass
	public static void init() {
		sa = FactoriaNegocio.getInstance().createSAObra();
		o = new TObra(0, genero, sinopsis, titulo, autor, anio,true);
		idObra = sa.create(o);
		if (idObra == -1){
			TObra aux = FactoriaIntegracion.getInstance().createDAOObra().readByName(titulo);
			idObra = aux.getId();
		}
		sa.deleteFisico(idObra);
	}
	
	@Before
	public void initTest() {
		idObra = sa.create(o);
	}
	
	@After
	public void destroy() {
		sa.deleteFisico(idObra);
	}
	
	@AfterClass
	public static void destroyClass() {
		sa.deleteFisico(idObra);
	}
	
	@Test
	public void TestDatosCorrecto() {
		TObra res = sa.read(idObra);
		assertTrue(res.getId() > -1);
	}
	
	@Test
	public void TestDatosNoEncontrado() {
		TObra res = sa.read(idNoEncontrado);
		assertTrue(res == null);
	}
	
	@Test
	public void TestDatosIncorrecto() {
		TObra res = sa.read(idIncorr);
		assertTrue(res == null);
	}
}
