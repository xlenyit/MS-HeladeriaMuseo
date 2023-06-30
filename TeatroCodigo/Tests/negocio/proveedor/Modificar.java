 package negocio.proveedor;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Modificar {
	private static TProveedor tProveedor =new TProveedor("60364660L","22345","casa2",true);
	private static SAProveedor saProveedor;
	@BeforeClass
	public static void init()
	{
		saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		tProveedor.setId(saProveedor.alta(tProveedor));	
	}
	@Test
	public void testCorrecto() {
		
		int res = saProveedor.modificar(tProveedor);
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
		
	}
	@Test
	public void testIncorrecto() {
		
		int res = saProveedor.modificar(new TProveedor("60365660","12", "", true));
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	@Test
	public void testRepetido() {
		TProveedor rep = new TProveedor("60364660L","22345","casa2",true);
		rep.setId(saProveedor.alta(rep));	
		rep.setId(tProveedor.getId());
		int res =saProveedor.modificar(rep);
		saProveedor.bajaFisica(rep.getId());
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}	
	}
	@Test
	public void testEntidadInactiva() {
		saProveedor.eliminar(tProveedor.getId());
		tProveedor.setId(1);
		int res = saProveedor.modificar(tProveedor);
		saProveedor.alta(tProveedor);
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}	
	}
	
	@AfterClass
	public static void destroyClass(){
		saProveedor.bajaFisica(tProveedor.getId());
	}
}
