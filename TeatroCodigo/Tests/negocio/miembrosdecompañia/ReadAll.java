package negocio.miembrosdecompa�ia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class ReadAll {
	
	private static TMiembrosDeCompa�ia tMiembro_correcto;

	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	
    private static int id;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompa�ia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public ReadAll(TMiembrosDeCompa�ia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();
	}
	
	@Test
	public void buscarMiembrosCompCorrecto(){
		
		id=saMiembrosDeCompa�ia.create(tMiembro_correcto);
		Collection<TMiembrosDeCompa�ia> res = saMiembrosDeCompa�ia.readAll();
		try{
			assertFalse(res.isEmpty());
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
