package negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class Delete {

	private static TCliente correct = new TCliente(-1, "09128355G", "Miguel", "Gonzalez Romero", true, true);
	private static TCliente incorrect = new TCliente(-1, "0912835G", "Manuel", "Gonzalez Romero", true, true);
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initializeClass() {
		saCliente = FactoriaNegocio.getInstance().createSACliente();

		correct.setId(saCliente.create(correct));
		incorrect.setId(saCliente.create(incorrect));
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCliente.delete(correct.getId());
		try {
			assertTrue(resultado > 0);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCliente.delete(incorrect.getId());
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {	
		saCliente.deleteFisico(correct.getId());
		saCliente.deleteFisico(incorrect.getId());
	}
	
}