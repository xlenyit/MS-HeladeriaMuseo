package negocio.compa�ia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class ReadAll {

	private static TCompa�ia tCompa�ia = new TCompa�ia("TestReadAll01", "TestReadAll01", true);
	private static SACompa�ia saCompa�ia;
	
	@BeforeClass
	public static void initializeClass() {
		saCompa�ia = FactoriaNegocio.getInstance().createSACompa�ia();
		
		tCompa�ia.setId(saCompa�ia.create(tCompa�ia));
	}
	
	@Test
	public void testCorrecto() {
		Collection<TCompa�ia> resultado = saCompa�ia.readAll();
		try {
			assertFalse(resultado.isEmpty());
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