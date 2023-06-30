package negocio.producto;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;

public class Update {
	private static int idMarca;
	private static String tipo = "TestUptDura";
	private static String tipoMod = "TestUptDuraMod";
	private static Date fecha = Date.valueOf("2000-01-01");
	private static Date fechaMod = Date.valueOf("2021-12-31");
	
	private static TProductoDurable durable = new TProductoDurable(0, idMarca, "TestUptDura", 1, 1, tipo, true);
	private static TProductoConsumible consumible = new TProductoConsumible(0, idMarca, "TestUptCons", 1, 1, fecha, true);
	private static SAProducto saProducto;
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void initClass() {
		saProducto = FactoriaNegocio.getInstance().createSAProducto();
		saMarca = FactoriaNegocio.getInstance().createSAMarca();
		
		idMarca = saMarca.alta(new TMarca("TestUptProducto"));
		
		durable.setIdMarca(idMarca);
		durable.setId(saProducto.alta(durable));
		
		consumible.setIdMarca(idMarca);
		consumible.setId(saProducto.alta(consumible));
	}
	
	@Test
	public void updateDurableCorrecto() {
		durable.setTipo(tipoMod);
		
		int res = saProducto.modificar(durable);
		
		durable.setTipo(tipo);
		
		try {
			assertTrue(res > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateConsumibleCorrecto() {
		consumible.setTiempoCaducidad(fechaMod);
		
		int res = saProducto.modificar(consumible);
		
		consumible.setTiempoCaducidad(fecha);
		
		try {
			assertTrue(res > 0);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateDurableIncorrecto() {
		durable.setIdMarca(-1);
		
		int res = saProducto.modificar(durable);
		
		durable.setIdMarca(idMarca);
		
		try {
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateConsumibleIncorrecto() {
		consumible.setIdMarca(-1);
		
		int res = saProducto.modificar(consumible);
		
		durable.setIdMarca(idMarca);
		try {
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateRepetido() {
		String nombre = durable.getNombre();
		durable.setNombre(consumible.getNombre());
		
		int res = saProducto.modificar(durable);
		
		durable.setNombre(nombre);
		try {
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateInactivo()
	{
		saProducto.eliminar(durable.getId());
		
		durable.setTipo(tipoMod);
		
		int res = saProducto.modificar(durable);
		
		durable.setTipo(tipo);
		
		saProducto.alta(durable);
		
		try {
			assertTrue(res == -1);
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saProducto.bajaFisica(durable.getId());
		saProducto.bajaFisica(consumible.getId());
		
		saMarca.bajaFisica(idMarca);
	}
}
