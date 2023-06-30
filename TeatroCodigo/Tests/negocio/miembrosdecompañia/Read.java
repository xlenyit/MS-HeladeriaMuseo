package negocio.miembrosdecompañia;

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

import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Read {
	
	private static TMiembrosDeCompañia tMiembro_correcto;
	private static TMiembrosDeCompañia tMiembro_incorrecto;	
	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	
    private static TCompañia tComp1 = new TCompañia(-1, "TCreate1", "Comedia", true);
    private static TCompañia tComp2 = new TCompañia(-1, "TCreate2", "Drama", true);
    
    private static int id;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompañia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public Read(TMiembrosDeCompañia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();
	}
	
	@Test
	public void buscarMiembroCorrecto(){
		
		
		id= saMiembrosDeCompañia.create(tMiembro_correcto);
		TMiembrosDeCompañia res = saMiembrosDeCompañia.read(id);
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	

	
	@Test
	public void buscarMiembroNoActivo(){
		
		id= saMiembrosDeCompañia.create(tMiembro_correcto);
		saMiembrosDeCompañia.delete(id);
		TMiembrosDeCompañia res = saMiembrosDeCompañia.read(id);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
		
	@Test
	public void buscarIdIncorrecto(){
		id= saMiembrosDeCompañia.create(tMiembro_correcto);
		TMiembrosDeCompañia res = saMiembrosDeCompañia.read(-1);
		try{
			assertNull(res);
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
