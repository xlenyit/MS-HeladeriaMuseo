package negocio.cliente;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class Read {

	private static TCliente tCliente = new TCliente(-1, "09128355G", "Miguel", "Gonzalez Romero", true, true);
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initializeClass() {
		saCliente = FactoriaNegocio.getInstance().createSACliente();
		
		tCliente.setId(saCliente.create(tCliente));
	}
	
	@Test
	public void buscarClienteCorrecto() {
		TCliente resultado = saCliente.read(tCliente.getId());
		try {
			assertNotNull(resultado);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void buscarClienteInactivo() {
		saCliente.delete(tCliente.getId());
		TCliente resultado = saCliente.read(tCliente.getId());
		try {
			assertNull(resultado);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void buscarIdIncorrecto() {
		TCliente resultado = saCliente.read(-1);
		try {
			assertNull(resultado);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {	
		saCliente.deleteFisico(tCliente.getId());
	}
	
}