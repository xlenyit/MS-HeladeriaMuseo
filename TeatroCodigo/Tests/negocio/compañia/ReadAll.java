package negocio.compañia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class ReadAll {

	private static TCompañia tCompañia = new TCompañia("TestReadAll01", "TestReadAll01", true);
	private static SACompañia saCompañia;
	
	@BeforeClass
	public static void initializeClass() {
		saCompañia = FactoriaNegocio.getInstance().createSACompañia();
		
		tCompañia.setId(saCompañia.create(tCompañia));
	}
	
	@Test
	public void testCorrecto() {
		Collection<TCompañia> resultado = saCompañia.readAll();
		try {
			assertFalse(resultado.isEmpty());
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