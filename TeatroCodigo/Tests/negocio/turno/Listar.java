package negocio.turno;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Listar {
	private static TTurno tTurno = new TTurno("TURNO_TEST_LISTAR");
	private static SATurno saTurno;
	
	@BeforeClass
	public static void init(){
		saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		
		tTurno.setId(saTurno.alta(tTurno));
	}
	
	@Test
	public void ListarTurnosCorrecto(){
		Collection<TTurno> res = saTurno.mostrarTurnos();
		
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void ListarTurnosInactivo(){
		saTurno.eliminar(tTurno.getId());
		Collection<TTurno> res = saTurno.mostrarTurnos();
		
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saTurno.bajaFisica(tTurno.getId());
	}
}
