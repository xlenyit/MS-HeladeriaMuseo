package negocio.miembrosdecompañia;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Delete {
	
	private static TMiembrosDeCompañia tMiembro_correcto;
	private static TMiembrosDeCompañia tMiembro_incorrecto;	
	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	private static SACompañia saCompañia;
    private static TCompañia tComp1 = new TCompañia(-1, "TCreate1", "Comedia", true);
    private static TCompañia tComp2 = new TCompañia(-1, "TCreate2", "Drama", true);
    
    private static int id;
    private static int idComp1;
    private static int idComp2;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompañia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public Delete(TMiembrosDeCompañia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();
		saCompañia = FactoriaNegocio.getInstance().createSACompañia();
	}
	
	@Test
	public void DeleteMiembroCorrecto(){
		
		
		id= saMiembrosDeCompañia.create(tMiembro_correcto);
		int res1 = saMiembrosDeCompañia.delete(id);
		TMiembrosDeCompañia res2= saMiembrosDeCompañia.read(res1);
		
		try{
			assertTrue(res1>0);
			assertNull(res2);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void DeleteMiembroIncorrecto(){
	
		int res1 = saMiembrosDeCompañia.delete(-1);
		try{
			assertTrue(res1==-1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void DeleteMiembroConCompañias(){
	
		
		id= saMiembrosDeCompañia.create(tMiembro_correcto);
		
		idComp1= saCompañia.create(tComp1);
		idComp2= saCompañia.create(tComp2);
		
		TLineaMiembro t1,t2;
		t1= new TLineaMiembro(idComp1,id,10);
		t2= new TLineaMiembro(idComp2,id,10);
		
		saMiembrosDeCompañia.addToCompany(t1);
		saMiembrosDeCompañia.addToCompany(t2);
		
		int res1 = saMiembrosDeCompañia.delete(id);
		TMiembrosDeCompañia res2= saMiembrosDeCompañia.read(res1);
		
		//Faltan leer las compañias a las que está agregado un miembro pero falta la operacion en SACompañia
		
		
		try{
			assertTrue(res1>0);
			assertNull(res2);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	

	@AfterClass
	public static void destroyClass()
	{	
		saMiembrosDeCompañia.deleteFisico(id);
		saCompañia.deleteFisico(idComp1);
		saCompañia.deleteFisico(idComp2);
	}
	

}
