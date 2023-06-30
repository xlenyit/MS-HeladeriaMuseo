package negocio.proveedor;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Alta {
	
	private static SAProveedor saProveedor;
	
	private static TProveedor tproveedor = new TProveedor("50364660L","12345","casa1",true);
	private static TProveedor tproveedorIncorrecto = new TProveedor("","123","casa2",true);
	
	@BeforeClass
	public static void init(){
		saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
	}
	
	@After
	public void reiniciarProveedor(){
		saProveedor.eliminar(tproveedor.getId());
	}
	
	@Test
	public void altaProveedorCorrecto(){
		tproveedor.setId(saProveedor.alta(tproveedor));
		
		try{
			assertTrue(tproveedor.getId() > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaProveedorIncorrecto(){
		int res = saProveedor.alta(tproveedorIncorrecto);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaProveedorInactivo(){
		tproveedor.setId(saProveedor.alta(tproveedor));
		
		saProveedor.eliminar(tproveedor.getId());
		
		tproveedor.setId(saProveedor.alta(tproveedor));
		
		try{
			assertTrue(tproveedor.getId() > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaProveedorRepetido(){
		tproveedor.setId(saProveedor.alta(tproveedor));
		int res = saProveedor.alta(tproveedor);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saProveedor.bajaFisica(tproveedor.getId());
	}
}