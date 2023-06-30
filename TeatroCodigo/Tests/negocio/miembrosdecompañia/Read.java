package negocio.miembrosdecompa�ia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Read {
	
	private static TMiembrosDeCompa�ia tMiembro_correcto;
	private static TMiembrosDeCompa�ia tMiembro_incorrecto;	
	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	
    private static TCompa�ia tComp1 = new TCompa�ia(-1, "TCreate1", "Comedia", true);
    private static TCompa�ia tComp2 = new TCompa�ia(-1, "TCreate2", "Drama", true);
    
    private static int id;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompa�ia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public Read(TMiembrosDeCompa�ia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();
	}
	
	@Test
	public void buscarMiembroCorrecto(){
		
		
		id= saMiembrosDeCompa�ia.create(tMiembro_correcto);
		TMiembrosDeCompa�ia res = saMiembrosDeCompa�ia.read(id);
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	

	
	@Test
	public void buscarMiembroNoActivo(){
		
		id= saMiembrosDeCompa�ia.create(tMiembro_correcto);
		saMiembrosDeCompa�ia.delete(id);
		TMiembrosDeCompa�ia res = saMiembrosDeCompa�ia.read(id);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
		
	@Test
	public void buscarIdIncorrecto(){
		id= saMiembrosDeCompa�ia.create(tMiembro_correcto);
		TMiembrosDeCompa�ia res = saMiembrosDeCompa�ia.read(-1);
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	

	@AfterClass
	public static void destroyClass()
	{	
		saMiembrosDeCompa�ia.deleteFisico(id);
	}
	

}
