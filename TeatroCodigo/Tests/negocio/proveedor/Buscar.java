package negocio.proveedor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Buscar {
	private static SAProveedor saProveedor;
	
	private static TProveedor tProveedor = new TProveedor ("50364660L","12345","casa1",true);
	private static TProveedor tproveedorIncorrecto = new TProveedor("","123","casa2",true);
	
	@BeforeClass
	public static void init(){
		saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		
		tProveedor .setId(saProveedor.alta(tProveedor ));
	}
	
	@Test
	public void buscarProveedorCorrecto(){
		TProveedor res = saProveedor.buscar(tProveedor.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarProveedorInactivo(){
		saProveedor.eliminar(tproveedorIncorrecto.getId());
		
		TProveedor res = saProveedor.buscar(tProveedor.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarProveedorIDIncorrecto(){
		TProveedor res = saProveedor.buscar(-1);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saProveedor.bajaFisica(tProveedor.getId());
	}
}
