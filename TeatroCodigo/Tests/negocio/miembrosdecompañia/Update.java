package negocio.miembrosdecompa�ia;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaNegocio;



@RunWith(Parameterized.class)
public class Update {
	
	private static TMiembrosDeCompa�ia tMiembro_inicial1;
	private static TMiembrosDeCompa�ia tMiembro_update_correcto;
	private static TMiembrosDeCompa�ia tMiembro_update_incorrecto;
	private static TMiembrosDeCompa�ia tMiembro_update_repetido;

	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	
	private static int idm1;
	
    private static TCompa�ia tComp1 = new TCompa�ia(-1, "TCreate1", "Comedia", true);
    private static TCompa�ia tComp2 = new TCompa�ia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			 new TMiembrosDeCompa�ia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true),
			 new TMiembrosDeCompa�ia(-1, "Jose Mar�a", "Aznar", "T�cnico", "23924666C", true),
			 new TMiembrosDeCompa�ia(-1, "Sebastian", "Morales", "T�cnico", "11580300Fsdf", true),
			 new TMiembrosDeCompa�ia(-1, "Antonio", "Gomez", "T�cnico", "69367077C", true)
			}});
	}
	
	public Update(TMiembrosDeCompa�ia tMiembro_inicial1,TMiembrosDeCompa�ia tMiembro__update_correcto,
				  TMiembrosDeCompa�ia tMiembro__update_incorrecto,TMiembrosDeCompa�ia tMiembro__update_repetido)
	{	
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();
		Update.tMiembro_inicial1= tMiembro_inicial1;
		Update.tMiembro_update_correcto = tMiembro__update_correcto;
		Update.tMiembro_update_incorrecto=tMiembro__update_incorrecto;
		Update.tMiembro_update_repetido=tMiembro__update_repetido;
	}
	
	@BeforeClass
	public static void inicio(){
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();
	}
	
	@Test
	public void update_correct() {
		
		idm1=saMiembrosDeCompa�ia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompa�ia.create(tMiembro_inicial1));
		int id = saMiembrosDeCompa�ia.update(tMiembro_update_correcto);
		
		try
		{
			assertTrue(id > 0);
			idm1=id;
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	
	
	@Test
	public void update_incorrect() {
		
		idm1=saMiembrosDeCompa�ia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompa�ia.create(tMiembro_inicial1));
		int id = saMiembrosDeCompa�ia.update(tMiembro_update_incorrecto);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void update_de_no_activo() {
		
		idm1=saMiembrosDeCompa�ia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompa�ia.create(tMiembro_inicial1));
		saMiembrosDeCompa�ia.delete(idm1);
		int id= saMiembrosDeCompa�ia.update(tMiembro_inicial1);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	

	@Test
	public void update_de_repetido() {
		
		idm1=saMiembrosDeCompa�ia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompa�ia.create(tMiembro_inicial1));
		int id= saMiembrosDeCompa�ia.update(tMiembro_update_repetido);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}

	@After
	public void destroyClass()
	{	
		saMiembrosDeCompa�ia.deleteFisico(idm1);
	}
	

}
