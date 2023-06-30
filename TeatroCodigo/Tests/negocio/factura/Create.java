package negocio.factura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.actividad.TActCultural;
import negocio.actividad.TActividad;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;

@RunWith(value = Parameterized.class)
public class Create {

	private TOAFacturaConActividad factura_correct;
	private TOAFacturaConActividad factura_incorrect;
	private static SAFactura saFactura;
	private static int idActividad;
	private static int idCliente;

	@Parameters
	public static Iterable<Object[]> getData() {
		TCliente cliente = new TCliente(0, "35617894J", "test cliente", "test cliente", true, true);
		idCliente = FactoriaNegocio.getInstance().createSACliente().create(cliente);
		TActividad actividad = new TActCultural(0, "test actividad", 1, Date.valueOf("2030-12-12"),
				Date.valueOf("2030-12-13"), Time.valueOf("00:00:00"), 120, 1.5, true);
		idActividad = FactoriaNegocio.getInstance().createSAActividad().create(actividad);
		Collection<TLineaFactura> carrito = new ArrayList<TLineaFactura>();
		carrito.add(new TLineaFactura(idActividad, 0, 1));
		return Arrays.asList(new Object[][] {
			{ new TOAFacturaConActividad(carrito, idCliente, 0), new TOAFacturaConActividad(carrito, 0, 0) } });
	}

	public Create(TOAFacturaConActividad factura_correct, TOAFacturaConActividad factura_incorrect) {
		this.factura_correct = factura_correct;
		this.factura_incorrect = factura_incorrect;
	}

	@BeforeClass
	public static void inicio() {
		saFactura = FactoriaNegocio.getInstance().createSAFactura();
	}

	@Test
	public void create_correct() {
		int id_producido = saFactura.create(factura_correct);
		try {
			assertTrue(id_producido > 0);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
		saFactura.deleteFisico(id_producido);
		FactoriaNegocio.getInstance().createSAActividad().deleteFisico(idActividad);
		FactoriaNegocio.getInstance().createSACliente().deleteFisico(idCliente);
	}

	@Test
	public void create_incorrect() {
		int id_producido = saFactura.create(factura_incorrect);
		int id_esperado = -1;
		try {
			assertEquals(id_esperado, id_producido);
		} catch (AssertionError ae) {
			fail(ae.getMessage());
		}
	}

}
