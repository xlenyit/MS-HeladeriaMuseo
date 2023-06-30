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

public class Buscar {
	private static final String NIF_C = "TestEmple";
	private static int TURNO_C; // lo calculamos
	private static final double BASE_C = 1;
	private static final double HORAS_C = 1;
	private static final double COMPLEMENTO_C = 1;
	private static final double SPH_C = 1;
	private static TEmpleadoTiempoCompleto completoOK = new TEmpleadoTiempoCompleto(0, true, NIF_C, TURNO_C, BASE_C, COMPLEMENTO_C);
	private TEmpleadoTiempoCompleto completoKO = new TEmpleadoTiempoCompleto(0, true, NIF_C, TURNO_C, BASE_C, COMPLEMENTO_C);
	private static TEmpleadoTiempoParcial parcialOK = new TEmpleadoTiempoParcial(0, true, NIF_C, TURNO_C, HORAS_C, SPH_C);
	private static TEmpleadoTiempoParcial parcialKO = new TEmpleadoTiempoParcial(0, true, NIF_C, TURNO_C, HORAS_C, SPH_C);
	private static SAEmpleado saEmp;
	private static SATurno saTur;
	
	public Buscar() {
	}
	
	@BeforeClass
	public static void initClass() {
		saEmp = FactoriaNegocio.getInstance().createSAEmpleado();
		saTur = FactoriaNegocio.getInstance().createSATurno();
		TURNO_C = saTur.alta(new TTurno(0, "testEmpleado", true));
	}
	
	@Before
	public void asignar(){
		int t = saTur.alta(new TTurno(0, "testEmpleado", true));
		if (t > 0) {
			TURNO_C = t;
		}
		completoOK.setId_turno(TURNO_C);
		parcialOK.setId_turno(TURNO_C);
	}
	
	@After
	public void reiniciar() {
		saEmp.bajaFisica(completoOK.getId());
		saEmp.bajaFisica(parcialOK.getId());
	}
	
	@AfterClass
	public static void destroyClass(){
		saEmp.bajaFisica(completoOK.getId());
		saEmp.bajaFisica(parcialOK.getId());
		saTur.bajaFisica(TURNO_C);
	}
	
	@Test
	public void buscarCorrectoCompleto() {
		completoOK.setId(saEmp.alta(completoOK));
		TEmpleado e = saEmp.buscar(completoOK.getId());
		try {
			assertTrue(e != null);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarCorrectoParcial() {
		parcialOK.setId(saEmp.alta(parcialOK));
		TEmpleado e = saEmp.buscar(parcialOK.getId());
		try {
			assertTrue(e != null);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarIncorrectoCompleto() {
		TEmpleado e = saEmp.buscar(-1);
		try{
			assertTrue(e == null);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarIncorrectoParcial() {
		TEmpleado e = saEmp.buscar(-1);
		try{
			assertTrue(e == null);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarParcialInactivo() {
		parcialOK.setId(saEmp.alta(parcialOK));
		saEmp.eliminar(parcialOK.getId());
		TEmpleado e = saEmp.buscar(parcialOK.getId());
		try{
			assertTrue(e != null);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarCompletoInactivo() {
		completoOK.setId(saEmp.alta(completoOK));
		saEmp.eliminar(completoOK.getId());
		TEmpleado e = saEmp.buscar(completoOK.getId());
		try{
			assertTrue(e != null);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	
}
