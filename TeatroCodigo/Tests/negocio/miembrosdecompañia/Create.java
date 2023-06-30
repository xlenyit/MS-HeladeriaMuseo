package negocio.miembrosdecompañia;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaNegocio;



@RunWith(Parameterized.class)
public class Create {
	
	private static TMiembrosDeCompañia tMiembro_correcto;
	private static TMiembrosDeCompañia tMiembro_repetido;
	private static TMiembrosDeCompañia tMiembro_incorrecto;
	
	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	
    private static TCompañia tComp1 = new TCompañia(-1, "TCreate1", "Comedia", true);
    private static TCompañia tComp2 = new TCompañia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompañia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true),
			new TMiembrosDeCompañia(-1, "Jorge", "del Vas Berenguer", "Tecnico", "69367077C", true),
			 new TMiembrosDeCompañia(-1, "Jose María", "Aznar", "Técnico", "69367077CJ", true)
			}});
	}
	
	public Create(TMiembrosDeCompañia tMiembro_correcto,TMiembrosDeCompañia tMiembro_repetido,TMiembrosDeCompañia tMiembro_incorrecto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
		this.tMiembro_repetido= tMiembro_repetido;
		this.tMiembro_incorrecto = tMiembro_incorrecto;
		
	}
	
	@BeforeClass
	public static void init()
	{
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();	
	
	}
	
	@Test
	public void create_correct() {
		
		int id = saMiembrosDeCompañia.create(tMiembro_correcto);
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
		
		saMiembrosDeCompañia.create(tMiembro_correcto);
		int id2 = saMiembrosDeCompañia.create(tMiembro_repetido);
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
		int id = saMiembrosDeCompañia.create(tMiembro_incorrecto);
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
		saMiembrosDeCompañia.deleteFisico(tMiembro_correcto.getId());
	}
	

}
