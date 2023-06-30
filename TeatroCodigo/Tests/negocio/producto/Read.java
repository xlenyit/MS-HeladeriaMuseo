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

public class Read {
	private static int idMarca;
	private static Date fecha = Date.valueOf("2000-01-01");
	
	private static TProductoDurable durable = new TProductoDurable(0, idMarca, "TestReadDura", 1, 1, "X", true);
	private static TProductoConsumible consumible = new TProductoConsumible(0, idMarca, "TestReadCons", 1, 1, fecha, true);
	private static SAProducto saProducto;
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void initClass() {
		saProducto = FactoriaNegocio.getInstance().createSAProducto();
		saMarca = FactoriaNegocio.getInstance().createSAMarca();
		idMarca = saMarca.alta(new TMarca(0, "TestReadProducto", true));
	}
	
	@Before
	public void asignar(){
		durable.setIdMarca(idMarca);
		consumible.setIdMarca(idMarca);
	}
	
	@After
	public void reiniciar() {
		saProducto.bajaFisica(durable.getId());
		saProducto.bajaFisica(consumible.getId());
	}
	
	@AfterClass
	public static void destroyClass(){
		saProducto.bajaFisica(durable.getId());
		saProducto.bajaFisica(consumible.getId());
		saMarca.bajaFisica(idMarca);
	}
	
	@Test
	public void readCorrectoDurable() {
		durable.setId(saProducto.alta(durable));
		TProducto p = saProducto.buscar(durable.getId());
		try {
			assertTrue(p != null);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void readCorrectoConsumible() {
		consumible.setId(saProducto.alta(consumible));
		TProducto p = saProducto.buscar(consumible.getId());
		try {
			assertTrue(p != null);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void readDurableInactivo() {
		durable.setId(saProducto.alta(durable));
		saProducto.eliminar(durable.getId());
		TProducto p = saProducto.buscar(durable.getId());
		try {
			assertTrue(p != null);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void readConsumibleInactivo() {
		consumible.setId(saProducto.alta(consumible));
		saProducto.eliminar(consumible.getId());
		TProducto p = saProducto.buscar(consumible.getId());
		try {
			assertTrue(p != null);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void readIncorrecto() {
		TProducto p = saProducto.buscar(-1);
		try {
			assertTrue(p == null);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
}
