package negocio.marca;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Update {
	
	private static TMarca tMarca = new TMarca("MARCA_MODIFICAR");
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void init() {
		saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		tMarca.setId(saMarca.alta(tMarca));	
	}
	
	@Test
	public void testCorrecto() {
		tMarca.setNombre("MARCA_MODIFICAR_CORRECTO");
		int result = saMarca.modificar(tMarca);
		try {
			assertTrue(result > 0);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
		
	}
	
	@Test
	public void testIncorrecto() {
		int result = saMarca.modificar(new TMarca(-1, "", true));
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testRepetido() {
		TMarca repetida = new TMarca("MARCA_MODIFICAR_REPETIDA");
		repetida.setId(saMarca.alta(repetida));	
		repetida.setNombre(tMarca.getNombre());
		int result = saMarca.modificar(repetida);
		saMarca.bajaFisica(repetida.getId());
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}	
	}
	
	@Test
	public void testEntidadInactiva() {
		saMarca.eliminar(tMarca.getId());
		tMarca.setNombre("MARCA_MODIFICAR");
		int result = saMarca.modificar(tMarca);
		saMarca.alta(tMarca);
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}	
	}
	
	@AfterClass
	public static void destroyClass(){
		saMarca.bajaFisica(tMarca.getId());
	}
	
}