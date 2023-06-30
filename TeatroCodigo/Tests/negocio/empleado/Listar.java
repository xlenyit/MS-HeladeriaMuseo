package negocio.empleado;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;
import negocio.turno.SATurno;
import negocio.turno.TTurno;

public class Listar {
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
	
	public Listar() {
		
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
	
	@AfterClass
	public static void destroyClass(){
		saEmp.bajaFisica(completoOK.getId());
		saEmp.bajaFisica(parcialOK.getId());
		saTur.bajaFisica(TURNO_C);
	}
	
	@Test
	public void listar() {
		saEmp.eliminar(parcialOK.getId());
		Collection<TEmpleado> res = saEmp.mostrar();
		try {
			assertTrue(!res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
}
