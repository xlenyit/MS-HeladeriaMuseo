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
public class Update {
	
	private static TMiembrosDeCompañia tMiembro_inicial1;
	private static TMiembrosDeCompañia tMiembro_update_correcto;
	private static TMiembrosDeCompañia tMiembro_update_incorrecto;
	private static TMiembrosDeCompañia tMiembro_update_repetido;

	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	
	private static int idm1;
	
    private static TCompañia tComp1 = new TCompañia(-1, "TCreate1", "Comedia", true);
    private static TCompañia tComp2 = new TCompañia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			 new TMiembrosDeCompañia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true),
			 new TMiembrosDeCompañia(-1, "Jose María", "Aznar", "Técnico", "23924666C", true),
			 new TMiembrosDeCompañia(-1, "Sebastian", "Morales", "Técnico", "11580300Fsdf", true),
			 new TMiembrosDeCompañia(-1, "Antonio", "Gomez", "Técnico", "69367077C", true)
			}});
	}
	
	public Update(TMiembrosDeCompañia tMiembro_inicial1,TMiembrosDeCompañia tMiembro__update_correcto,
				  TMiembrosDeCompañia tMiembro__update_incorrecto,TMiembrosDeCompañia tMiembro__update_repetido)
	{	
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();
		Update.tMiembro_inicial1= tMiembro_inicial1;
		Update.tMiembro_update_correcto = tMiembro__update_correcto;
		Update.tMiembro_update_incorrecto=tMiembro__update_incorrecto;
		Update.tMiembro_update_repetido=tMiembro__update_repetido;
	}
	
	@BeforeClass
	public static void inicio(){
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();
	}
	
	@Test
	public void update_correct() {
		
		idm1=saMiembrosDeCompañia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompañia.create(tMiembro_inicial1));
		int id = saMiembrosDeCompañia.update(tMiembro_update_correcto);
		
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
		
		idm1=saMiembrosDeCompañia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompañia.create(tMiembro_inicial1));
		int id = saMiembrosDeCompañia.update(tMiembro_update_incorrecto);
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
		
		idm1=saMiembrosDeCompañia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompañia.create(tMiembro_inicial1));
		saMiembrosDeCompañia.delete(idm1);
		int id= saMiembrosDeCompañia.update(tMiembro_inicial1);
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
		
		idm1=saMiembrosDeCompañia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompañia.create(tMiembro_inicial1));
		int id= saMiembrosDeCompañia.update(tMiembro_update_repetido);
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
		saMiembrosDeCompañia.deleteFisico(idm1);
	}
	

}
