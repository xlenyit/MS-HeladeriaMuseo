package negocio.miembrosdecompaņia;

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

import negocio.compaņia.TCompaņia;
import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Read {
	
	private static TMiembrosDeCompaņia tMiembro_correcto;
	private static TMiembrosDeCompaņia tMiembro_incorrecto;	
	private static SAMiembrosDeCompaņia saMiembrosDeCompaņia;
	
    private static TCompaņia tComp1 = new TCompaņia(-1, "TCreate1", "Comedia", true);
    private static TCompaņia tComp2 = new TCompaņia(-1, "TCreate2", "Drama", true);
    
    private static int id;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompaņia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public Read(TMiembrosDeCompaņia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();
	}
	
	@Test
	public void buscarMiembroCorrecto(){
		
		
		id= saMiembrosDeCompaņia.create(tMiembro_correcto);
		TMiembrosDeCompaņia res = saMiembrosDeCompaņia.read(id);
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	

	
	@Test
	public void buscarMiembroNoActivo(){
		
		id= saMiembrosDeCompaņia.create(tMiembro_correcto);
		saMiembrosDeCompaņia.delete(id);
		TMiembrosDeCompaņia res = saMiembrosDeCompaņia.read(id);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
		
	@Test
	public void buscarIdIncorrecto(){
		id= saMiembrosDeCompaņia.create(tMiembro_correcto);
		TMiembrosDeCompaņia res = saMiembrosDeCompaņia.read(-1);
		try{
			assertNull(res);
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
