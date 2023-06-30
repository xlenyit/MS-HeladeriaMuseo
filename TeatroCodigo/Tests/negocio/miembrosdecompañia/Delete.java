package negocio.miembrosdecompa�ia;

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

import negocio.compa�ia.SACompa�ia;
import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Delete {
	
	private static TMiembrosDeCompa�ia tMiembro_correcto;
	private static TMiembrosDeCompa�ia tMiembro_incorrecto;	
	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	private static SACompa�ia saCompa�ia;
    private static TCompa�ia tComp1 = new TCompa�ia(-1, "TCreate1", "Comedia", true);
    private static TCompa�ia tComp2 = new TCompa�ia(-1, "TCreate2", "Drama", true);
    
    private static int id;
    private static int idComp1;
    private static int idComp2;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompa�ia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public Delete(TMiembrosDeCompa�ia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();
		saCompa�ia = FactoriaNegocio.getInstance().createSACompa�ia();
	}
	
	@Test
	public void DeleteMiembroCorrecto(){
		
		
		id= saMiembrosDeCompa�ia.create(tMiembro_correcto);
		int res1 = saMiembrosDeCompa�ia.delete(id);
		TMiembrosDeCompa�ia res2= saMiembrosDeCompa�ia.read(res1);
		
		try{
			assertTrue(res1>0);
			assertNull(res2);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void DeleteMiembroIncorrecto(){
	
		int res1 = saMiembrosDeCompa�ia.delete(-1);
		try{
			assertTrue(res1==-1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void DeleteMiembroConCompa�ias(){
	
		
		id= saMiembrosDeCompa�ia.create(tMiembro_correcto);
		
		idComp1= saCompa�ia.create(tComp1);
		idComp2= saCompa�ia.create(tComp2);
		
		TLineaMiembro t1,t2;
		t1= new TLineaMiembro(idComp1,id,10);
		t2= new TLineaMiembro(idComp2,id,10);
		
		saMiembrosDeCompa�ia.addToCompany(t1);
		saMiembrosDeCompa�ia.addToCompany(t2);
		
		int res1 = saMiembrosDeCompa�ia.delete(id);
		TMiembrosDeCompa�ia res2= saMiembrosDeCompa�ia.read(res1);
		
		//Faltan leer las compa�ias a las que est� agregado un miembro pero falta la operacion en SACompa�ia
		
		
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
		saMiembrosDeCompa�ia.deleteFisico(id);
		saCompa�ia.deleteFisico(idComp1);
		saCompa�ia.deleteFisico(idComp2);
	}
	

}
