package negocio.producto;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;

public class Delete {
	
	private static int idMarca;
	private static TProductoDurable producto = new TProductoDurable(0, idMarca, "TestBajaProducto", 1, 1, "W", true);
	private static SAProducto saProducto;
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void initClass() {
		saProducto = FactoriaNegocio.getInstance().createSAProducto();
		saMarca = FactoriaNegocio.getInstance().createSAMarca();
		idMarca = saMarca.alta(new TMarca(0, "TestBajaProducto", true));
		producto.setIdMarca(idMarca);
		producto.setId(saProducto.alta(producto));
	}
	
	@After
	public void reiniciar() {
		producto.setIdMarca(idMarca);
		saProducto.alta(producto);
	}
	
	@AfterClass
	public static void destroyClass(){
		saProducto.bajaFisica(producto.getId());
		saMarca.bajaFisica(idMarca);
	}
	
	@Test
	public void altaCorrectoDurable() {	
		int res = saProducto.eliminar(producto.getId());
		try {
			assertTrue(res > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaIncorrecto() {
		int res = saProducto.eliminar(-1);
		try {
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaInactivo() {
		saProducto.eliminar(producto.getId());
		int res = saProducto.eliminar(producto.getId());
		try{
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
}
