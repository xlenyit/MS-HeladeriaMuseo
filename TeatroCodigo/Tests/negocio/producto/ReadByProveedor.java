package negocio.producto;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TLineaProveedorProducto;
import negocio.proveedor.TProveedor;

public class ReadByProveedor {
	
	private static int idMarca;
	private static int idProducto;
	private static int idProveedor;
	
	private static TProductoDurable producto = new TProductoDurable(0, idMarca, "TestReadProdProv", 1, 1, "X", true);
	private static TProveedor proveedor = new TProveedor(0, "TESTPROD0", "XXX", "TestReadProdProv", true);
	private static TLineaProveedorProducto prov_prod;
	private static SAProducto saProducto;
	private static SAMarca saMarca;
	private static SAProveedor saProveedor;
	
	@BeforeClass
	public static void initClass() {
		saProducto = FactoriaNegocio.getInstance().createSAProducto();
		saMarca = FactoriaNegocio.getInstance().createSAMarca();
		saProveedor = FactoriaNegocio.getInstance().createSAProveedor();
		idMarca = saMarca.alta(new TMarca(0, "TestProdProv", true));
		producto.setIdMarca(idMarca);
		idProducto = saProducto.alta(producto);
		idProveedor = saProveedor.alta(proveedor);

		prov_prod = new TLineaProveedorProducto (idProducto, idProveedor);
		saProveedor.asignarProveedorAProducto(prov_prod);
	}
	
	@AfterClass
	public static void destroyClass(){
		saProveedor.desasignarProveedorDeProducto(prov_prod);
		saProducto.bajaFisica(idProducto);
		saMarca.bajaFisica(idMarca);
		saProveedor.bajaFisica(idProveedor);
	}
	
	@Test
	public void readByProvCorrecto() {
		Collection<TProducto> res = saProducto.mostrarProdPorProveedor(idProveedor);
		try {
			assertTrue(!res.isEmpty());
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void readByProvIncorrecto() {
		Collection<TProducto> res = saProducto.mostrarProdPorProveedor(-1);
		try {
			assertTrue(res.isEmpty());
		} catch(Exception ae){
			fail(ae.getMessage());
		}
	}


}
