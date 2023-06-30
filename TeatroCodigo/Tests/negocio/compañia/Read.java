package negocio.compañia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class Read {

	private static TCompañia tCompañia = new TCompañia("TestRead01", "TestRead01", true);
	private static SACompañia saCompañia;
	
	@BeforeClass
	public static void initializeClass() {
		saCompañia = FactoriaNegocio.getInstance().createSACompañia();
		tCompañia.setId(saCompañia.create(tCompañia));
	}
	
	@Test
	public void testCorrecto() {
		TCompañia resultado = saCompañia.read(tCompañia.getId());
		try {
			assertNotNull(resultado);
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testInactivo() {
		saCompañia.delete(tCompañia.getId());
		TCompañia resultado = saCompañia.read(tCompañia.getId());
		
		try {
			assertTrue(resultado == null);
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testIdIncorrecto() {
		TCompañia resultado = saCompañia.read(-1);
		try {
			assertNull(resultado);
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {	
		saCompañia.deleteFisico(tCompañia.getId());
	}
	
}