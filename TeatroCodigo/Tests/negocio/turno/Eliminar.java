package negocio.turno;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TEmpleadoTiempoCompleto;
import negocio.empleado.TEmpleadoTiempoParcial;
import negocio.factoria.FactoriaAbstractaNegocio;

public class Eliminar {
	private static SATurno saTurno;
	private static SAEmpleado saEmpleado;
	
	private static TTurno tTurno = new TTurno("TURNO_TEST_BAJA");
	private static TEmpleado tEmpleadoTiempoCompleto = new TEmpleadoTiempoCompleto(-1, true, "12345678C", -1, 2000, 500);
	private static TEmpleado tEmpleadoTiempoParcial = new TEmpleadoTiempoParcial(-1, true, "12345678P", -1, 160, 15); 
	
	@BeforeClass
	public static void init(){
		saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		
		tTurno.setId(saTurno.alta(tTurno));
		tEmpleadoTiempoCompleto.setId_turno(tTurno.getId());
		tEmpleadoTiempoParcial.setId_turno(tTurno.getId());
	}
	
	@After
	public void reiniciarTurno(){
		saTurno.alta(tTurno);
	}
	
	@Test
	public void eliminarTurnoCorrecto(){
		int res = saTurno.eliminar(tTurno.getId());
		
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void eliminarTurnoIncorrecto(){
		int res = saTurno.eliminar(-1);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void eliminarTurnoInactivo(){
		saTurno.eliminar(tTurno.getId());
		
		int res = saTurno.eliminar(tTurno.getId());
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void eliminarTurnoConEmpleadosActivos()
	{
		tEmpleadoTiempoCompleto.setId(saEmpleado.alta(tEmpleadoTiempoCompleto));
		tEmpleadoTiempoParcial.setId(saEmpleado.alta(tEmpleadoTiempoParcial));
		
		int res = saTurno.eliminar(tTurno.getId());
		
		saEmpleado.eliminar(tEmpleadoTiempoCompleto.getId());
		saEmpleado.eliminar(tEmpleadoTiempoParcial.getId());
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void eliminarTurnoConEmpleadosInactivos()
	{
		tEmpleadoTiempoCompleto.setId(saEmpleado.alta(tEmpleadoTiempoCompleto));
		tEmpleadoTiempoParcial.setId(saEmpleado.alta(tEmpleadoTiempoParcial));
		
		saEmpleado.eliminar(tEmpleadoTiempoCompleto.getId());
		saEmpleado.eliminar(tEmpleadoTiempoParcial.getId());
		
		int res = saTurno.eliminar(tTurno.getId());
		
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saEmpleado.bajaFisica(tEmpleadoTiempoCompleto.getId());
		saEmpleado.bajaFisica(tEmpleadoTiempoParcial.getId());
		
		saTurno.bajaFisica(tTurno.getId());
	}
}
