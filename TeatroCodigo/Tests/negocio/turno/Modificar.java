 package negocio.turno;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Modificar {
	private static TTurno tTurno =new TTurno("TURNO_TEST_MODIFICAR");
	private static SATurno saTurno;
	@BeforeClass
	public static void init()
	{
		saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		tTurno.setId(saTurno.alta(tTurno));	
	}
	@Test
	public void testCorrecto() {
		tTurno.setHorario("TURNO_TEST_MODIFICAR_CORRECTO");
		int res = saTurno.modificar(tTurno);
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
		
	}
	@Test
	public void testIncorrecto() {
		
		int res = saTurno.modificar(new TTurno(-1, "", true));
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	@Test
	public void testRepetido() {
		TTurno rep = new TTurno("TURNO_TEST_MODIFICAR_REP");
		rep.setId(saTurno.alta(rep));	
		rep.setHorario(tTurno.getHorario());
		int res =saTurno.modificar(rep);
		saTurno.bajaFisica(rep.getId());
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}	
	}
	@Test
	public void testEntidadInactiva() {
		saTurno.eliminar(tTurno.getId());
		tTurno.setHorario("TURNO_TEST_MODIFICAR");
		int res = saTurno.modificar(tTurno);
		saTurno.alta(tTurno);
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
