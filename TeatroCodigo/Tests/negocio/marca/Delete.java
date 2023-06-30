package negocio.marca;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import negocio.producto.TProductoDurable;

public class Delete {
	
	private static SAMarca saMarca;
	private static SAProducto saProducto;
	
	private static TMarca tMarca = new TMarca("MARCA_BAJA");
	private static TProducto tProducto = new TProductoDurable(-1, -1, "PRODUCTO_TEST_MARCA", 600, 20, true);
	
	@BeforeClass
	public static void init() {
		saMarca = FactoriaAbstractaNegocio.getInstance().createSAMarca();
		saProducto = FactoriaAbstractaNegocio.getInstance().createSAProducto();
		tMarca.setId(saMarca.alta(tMarca));
		tProducto.setIdMarca(tMarca.getId());
	}
	
	@After
	public void reiniciarMarca() {
		saMarca.alta(tMarca);
	}
	
	@Test
	public void eliminarCorrecto() {
		int result = saMarca.eliminar(tMarca.getId());
		try {
			assertTrue(result > 0);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void eliminarIncorrecto() {
		int result = saMarca.eliminar(-1);
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void eliminarInactivo() {
		saMarca.eliminar(tMarca.getId());
		int result = saMarca.eliminar(tMarca.getId());
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void eliminarConProductosActivos() {
		tProducto.setId(saProducto.alta(tProducto));
		int result = saMarca.eliminar(tMarca.getId());
		saProducto.eliminar(tProducto.getId());
		try {
			assertTrue(result == -1);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void eliminarConProductosInactivos() {
		tProducto.setId(saProducto.alta(tProducto));
		saProducto.eliminar(tProducto.getId());
		int result = saMarca.eliminar(tMarca.getId());
		try {
			assertTrue(result > 0);
		} catch(Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saProducto.bajaFisica(tProducto.getId());		
		saMarca.bajaFisica(tMarca.getId());
	}
	
}