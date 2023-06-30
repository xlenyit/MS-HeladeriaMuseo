package negocio.compa�ia;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Update {

	private static TCompa�ia tCompa�ia = new TCompa�ia("TestUpdate01", "TestUpdate01", true);
	private static TCompa�ia tCompa�iaMod = new TCompa�ia("TestUpdate01_mod", "TestUpdate01_mod", true);
	private static TCompa�ia tCompa�iaRepetido = new TCompa�ia("TestUpdate01", "TestUpdate01", true);
	
	private static SACompa�ia saCompa�ia;
	
	@BeforeClass
	public static void initalizeClass() {
		saCompa�ia = FactoriaAbstractaNegocio.getInstance().createSACompa�ia();
		
		tCompa�ia.setId(saCompa�ia.create(tCompa�ia));
		tCompa�iaMod.setId(tCompa�ia.getId());
		tCompa�iaRepetido.setId(saCompa�ia.create(tCompa�iaRepetido));
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCompa�ia.update(tCompa�iaMod);
		try {
			assertTrue(resultado > 0);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testInactivo() {
		saCompa�ia.delete(tCompa�ia.getId());
		int resultado = saCompa�ia.update(tCompa�iaMod);
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testRepetido() {
		int resultado = saCompa�ia.update(tCompa�iaRepetido);
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saCompa�ia.deleteFisico(tCompa�ia.getId());
		saCompa�ia.deleteFisico(tCompa�iaRepetido.getId());
	}
	
}