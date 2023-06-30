package negocio.marca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class ReadAll {
	
	private static TMarca tMarca = new TMarca("MARCA_LISTAR");
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void init(){
		saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		tMarca.setId(saMarca.alta(tMarca));
	}
	
	@Test
	public void listarCorrecto() {
		System.out.println();
		Collection<TMarca> result = saMarca.mostrarMarcas();
		try {
			assertFalse(result.isEmpty());
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void listarInactivo() {
		saMarca.eliminar(tMarca.getId());
		Collection<TMarca> result = saMarca.mostrarMarcas();
		try {
			assertFalse(result.isEmpty());
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saMarca.bajaFisica(tMarca.getId());
	}
	
}