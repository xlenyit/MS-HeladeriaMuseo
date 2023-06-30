package negocio.obra;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import integración.factoria.FactoriaIntegracion;
import negocio.factoria.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;

public class Delete {
	private static final int anio = 2020;
	private static final String sinopsis = "TEST_OBRA_DELETE";
	private static final String genero = "TEST_OBRA_DELETE";
	private static final String autor = "TEST_OBRA_DELETE";
	private static final String titulo = "TEST_OBRA_DELETE";
	public static int idObra, idObraRep;
	private static TObra o;
	private static SAObra sa;
	
	@BeforeClass
	public static void initClass() {
		sa = FactoriaNegocio.getInstance().createSAObra();
		o = new TObra(0, genero, sinopsis, titulo, autor, anio,true);
		idObra = sa.create(o);
		if (idObra == -1){
			TObra aux = FactoriaIntegracion.getInstance().createDAOObra().readByName(titulo);
			idObra = aux.getId();
		}
		sa.deleteFisico(idObra);
		/*
		idObra = sa.create(o);
		if (idObra == -1){
			TObra aux = FactoriaIntegracion.getInstance().createDAOObra().readByName(titulo);
			idObra = aux.getId();
		}
		*/
	}
	
	@Before
	public void initTest() {
		idObra = sa.create(o);
	}
	
	@After
	public void destroyTest() {
		sa.deleteFisico(idObra);
	}

	
	@AfterClass
	public static void destroyClass() {
		sa.deleteFisico(idObra);
	}
	
	@Test
	public void testCorrecto()
	{
		int resultado = 0;
		resultado = sa.delete(idObra);
		assertTrue(resultado != -1);
	}
	
	@Test
	public void testDatosIncorrectos()
	{
		int resultado;
		resultado = sa.delete(-1);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testDatosNoEncontrado()
	{
		int resultado;
		sa.delete(idObra);
		resultado = sa.delete(idObra);
		assertTrue(resultado == -1);
	}
}
