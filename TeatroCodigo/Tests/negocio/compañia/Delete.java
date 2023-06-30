package negocio.compañia;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.factoria.FactoriaNegocio;
import negocio.miembrosdecompañia.SAMiembrosDeCompañia;
import negocio.miembrosdecompañia.TLineaMiembro;
import negocio.miembrosdecompañia.TMiembrosDeCompañia;

@RunWith(Parameterized.class)
public class Delete {

	private TCompañia correct;
	private TCompañia incorrect;
	private TCompañia notFound;
	private TMiembrosDeCompañia asignado;
	private static TLineaMiembro tLineaMiembro;
	private static SACompañia saCompañia;
	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] {
			{ new TCompañia("TestDelete01", "TestDelete01", true),
				new TCompañia("TestDelete02", "TestDelete02", true),
				new TCompañia("TestDelete03", "TestDelete03", true), 
				new TMiembrosDeCompañia(-1, "TestDeleteAux", "TestDeleteAux", "TestDeleteAux", "00000000X", true)}
		});
	}
	
	public Delete(TCompañia correct, TCompañia incorrect, TCompañia notFound, TMiembrosDeCompañia asignado) {
		this.correct = correct;
		this.incorrect = incorrect;
		this.notFound = notFound;
		this.asignado = asignado;
	}
	
	@BeforeClass
	public static void initializeClass() {
		saCompañia = FactoriaNegocio.getInstance().createSACompañia();
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();	
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCompañia.create(correct);
		resultado = saCompañia.delete(resultado);
		
		try {
			assertTrue(resultado > 0);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
		saCompañia.deleteFisico(resultado);
	}
	
	@Test
	public void testNoEncontrado() {
		int resultado = saCompañia.delete(notFound.getId());
		try {
			assertTrue(resultado == -1);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCompañia.delete(incorrect.getId());
		try {
			assertTrue(resultado == -1);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
	}
	
	@Test
	public void testMiembroAsignado() {
		int idAsignado = saMiembrosDeCompañia.create(asignado);
		int idComp = saCompañia.create(correct);
		tLineaMiembro = new TLineaMiembro(idComp, idAsignado, 10);
		saMiembrosDeCompañia.addToCompany(tLineaMiembro);
		
		int resultado = saCompañia.delete(idComp);
		try {
			assertTrue(resultado > 0);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
		saMiembrosDeCompañia.deleteFisico(idAsignado);
		saCompañia.deleteFisico(idComp);
	}
	
}