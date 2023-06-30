package negocio.miembrosdecompañia;

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
	
	private static TMiembrosDeCompañia tMiembro_correcto;

	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	
    private static int id;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompañia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public ReadAll(TMiembrosDeCompañia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();
	}
	
	@Test
	public void buscarMiembrosCompCorrecto(){
		
		id=saMiembrosDeCompañia.create(tMiembro_correcto);
		Collection<TMiembrosDeCompañia> res = saMiembrosDeCompañia.readAll();
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	
	@AfterClass
	public static void destroyClass()
	{	
		saMiembrosDeCompañia.deleteFisico(id);
	}
}
