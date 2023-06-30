package negocio.marca;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Read {
	
	private static SAMarca saMarca;
	
	private static TMarca tMarca = new TMarca("MARCA_BUSCAR");
	
	@BeforeClass
	public static void init() {
		saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		tMarca.setId(saMarca.alta(tMarca));
	}
	
	@Test
	public void buscarCorrecto() {
		TMarca result = saMarca.buscar(tMarca.getId());
		try {
			assertNotNull(result);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void buscarInactivo() {
		saMarca.eliminar(tMarca.getId());
		TMarca result = saMarca.buscar(tMarca.getId());
		try {
			assertNotNull(result);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void buscarIDIncorrecto() {
		TMarca result = saMarca.buscar(-1);
		try {
			assertNull(result);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saMarca.bajaFisica(tMarca.getId());
	}
	
}