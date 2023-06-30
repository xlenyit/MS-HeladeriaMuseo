package negocio.turno;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import negocio.empleado.TEmpleadoTiempoCompleto;
import negocio.empleado.TEmpleadoTiempoParcial;
import negocio.factoria.FactoriaAbstractaNegocio;

public class CalcularNomina {

	private static SATurno saTurno;
	private static SAEmpleado saEmpleado;
	
	private static TTurno tTurno = new TTurno("TURNO_TEST_NOMINA");
	private static TEmpleado tEmpleadoTC = new TEmpleadoTiempoCompleto(0, true,"192837465", 0, 1200, 300);
	private static TEmpleado tEmpleadoTP = new TEmpleadoTiempoParcial(0, true,"192837465", 0, 8, 12);
	@BeforeClass
	public static void init(){
		saTurno = FactoriaAbstractaNegocio.getInstance().createSATurno();
		saEmpleado = FactoriaAbstractaNegocio.getInstance().createSAEmpleado();
		
		tTurno.setId(saTurno.alta(tTurno));
		tEmpleadoTC.setId_turno(tTurno.getId());
		tEmpleadoTC.setId(saEmpleado.alta(tEmpleadoTC));
		tEmpleadoTP.setId_turno(tTurno.getId());
		tEmpleadoTP.setId(saEmpleado.alta(tEmpleadoTP));
	}
	
	@Test
	public void calcularNominaCorrecto(){
		int res = saTurno.calcularNomina(tTurno.getId());
		
		try{
			assertTrue(res >= 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void calcularNominaIncorrecto(){
		int res = saTurno.calcularNomina(-1);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void calcularNominaConUnoInactivo(){
		
		int res_ant = saTurno.calcularNomina(tTurno.getId());
		saEmpleado.eliminar(tEmpleadoTC.getId());
		res_ant -= 1500;
		int res = saTurno.calcularNomina(tTurno.getId());
		
		try{
			assertTrue(res == res_ant);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saEmpleado.bajaFisica(tEmpleadoTC.getId());
		saEmpleado.bajaFisica(tEmpleadoTP.getId());
		
		saTurno.bajaFisica(tTurno.getId());
	}
}

