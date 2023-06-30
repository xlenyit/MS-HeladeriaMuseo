package negocio.compa�ia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class Read {

	private static TCompa�ia tCompa�ia = new TCompa�ia("TestRead01", "TestRead01", true);
	private static SACompa�ia saCompa�ia;
	
	@BeforeClass
	public static void initializeClass() {
		saCompa�ia = FactoriaNegocio.getInstance().createSACompa�ia();
		tCompa�ia.setId(saCompa�ia.create(tCompa�ia));
	}
	
	@Test
	public void testCorrecto() {
		TCompa�ia resultado = saCompa�ia.read(tCompa�ia.getId());
		try {
			assertNotNull(resultado);
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testInactivo() {
		saCompa�ia.delete(tCompa�ia.getId());
		TCompa�ia resultado = saCompa�ia.read(tCompa�ia.getId());
		
		try {
			assertTrue(resultado == null);
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testIdIncorrecto() {
		TCompa�ia resultado = saCompa�ia.read(-1);
		try {
			assertNull(resultado);
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {	
		saCompa�ia.deleteFisico(tCompa�ia.getId());
	}
	
}