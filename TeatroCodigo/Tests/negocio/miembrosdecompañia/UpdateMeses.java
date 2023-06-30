package negocio.miembrosdecompa�ia;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compa�ia.SACompa�ia;
import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaNegocio;



@RunWith(Parameterized.class)
public class UpdateMeses{
	
	private static TMiembrosDeCompa�ia tMiembro_correcto;
	
	private static TLineaMiembro tLineaMiembro_correcto;
	
	private static SAMiembrosDeCompa�ia saMiembrosDeCompa�ia;
	private static SACompa�ia saCompa�ia;
	
    private static TCompa�ia tComp1 = new TCompa�ia(-1, "TCreate1", "Comedia", true);
    private static TCompa�ia tComp2 = new TCompa�ia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompa�ia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public UpdateMeses(TMiembrosDeCompa�ia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	
	
	
	
	@Before
	public void init()
	{
		saMiembrosDeCompa�ia = FactoriaNegocio.getInstance().createSAMiembrosDeCompa�ia();
		saCompa�ia = FactoriaNegocio.getInstance().createSACompa�ia();
		
		tComp1.setId(saCompa�ia.create(tComp1));
		tComp2.setId(saCompa�ia.create(tComp2));
		
		tMiembro_correcto.setId(saMiembrosDeCompa�ia.create(tMiembro_correcto));
		
		tLineaMiembro_correcto = new TLineaMiembro(tComp1.getId(), tMiembro_correcto.getId(), 10);
	}
	
	
	@Test
	public void update_correct() {
		
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
		tLineaMiembro_correcto.setNMeses(40);
		int res=saMiembrosDeCompa�ia.updateMeses(tLineaMiembro_correcto);
		
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
	public void	Update_idcompa�ia_Incorrect() {
		
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
		int idcomp= tLineaMiembro_correcto.getIdCompa�ia();
		tLineaMiembro_correcto.setIdCompa�ia(-1);
		int res=saMiembrosDeCompa�ia.updateMeses(tLineaMiembro_correcto);
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
		tLineaMiembro_correcto.setIdCompa�ia(idcomp);
	}
	
	
	@Test
	public void Update_idmiembro_incorrect() {
		
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
		int idmiembro= tLineaMiembro_correcto.getIdMiembro();
		tLineaMiembro_correcto.setIdMiembro(-1);
		int res=saMiembrosDeCompa�ia.updateMeses(tLineaMiembro_correcto);
		
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
			
			saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
			int numeses= tLineaMiembro_correcto.getNMeses();
			tLineaMiembro_correcto.setNMeses(-20);
			int res=saMiembrosDeCompa�ia.updateMeses(tLineaMiembro_correcto);
			
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
		saMiembrosDeCompa�ia.deleteFisico(tMiembro_correcto.getId());
		saCompa�ia.deleteFisico(tComp1.getId());
		saCompa�ia.deleteFisico(tComp2.getId());
	}
	

}