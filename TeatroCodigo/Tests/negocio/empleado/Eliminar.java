package negocio.empleado;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.turno.SATurno;
import negocio.turno.TTurno;

public class Eliminar {
	private static final String NIF_C = "TestEmpld";
	private static final String NIF_2 = "TestEmpl2";
	private static int TURNO_C; // lo calculamos
	private static final double BASE_C = 1;
	private static final double HORAS_C = 1;
	private static final double COMPLEMENTO_C = 1;
	private static final double SPH_C = 1;
	private static TEmpleadoTiempoCompleto completoOK = new TEmpleadoTiempoCompleto(0, true, NIF_C, TURNO_C, BASE_C, COMPLEMENTO_C);
	private TEmpleadoTiempoCompleto completoKO = new TEmpleadoTiempoCompleto(0, true, NIF_C, TURNO_C, BASE_C, COMPLEMENTO_C);
	private static TEmpleadoTiempoParcial parcialOK = new TEmpleadoTiempoParcial(0, true, NIF_2, TURNO_C, HORAS_C, SPH_C);
	private static TEmpleadoTiempoParcial parcialKO = new TEmpleadoTiempoParcial(0, true, NIF_C, TURNO_C, HORAS_C, SPH_C);
	private static SAEmpleado saEmp;
	private static SATurno saTur;
	
	public Eliminar() {
	}
	
	@BeforeClass
	public static void initClass() {
		saEmp = FactoriaNegocio.getInstance().createSAEmpleado();
		saTur = FactoriaNegocio.getInstance().createSATurno();
		TURNO_C = saTur.alta(new TTurno(0, "testEmpleado2", true));
		completoOK.setId_turno(TURNO_C);
		parcialOK.setId_turno(TURNO_C);
		completoOK.setId(saEmp.alta(completoOK));
		parcialOK.setId(saEmp.alta(parcialOK));
	}
	
	@After
	public void reiniciar() {
		saEmp.alta(completoOK);
		saEmp.alta(completoKO);
	}
	
	@AfterClass
	public static void destroyClass(){
		saEmp.bajaFisica(completoOK.getId());
		saEmp.bajaFisica(parcialOK.getId());
		saTur.bajaFisica(TURNO_C);
	}
	
	@Test
	public void bajaCorrectoCompleto() {
		int res = saEmp.eliminar(completoOK.getId());
		try {
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaCorrectoParcial() {
		int res = saEmp.eliminar(parcialOK.getId());
		try {
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaIncorrectoCompleto() {
		int res = saEmp.eliminar(-1);
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaIncorrectoParcial() {
		int res = saEmp.eliminar(-1);
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaParcialInactivo() {
		saEmp.eliminar(parcialOK.getId());
		int res = saEmp.eliminar(parcialOK.getId());
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void bajaCompletoInactivo() {
		saEmp.eliminar(completoOK.getId());
		int res = saEmp.eliminar(completoOK.getId());
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
}
