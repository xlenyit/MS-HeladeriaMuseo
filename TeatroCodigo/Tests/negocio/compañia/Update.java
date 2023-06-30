package negocio.compañia;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Update {

	private static TCompañia tCompañia = new TCompañia("TestUpdate01", "TestUpdate01", true);
	private static TCompañia tCompañiaMod = new TCompañia("TestUpdate01_mod", "TestUpdate01_mod", true);
	private static TCompañia tCompañiaRepetido = new TCompañia("TestUpdate01", "TestUpdate01", true);
	
	private static SACompañia saCompañia;
	
	@BeforeClass
	public static void initalizeClass() {
		saCompañia = FactoriaAbstractaNegocio.getInstance().createSACompañia();
		
		tCompañia.setId(saCompañia.create(tCompañia));
		tCompañiaMod.setId(tCompañia.getId());
		tCompañiaRepetido.setId(saCompañia.create(tCompañiaRepetido));
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCompañia.update(tCompañiaMod);
		try {
			assertTrue(resultado > 0);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testInactivo() {
		saCompañia.delete(tCompañia.getId());
		int resultado = saCompañia.update(tCompañiaMod);
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testRepetido() {
		int resultado = saCompañia.update(tCompañiaRepetido);
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saCompañia.deleteFisico(tCompañia.getId());
		saCompañia.deleteFisico(tCompañiaRepetido.getId());
	}
	
}