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
public class Update {
	
	private static TMiembrosDeCompaņia tMiembro_inicial1;
	private static TMiembrosDeCompaņia tMiembro_update_correcto;
	private static TMiembrosDeCompaņia tMiembro_update_incorrecto;
	private static TMiembrosDeCompaņia tMiembro_update_repetido;

	private static SAMiembrosDeCompaņia saMiembrosDeCompaņia;
	
	private static int idm1;
	
    private static TCompaņia tComp1 = new TCompaņia(-1, "TCreate1", "Comedia", true);
    private static TCompaņia tComp2 = new TCompaņia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			 new TMiembrosDeCompaņia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true),
			 new TMiembrosDeCompaņia(-1, "Jose María", "Aznar", "Técnico", "23924666C", true),
			 new TMiembrosDeCompaņia(-1, "Sebastian", "Morales", "Técnico", "11580300Fsdf", true),
			 new TMiembrosDeCompaņia(-1, "Antonio", "Gomez", "Técnico", "69367077C", true)
			}});
	}
	
	public Update(TMiembrosDeCompaņia tMiembro_inicial1,TMiembrosDeCompaņia tMiembro__update_correcto,
				  TMiembrosDeCompaņia tMiembro__update_incorrecto,TMiembrosDeCompaņia tMiembro__update_repetido)
	{	
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();
		Update.tMiembro_inicial1= tMiembro_inicial1;
		Update.tMiembro_update_correcto = tMiembro__update_correcto;
		Update.tMiembro_update_incorrecto=tMiembro__update_incorrecto;
		Update.tMiembro_update_repetido=tMiembro__update_repetido;
	}
	
	@BeforeClass
	public static void inicio(){
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();
	}
	
	@Test
	public void update_correct() {
		
		idm1=saMiembrosDeCompaņia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompaņia.create(tMiembro_inicial1));
		int id = saMiembrosDeCompaņia.update(tMiembro_update_correcto);
		
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
		
		idm1=saMiembrosDeCompaņia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompaņia.create(tMiembro_inicial1));
		int id = saMiembrosDeCompaņia.update(tMiembro_update_incorrecto);
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
		
		idm1=saMiembrosDeCompaņia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompaņia.create(tMiembro_inicial1));
		saMiembrosDeCompaņia.delete(idm1);
		int id= saMiembrosDeCompaņia.update(tMiembro_inicial1);
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
		
		idm1=saMiembrosDeCompaņia.create(tMiembro_inicial1);
		tMiembro_update_correcto.setId(saMiembrosDeCompaņia.create(tMiembro_inicial1));
		int id= saMiembrosDeCompaņia.update(tMiembro_update_repetido);
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
		saMiembrosDeCompaņia.deleteFisico(idm1);
	}
	

}
