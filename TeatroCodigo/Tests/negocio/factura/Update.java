package negocio.factura;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.actividad.SAActividad;
import negocio.actividad.TActCultural;
import negocio.actividad.TActividad;
import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.factoria.FactoriaNegocio;

public class Update {

	private static SAFactura saFactura;
	private static SAActividad saActividad;
	private static SACliente saCliente;

	private static TOAFacturaConActividad factura_original, factura_modificada, factura_incorrecta;
	private static TActividad actividad = new TActCultural(0, "test actividad", 1, Date.valueOf("2034-12-12"),
			Date.valueOf("2034-12-13"), Time.valueOf("00:00:00"), 120, 1.5, true);
	private static TCliente cliente_original = new TCliente(0, "35617898J", "test cliente o", "test cliente o", true, true);
	private static TCliente cliente_nuevo = new TCliente(0, "65447889G", "test cliente n", "test cliente n", true, true);

	private static int idFactura_original;

	@BeforeClass
	public static void inicio() {
		saFactura = FactoriaNegocio.getInstance().createSAFactura();
		saActividad = FactoriaNegocio.getInstance().createSAActividad();
		saCliente = FactoriaNegocio.getInstance().createSACliente();

		actividad.setId(saActividad.create(actividad));
		cliente_original.setId(saCliente.create(cliente_original));
		cliente_nuevo.setId(saCliente.create(cliente_nuevo));
		Collection<TLineaFactura> carrito = new ArrayList<TLineaFactura>();
		carrito.add(new TLineaFactura(actividad.getId(), 0, 1));
		factura_original = new TOAFacturaConActividad(carrito, cliente_original.getId(), 0);
		factura_modificada = new TOAFacturaConActividad(carrito, cliente_original.getId(), 0);
		factura_incorrecta = new TOAFacturaConActividad(carrito, -1, 0);
		idFactura_original = saFactura.create(factura_original);
		factura_original.getTFactura().setId(idFactura_original);
		factura_modificada.getTFactura().setId(idFactura_original);
	}

	@Test
	public void updateFacturaCorrect() {
		int res = saFactura.update(factura_modificada);
		try {
			assertTrue(res > 0);
		} catch (Exception ae) {
			fail(ae.getMessage());
		}
	}

	@Test
	public void updateFacturaNoActiva() {
		saFactura.delete(idFactura_original);
		int res = saFactura.update(factura_modificada);
		try {
			assertTrue(res == -1);
		} catch (Exception ae) {
			fail(ae.getMessage());
		}
	}

	@Test
	public void updateFacturaDatosIncorrectos() {
		int res = saFactura.update(factura_incorrecta);
		try {
			assertTrue(res == -1);
		} catch (Exception ae) {
			fail(ae.getMessage());
		}
	}

	@AfterClass
	public static void destroyClass() {
		saFactura.deleteFisico(idFactura_original);
		saActividad.deleteFisico(actividad.getId());
		saCliente.deleteFisico(cliente_original.getId());
		saCliente.deleteFisico(cliente_nuevo.getId());

	}

}
