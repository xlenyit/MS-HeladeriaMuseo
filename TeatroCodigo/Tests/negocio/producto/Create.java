package negocio.producto;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;

public class Create {
	
	private static int idMarca;
	private static Date fecha = Date.valueOf("2000-01-01");
	
	private static TProductoDurable durableOk = new TProductoDurable(0, idMarca, "TestAltaDuraOK", 1, 1, "X", true);
	private static TProductoDurable durableKO = new TProductoDurable(0, -1, "TestAltaDuraKO", 1, 1, "X1", true);
	private static TProductoConsumible consumibleOK = new TProductoConsumible(0, idMarca, "TestAltaConsuOK", 1, 1, fecha, true);
	private static TProductoConsumible consumibleKO = new TProductoConsumible(0, -1, "TestAltaConsuKO", 1, 1, fecha, true);
	private static SAProducto saProducto;
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void initClass() {
		saProducto = FactoriaNegocio.getInstance().createSAProducto();
		saMarca = FactoriaNegocio.getInstance().createSAMarca();
		idMarca = saMarca.alta(new TMarca(0, "TestAltaProducto", true));
	}
	
	@Before
	public void asignar(){
		durableOk.setIdMarca(idMarca);
		consumibleOK.setIdMarca(idMarca);
	}
	
	@After
	public void reiniciar() {
		saProducto.bajaFisica(durableOk.getId());
		saProducto.bajaFisica(consumibleOK.getId());
	}
	
	@AfterClass
	public static void destroyClass(){
		saProducto.bajaFisica(durableOk.getId());
		saProducto.bajaFisica(consumibleOK.getId());
		saMarca.bajaFisica(idMarca);
	}
	
	@Test
	public void altaCorrectoDurable() {
		durableOk.setId(saProducto.alta(durableOk));
		
		try {
			assertTrue(durableOk.getId() > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaCorrectoConsumible() {
		consumibleOK.setId(saProducto.alta(consumibleOK));
		try {
			assertTrue(consumibleOK.getId() > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaIncorrectoDurable() {
		int res = saProducto.alta(durableKO);
		try{
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaIncorrectoConsumible() {
		int res = saProducto.alta(consumibleKO);
		try{
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaDurablelInactivo() {
		durableOk.setId(saProducto.alta(durableOk));
		saProducto.eliminar(durableOk.getId());
		durableOk.setId(saProducto.alta(durableOk));
		try{
			assertTrue(durableOk.getId() > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaConsumibleInactivo() {
		consumibleOK.setId(saProducto.alta(consumibleOK));
		saProducto.eliminar(consumibleOK.getId());
		consumibleOK.setId(saProducto.alta(consumibleOK));
		try{
			assertTrue(consumibleOK.getId() > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
}
