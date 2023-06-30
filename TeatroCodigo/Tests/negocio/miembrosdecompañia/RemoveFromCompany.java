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
public class RemoveFromCompany {
	
	private static TMiembrosDeCompañia tMiembro_correcto;
	
	private static TLineaMiembro tLineaMiembro_correcto;
	private static TLineaMiembro tLineaMiembro_id_miembro_incorrecto;
	private static TLineaMiembro tLineaMiembro_id_compañia_incorrecto;
	
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
	
	public RemoveFromCompany(TMiembrosDeCompañia tMiembro_correcto)
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
		tLineaMiembro_id_miembro_incorrecto = new TLineaMiembro(tComp1.getId(),-1,10);
		tLineaMiembro_id_compañia_incorrecto = new TLineaMiembro(-1,tMiembro_correcto.getId(),10);

	}
	
	@Test
	public void Remove_correct() {
		
		saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompañia.removeFromCompany(tLineaMiembro_correcto);
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
	public void	Remove_idcompañia_Incorrect() {
		
		
		saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompañia.addToCompany(tLineaMiembro_id_compañia_incorrecto);
		
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
		
		saMiembrosDeCompañia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompañia.addToCompany(tLineaMiembro_id_miembro_incorrecto);
		
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
		saMiembrosDeCompañia.deleteFisico(tMiembro_correcto.getId());
		saMiembrosDeCompañia.removeFromCompany(tLineaMiembro_correcto);
		saCompañia.deleteFisico(tComp1.getId());
		saCompañia.deleteFisico(tComp2.getId());
	}
	

}
