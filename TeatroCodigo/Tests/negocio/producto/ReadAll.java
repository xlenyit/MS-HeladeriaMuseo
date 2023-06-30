package negocio.producto;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;

public class ReadAll {
	private static int idMarca;
	private static Date fecha = Date.valueOf("2000-01-01");
	
	private static TProductoDurable durable = new TProductoDurable(0, idMarca, "TestReadAllDura", 1, 1, "X", true);
	private static TProductoConsumible consumible = new TProductoConsumible(0, idMarca, "TestReadAllCons", 1, 1, fecha, true);
	private static SAProducto saProducto;
	private static SAMarca saMarca;
	
	@BeforeClass
	public static void initClass() {
		saProducto = FactoriaNegocio.getInstance().createSAProducto();
		saMarca = FactoriaNegocio.getInstance().createSAMarca();
		idMarca = saMarca.alta(new TMarca(0, "TestReadAllProducto", true));
		durable.setIdMarca(idMarca);
		consumible.setIdMarca(idMarca);
		durable.setId(saProducto.alta(durable));
		consumible.setId(saProducto.alta(consumible));
	}
	
	@AfterClass
	public static void destroyClass(){
		saProducto.bajaFisica(durable.getId());
		saProducto.bajaFisica(consumible.getId());
		saMarca.bajaFisica(idMarca);
	}
	
	@Test
	public void readAllCorrecto() {
		Collection<TProducto> res = saProducto.mostrar();
		try {
			assertTrue(!res.isEmpty());
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
}
