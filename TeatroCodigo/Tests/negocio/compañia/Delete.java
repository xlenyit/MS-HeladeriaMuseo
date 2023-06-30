package negocio.compa�ia;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.factoria.FactoriaNegocio;
import negocio.miembrosdecompa�ia.SAMiembrosDeCompa�ia;
import negocio.miembrosdecompa�ia.TLineaMiembro;
import negocio.miembrosdecompa�ia.TMiembrosDeCompa�ia;

@RunWith(Parameterized.class)
public class Delete {

	private TCompa�ia correct;
	private TCompa�ia incorrect;
	private TCompa�ia notFound;
	private TMiembrosDeCompa�ia asignado;
	private static TLineaMiembro tLineaMiembro;
	private static SACompa�ia saCompa�ia;
	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] {
			{ new TCompa�ia("TestDelete01", "TestDelete01", true),
				new TCompa�ia("TestDelete02", "TestDelete02", true),
				new TCompa�ia("TestDelete03", "TestDelete03", true), 
				new TMiembrosDeCompa�ia(-1, "TestDeleteAux", "TestDeleteAux", "TestDeleteAux", "00000000X", true)}
		});
	}
	
	public Delete(TCompa�ia correct, TCompa�ia incorrect, TCompa�ia notFound, TMiembrosDeCompa�ia asignado) {
		this.correct = correct;
		this.incorrect = incorrect;
		this.notFound = notFound;
		this.asignado = asignado;
	}
	
	@BeforeClass
	public static void initializeClass() {
		saCompa�ia = FactoriaNegocio.getInstance().createSACompa�ia();
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();	
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCompa�ia.create(correct);
		resultado = saCompa�ia.delete(resultado);
		
		try {
			assertTrue(resultado > 0);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
		saCompa�ia.deleteFisico(resultado);
	}
	
	@Test
	public void testNoEncontrado() {
		int resultado = saCompa�ia.delete(notFound.getId());
		try {
			assertTrue(resultado == -1);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCompa�ia.delete(incorrect.getId());
		try {
			assertTrue(resultado == -1);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
	}
	
	@Test
	public void testMiembroAsignado() {
		int idAsignado = saMiembrosDeCompa�ia.create(asignado);
		int idComp = saCompa�ia.create(correct);
		tLineaMiembro = new TLineaMiembro(idComp, idAsignado, 10);
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro);
		
		int resultado = saCompa�ia.delete(idComp);
		try {
			assertTrue(resultado > 0);
		}
		catch (AssertionError assertionError) {
			fail(assertionError.getMessage());
		}
		saMiembrosDeCompa�ia.deleteFisico(idAsignado);
		saCompa�ia.deleteFisico(idComp);
	}
	
}