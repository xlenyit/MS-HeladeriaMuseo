package negocio.miembrosdecompañia;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaNegocio;



@RunWith(Parameterized.class)
public class UpdateMeses{
	
	private static TMiembrosDeCompañia tMiembro_correcto;
	
	private static TLineaMiembro tLineaMiembro_correcto;
	
	private static SAMiembrosDeCompañia saMiembrosDeCompañia;
	private static SACompañia saCompañia;
	
    private static TCompañia tComp1 = new TCompañia(-1, "TCreate1", "Comedia", true);
    private static TCompañia tComp2 = new TCompañia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompañia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public UpdateMeses(TMiembrosDeCompañia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	
	
	
	
	@Before
	public void init()
	{
		saMiembrosDeCompañia = FactoriaNegocio.getInstance().createSAMiembrosDeCompañia();
		saCompañia = FactoriaNegocio.getInstance().createSACompañia();
		
		tComp1.setId(saCompañia.create(tComp1));
		tComp2.setId(saCompañia.create(tComp2));
		
		tMiembro_correcto.setId(saMiembrosDeCompañia.create(tMiembro_correcto));
		
		tLineaMiembro_correcto = new TLineaMiembro(tComp1.getId(), tMiembro_correcto.getId(), 10);
	}
	
	
	@Test
	public void update_correct() {
		
		saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
		tLineaMiembro_correcto.setNMeses(40);
		int res=saMiembrosDeCompañia.updateMeses(tLineaMiembro_correcto);
		
		try
		{
			assertTrue(res > 0);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
		
	}
	
	@Test
	public void	Update_idcompañia_Incorrect() {
		
		saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
		int idcomp= tLineaMiembro_correcto.getIdCompañia();
		tLineaMiembro_correcto.setIdCompañia(-1);
		int res=saMiembrosDeCompañia.updateMeses(tLineaMiembro_correcto);
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
		tLineaMiembro_correcto.setIdCompañia(idcomp);
	}
	
	
	@Test
	public void Update_idmiembro_incorrect() {
		
		saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
		int idmiembro= tLineaMiembro_correcto.getIdMiembro();
		tLineaMiembro_correcto.setIdMiembro(-1);
		int res=saMiembrosDeCompañia.updateMeses(tLineaMiembro_correcto);
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
		tLineaMiembro_correcto.setIdMiembro(idmiembro);
	}
	
	@Test
	public void Update_numMeses_incorrect() {
			
			saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
			int numeses= tLineaMiembro_correcto.getNMeses();
			tLineaMiembro_correcto.setNMeses(-20);
			int res=saMiembrosDeCompañia.updateMeses(tLineaMiembro_correcto);
			
			try
			{
				assertTrue(res == -1);
			}
			catch(Exception ae)
			{
				fail(ae.getMessage());
			}
			
			tLineaMiembro_correcto.setNMeses(numeses);
	}
	
	
	
	
	@After
	public void destroyClass()
	{	
		//Ya se encarga de borrar tambien la tabla intermedia
		saMiembrosDeCompañia.deleteFisico(tMiembro_correcto.getId());
		saCompañia.deleteFisico(tComp1.getId());
		saCompañia.deleteFisico(tComp2.getId());
	}
	

}