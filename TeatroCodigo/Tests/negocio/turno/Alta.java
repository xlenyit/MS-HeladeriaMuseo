package negocio.turno;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Alta {
	private static SATurno saTurno;
	
	private static TTurno tTurno = new TTurno("TURNO_TEST_ALTA");
	private static TTurno tTurnoIncorrecto = new TTurno("");
	
	@BeforeClass
	public static void init(){
		saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
	}
	
	@After
	public void reiniciarTurno(){
		saTurno.eliminar(tTurno.getId());
	}
	
	@Test
	public void altaTurnoCorrecto(){
		tTurno.setId(saTurno.alta(tTurno));
		
		try{
			assertTrue(tTurno.getId() > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaTurnoIncorrecto(){
		int res = saTurno.alta(tTurnoIncorrecto);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaTurnoInactivo(){
		tTurno.setId(saTurno.alta(tTurno));
		
		saTurno.eliminar(tTurno.getId());
		
		tTurno.setId(saTurno.alta(tTurno));
		
		try{
			assertTrue(tTurno.getId() > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void altaTurnoRepetido(){
		tTurno.setId(saTurno.alta(tTurno));
		int res = saTurno.alta(tTurno);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saTurno.bajaFisica(tTurno.getId());
	}
}
