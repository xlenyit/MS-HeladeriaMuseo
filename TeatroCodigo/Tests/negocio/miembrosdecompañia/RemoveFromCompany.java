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
public class RemoveFromCompany {
	
	private static TMiembrosDeCompa�ia tMiembro_correcto;
	
	private static TLineaMiembro tLineaMiembro_correcto;
	private static TLineaMiembro tLineaMiembro_id_miembro_incorrecto;
	private static TLineaMiembro tLineaMiembro_id_compa�ia_incorrecto;
	
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
	
	public RemoveFromCompany(TMiembrosDeCompa�ia tMiembro_correcto)
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
		tLineaMiembro_id_miembro_incorrecto = new TLineaMiembro(tComp1.getId(),-1,10);
		tLineaMiembro_id_compa�ia_incorrecto = new TLineaMiembro(-1,tMiembro_correcto.getId(),10);

	}
	
	@Test
	public void Remove_correct() {
		
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompa�ia.removeFromCompany(tLineaMiembro_correcto);
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
	public void	Remove_idcompa�ia_Incorrect() {
		
		
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_id_compa�ia_incorrecto);
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
	}
	
	
	@Test
	public void Remove_idmiembro_incorrect() {
		
		saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompa�ia.addToCompany(tLineaMiembro_id_miembro_incorrecto);
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	

	@After
	public  void destroyClass()
	{	
		//Ya se encarga de borrar tambien la tabla intermedia
		saMiembrosDeCompa�ia.deleteFisico(tMiembro_correcto.getId());
		saMiembrosDeCompa�ia.removeFromCompany(tLineaMiembro_correcto);
		saCompa�ia.deleteFisico(tComp1.getId());
		saCompa�ia.deleteFisico(tComp2.getId());
	}
	

}
