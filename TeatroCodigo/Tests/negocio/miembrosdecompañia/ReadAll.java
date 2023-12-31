package negocio.miembrosdecompaņia;

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
	
	private static TMiembrosDeCompaņia tMiembro_correcto;

	private static SAMiembrosDeCompaņia saMiembrosDeCompaņia;
	
    private static int id;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompaņia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public ReadAll(TMiembrosDeCompaņia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();
	}
	
	@Test
	public void buscarMiembrosCompCorrecto(){
		
		id=saMiembrosDeCompaņia.create(tMiembro_correcto);
		Collection<TMiembrosDeCompaņia> res = saMiembrosDeCompaņia.readAll();
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	
	@AfterClass
	public static void destroyClass()
	{	
		saMiembrosDeCompaņia.deleteFisico(id);
	}
}
