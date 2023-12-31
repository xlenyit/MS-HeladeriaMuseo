package negocio.miembrosdecompaņia;

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

import negocio.compaņia.SACompaņia;
import negocio.compaņia.TCompaņia;
import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Delete {
	
	private static TMiembrosDeCompaņia tMiembro_correcto;
	private static TMiembrosDeCompaņia tMiembro_incorrecto;	
	private static SAMiembrosDeCompaņia saMiembrosDeCompaņia;
	private static SACompaņia saCompaņia;
    private static TCompaņia tComp1 = new TCompaņia(-1, "TCreate1", "Comedia", true);
    private static TCompaņia tComp2 = new TCompaņia(-1, "TCreate2", "Drama", true);
    
    private static int id;
    private static int idComp1;
    private static int idComp2;
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompaņia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public Delete(TMiembrosDeCompaņia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();
		saCompaņia = FactoriaNegocio.getInstance().createSACompaņia();
	}
	
	@Test
	public void DeleteMiembroCorrecto(){
		
		
		id= saMiembrosDeCompaņia.create(tMiembro_correcto);
		int res1 = saMiembrosDeCompaņia.delete(id);
		TMiembrosDeCompaņia res2= saMiembrosDeCompaņia.read(res1);
		
		try{
			assertTrue(res1>0);
			assertNull(res2);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void DeleteMiembroIncorrecto(){
	
		int res1 = saMiembrosDeCompaņia.delete(-1);
		try{
			assertTrue(res1==-1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void DeleteMiembroConCompaņias(){
	
		
		id= saMiembrosDeCompaņia.create(tMiembro_correcto);
		
		idComp1= saCompaņia.create(tComp1);
		idComp2= saCompaņia.create(tComp2);
		
		TLineaMiembro t1,t2;
		t1= new TLineaMiembro(idComp1,id,10);
		t2= new TLineaMiembro(idComp2,id,10);
		
		saMiembrosDeCompaņia.addToCompany(t1);
		saMiembrosDeCompaņia.addToCompany(t2);
		
		int res1 = saMiembrosDeCompaņia.delete(id);
		TMiembrosDeCompaņia res2= saMiembrosDeCompaņia.read(res1);
		
		//Faltan leer las compaņias a las que está agregado un miembro pero falta la operacion en SACompaņia
		
		
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
		saMiembrosDeCompaņia.deleteFisico(id);
		saCompaņia.deleteFisico(idComp1);
		saCompaņia.deleteFisico(idComp2);
	}
	

}
