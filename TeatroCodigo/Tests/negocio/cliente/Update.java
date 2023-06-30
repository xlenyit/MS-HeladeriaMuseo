package negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Update {

	private static TCliente tCliente = new TCliente(-1, "09837595A", "Julian", "Gomez Gomez", true, true);
	private static TCliente tClienteModificacion = new TCliente(-1, "29034868J", "Julian", "Gomez Gomez", true, true);
	private static TCliente tClienteRepOriginal = new TCliente(-1, "06615945D", "Julian", "Gomez Gomez", true, true);
	private static TCliente tClienteRepetido = new TCliente(-1, "09837595A", "Julian", "Gomez Gomez", true, true);
	
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initalizeClass() {
		saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
		
		tCliente.setId(saCliente.create(tCliente));
		tClienteModificacion.setId(tCliente.getId());
	}
	
	@After
	public void reiniciarActividades() {
		saCliente.update(tCliente);
	}
	
	@Test
	public void updateClienteCorrecto() {
		int resultado = saCliente.update(tClienteModificacion);
		try {
			assertTrue(resultado > 0);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void updateClienteInactivo() {
		saCliente.delete(tCliente.getId());
		int resultado = saCliente.update(tClienteModificacion);
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void updateClienteRepetido() {
		tClienteRepetido.setId(saCliente.create(tClienteRepOriginal));
		int resultado = saCliente.update(tClienteRepetido);
		try {
			assertTrue(resultado == -1);
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void updateClienteDatosIncorrectos(){
		String dniOriginal = tClienteModificacion.getDni();
		tClienteModificacion.setDni("2390475H");
		
		int resultado = saCliente.update(tClienteModificacion);
		
		tClienteModificacion.setDni(dniOriginal);
		try {
			assertTrue(resultado == -1);
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