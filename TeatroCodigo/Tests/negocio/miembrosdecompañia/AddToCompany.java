package negocio.miembrosdecompaņia;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.compaņia.SACompaņia;
import negocio.compaņia.TCompaņia;
import negocio.factoria.FactoriaNegocio;



@RunWith(Parameterized.class)
public class AddToCompany {
	
	private static TMiembrosDeCompaņia tMiembro_correcto;
	
	private static TLineaMiembro tLineaMiembro_correcto;
	private static TLineaMiembro tLineaMiembro_id_miembro_incorrecto;
	private static TLineaMiembro tLineaMiembro_id_compaņia_incorrecto;
	private static TLineaMiembro tLineaMiembro_numeses_incorrecto;
	private static TLineaMiembro tLineaMiembro_repetido;
	
	private static SAMiembrosDeCompaņia saMiembrosDeCompaņia;
	private static SACompaņia saCompaņia;
	
    private static TCompaņia tComp1 = new TCompaņia(-1, "TCreate1", "Comedia", true);
    private static TCompaņia tComp2 = new TCompaņia(-1, "TCreate2", "Drama", true);
    
    @Parameters
	public static Iterable<Object[]> getData()
	{	
		return Arrays.asList(new Object[][]{{
			new TMiembrosDeCompaņia(-1, "Alejandro", "Moreno Murillo", "Actor", "69367077C", true)
			}});
	}
	
	public AddToCompany(TMiembrosDeCompaņia tMiembro_correcto)
	{
		this.tMiembro_correcto= tMiembro_correcto;
	}
	
	@Before
	public void init()
	{
		saMiembrosDeCompaņia = FactoriaNegocio.getInstance().createSAMiembrosDeCompaņia();
		saCompaņia = FactoriaNegocio.getInstance().createSACompaņia();
		
		tComp1.setId(saCompaņia.create(tComp1));
		tComp2.setId(saCompaņia.create(tComp2));
		
		tMiembro_correcto.setId(saMiembrosDeCompaņia.create(tMiembro_correcto));
		
		tLineaMiembro_correcto = new TLineaMiembro(tComp1.getId(), tMiembro_correcto.getId(), 10);
		tLineaMiembro_repetido = new TLineaMiembro(tComp1.getId(), tMiembro_correcto.getId(), 10);
		
		tLineaMiembro_id_miembro_incorrecto = new TLineaMiembro(tComp1.getId(),-1,10);
		tLineaMiembro_id_compaņia_incorrecto = new TLineaMiembro(-1,tMiembro_correcto.getId(),10);
		tLineaMiembro_numeses_incorrecto = new TLineaMiembro(tComp1.getId(),tMiembro_correcto.getId(),-20);
		
	}
	
	@Test
	public void Add_correct_no_repetido() {
		
		int res=saMiembrosDeCompaņia.addToCompany(tLineaMiembro_correcto);

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
	public void Add_correct_repetido() {
		
		
		saMiembrosDeCompaņia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompaņia.addToCompany(tLineaMiembro_repetido);
 
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
	public void Add_idmiembro_incorrect() {
		
		int res=saMiembrosDeCompaņia.addToCompany(tLineaMiembro_id_miembro_incorrecto);
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
	public void Add_idcompaņia_incorrect() {
		
		int res=saMiembrosDeCompaņia.addToCompany(tLineaMiembro_id_compaņia_incorrecto);
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
	public void Add_meses_incorrect() {
		
		int aux=saMiembrosDeCompaņia.addToCompany(tLineaMiembro_correcto);
		int res=saMiembrosDeCompaņia.addToCompany(tLineaMiembro_numeses_incorrecto);
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
		saMiembrosDeCompaņia.deleteFisico(tMiembro_correcto.getId());
		saCompaņia.deleteFisico(tComp1.getId());
		saCompaņia.deleteFisico(tComp2.getId());
	}
	

}
