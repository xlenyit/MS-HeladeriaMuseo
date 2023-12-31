package negocio.miembrosdecompaņia;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compaņia.TCompaņia;
import negocio.factoria.FactoriaNegocio;



@RunWith(Parameterized.class)
public class Create {
	
	private static TMiembrosDeCompaņia tMiembro_correcto;
	private static TMiembrosDeCompaņia tMiembro_repetido;
	private static TMiembrosDeCompaņia tMiembro_incorrecto;
	
	private static SAMiembrosDeCompaņia saMiembrosDeCompaņia;
	
    private static TCompaņia tComp1 = new TCompaņia(-1, "TCreate1", "Comedia", true);
    private static TCompaņia tComp2 = new TCompaņia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompaņia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true),
			new TMiembrosDeCompaņia(-1, "Jorge", "del Vas Berenguer", "Tecnico", "69367077C", true),
			 new TMiembrosDeCompaņia(-1, "Jose María", "Aznar", "Técnico", "69367077CJ", true)
			}});
	}
	
	public Create(TMiembrosDeCompaņia tMiembro_correcto,TMiembrosDeCompaņia tMiembro_repetido,TMiembrosDeCompaņia tMiembro_incorrecto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
		this.tMiembro_repetido= tMiembro_repetido;
		this.tMiembro_incorrecto = tMiembro_incorrecto;
		
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();	
	
	}
	
	@Test
	public void create_correct() {
		
		int id = saMiembrosDeCompaņia.create(tMiembro_correcto);
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
		
		saMiembrosDeCompaņia.create(tMiembro_correcto);
		int id2 = saMiembrosDeCompaņia.create(tMiembro_repetido);
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
		int id = saMiembrosDeCompaņia.create(tMiembro_incorrecto);
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
		saMiembrosDeCompaņia.deleteFisico(tMiembro_correcto.getId());
	}
	

}
