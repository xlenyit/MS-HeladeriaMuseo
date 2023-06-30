package negocio.facturaTienda;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.empleado.TEmpleado;
import negocio.empleado.TEmpleadoTiempoParcial;
import negocio.factoria.FactoriaNegocio;
import negocio.facturaTienda.imp.TFacturaConProducto;
import negocio.facturaTienda.imp.TLineaFacturaTienda;
import negocio.marca.TMarca;
import negocio.producto.TProducto;
import negocio.producto.TProductoDurable;
import negocio.turno.TTurno;

public class Alta {

	private static TFacturaConProducto factura_correct;
	private static TFacturaConProducto factura_incorrect;
	private static SAFacturaTienda saFactura;
	private static int idProducto;
	private static int idEmpleado;
	private static int idTurno;
	private static int idMarca;
	private static int idProducido;
	
	@BeforeClass
	public static void inicio() {
		saFactura = FactoriaNegocio.getInstance().createSAFacturaTienda();
		idTurno = FactoriaNegocio.getInstance().createSATurno().alta(new TTurno("Test factura"));
		TEmpleado empleado = new TEmpleadoTiempoParcial(0, true, "35617894J", idTurno, 1, 1);
		idEmpleado = FactoriaNegocio.getInstance().createSAEmpleado().alta(empleado);
		idMarca = FactoriaNegocio.getInstance().createSAMarca().alta(new TMarca("Test factura"));
		TProducto producto = new TProductoDurable(0, idMarca, "Test factura", 5, 1, "Tipo", true);
		idProducto = FactoriaNegocio.getInstance().createSAProducto().alta(producto);
		TFacturaConProducto tCorrect = new TFacturaConProducto();
		tCorrect.añadirLineaFacturaTienda(new TLineaFacturaTienda(idProducto, 0, 1, 1));
		tCorrect.añadirTFacturaTienda(new TFacturaTienda(0, 1, 1, Date.valueOf("2021-12-14"), idEmpleado, true));
		TFacturaConProducto tIncorrect = new TFacturaConProducto();
		tIncorrect.añadirLineaFacturaTienda(new TLineaFacturaTienda(idProducto, 0, 1, 1));
		tIncorrect.añadirTFacturaTienda(new TFacturaTienda(0, 1, 1, Date.valueOf("2021-12-14"), 0, true));
		factura_correct = tCorrect;
		factura_incorrect = tIncorrect;
	}

	@Test
	public void create_correct() {
		idProducido = saFactura.cerrarVenta(factura_correct);
		try {
			assertTrue(idProducido > 0);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
	}

	@Test
	public void create_incorrect() {
		int idProducido = saFactura.cerrarVenta(factura_incorrect);
		int id_esperado = -1;
		try {
			assertEquals(id_esperado, idProducido);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saFactura.bajaFisica(idProducido);
		FactoriaNegocio.getInstance().createSAProducto().bajaFisica(idProducto);
		FactoriaNegocio.getInstance().createSAMarca().bajaFisica(idMarca);
		FactoriaNegocio.getInstance().createSAEmpleado().bajaFisica(idEmpleado);
		FactoriaNegocio.getInstance().createSATurno().bajaFisica(idTurno);
	}

}
