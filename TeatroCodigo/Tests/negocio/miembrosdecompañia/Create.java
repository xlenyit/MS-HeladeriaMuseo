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
public class Create {
	
	private static TMiembrosDeCompa�ia tMiembro_correcto;
	private static TMiembrosDeCompa�ia tMiembro_repetido;
	private static TMiembrosDeCompa�ia tMiembro_incorrecto;
	
	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	
    private static TCompa�ia tComp1 = new TCompa�ia(-1, "TCreate1", "Comedia", true);
    private static TCompa�ia tComp2 = new TCompa�ia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompa�ia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true),
			new TMiembrosDeCompa�ia(-1, "Jorge", "del Vas Berenguer", "Tecnico", "69367077C", true),
			 new TMiembrosDeCompa�ia(-1, "Jose Mar�a", "Aznar", "T�cnico", "69367077CJ", true)
			}});
	}
	
	public Create(TMiembrosDeCompa�ia tMiembro_correcto,TMiembrosDeCompa�ia tMiembro_repetido,TMiembrosDeCompa�ia tMiembro_incorrecto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
		this.tMiembro_repetido= tMiembro_repetido;
		this.tMiembro_incorrecto = tMiembro_incorrecto;
		
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();	
	
	}
	
	@Test
	public void create_correct() {
		
		int id = saMiembrosDeCompa�ia.create(tMiembro_correcto);
		try
		{
			assertTrue(id > 0);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
		tMiembro_correcto.setId(id);
	}
	
	
	public void create_dni_rep() {
		
		saMiembrosDeCompa�ia.create(tMiembro_correcto);
		int id2 = saMiembrosDeCompa�ia.create(tMiembro_repetido);
		try
		{
			assertTrue(id2 == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	
	
	@Test
	public void create_incorrect() {
		int id = saMiembrosDeCompa�ia.create(tMiembro_incorrecto);
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
		saMiembrosDeCompa�ia.deleteFisico(tMiembro_correcto.getId());
	}
	

}
