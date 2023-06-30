package negocio.cliente;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class ReadAll {

	private static TCliente tCliente = new TCliente(-1, "09128355G", "Miguel", "Gonzalez Romero", true, true);
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initializeClass() {
		saCliente = FactoriaNegocio.getInstance().createSACliente();
		
		tCliente.setId(saCliente.create(tCliente));
	}
	
	@Test
	public void buscarClienteCorrecto() {
		Collection<TCliente> resultado = saCliente.readAll();
		try {
			assertFalse(resultado.isEmpty());
		}
		catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saCliente.deleteFisico(tCliente.getId());
	}
	
}