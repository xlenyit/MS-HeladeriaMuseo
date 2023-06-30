package negocio.marca;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Create {
	
	private static SAMarca saMarca;
	private static TMarca correcta = new TMarca("MARCA_ALTA");
	private static TMarca incorrecta = new TMarca("");
	
	@BeforeClass
	public static void init() {
		saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
	}
	
	@After
	public void reiniciarMarca() {
		saMarca.eliminar(correcta.getId());
	}
	
	@Test
	public void altaCorrecto() {
		correcta.setId(saMarca.alta(correcta));
		try {
			assertTrue(correcta.getId() > 0);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void altaIncorrecto() {
		int result = saMarca.alta(incorrecta);
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void altaInactivo() {
		correcta.setId(saMarca.alta(correcta));
		saMarca.eliminar(correcta.getId());
		correcta.setId(saMarca.alta(correcta));
		try {
			assertTrue(correcta.getId() > 0);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void altaRepetido() {
		correcta.setId(saMarca.alta(correcta));
		int result = saMarca.alta(correcta);
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saMarca.bajaFisica(correcta.getId());
	}
	
}