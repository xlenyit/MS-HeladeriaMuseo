package negocio.turno;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Buscar {
	private static SATurno saTurno;
	
	private static TTurno tTurno = new TTurno("TURNO_TEST_BUSCAR");
	
	@BeforeClass
	public static void init(){
		saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		
		tTurno.setId(saTurno.alta(tTurno));
	}
	
	@Test
	public void buscarTurnoCorrecto(){
		TTurno res = saTurno.buscar(tTurno.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarTurnoInactivo(){
		saTurno.eliminar(tTurno.getId());
		
		TTurno res = saTurno.buscar(tTurno.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarTurnoIDIncorrecto(){
		TTurno res = saTurno.buscar(-1);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saTurno.bajaFisica(tTurno.getId());
	}
}
