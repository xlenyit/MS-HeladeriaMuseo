package negocio.factura;

import static org.junit.Assert.assertFalse;
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

public class ReadAll {
	
	private static SAFactura saFactura;
	private static SAActividad saActividad;
	private static SACliente saCliente;

	private static TOAFacturaConActividad factura;
	private static TActividad actividad = new TActCultural(0, "test actividad", 1, Date.valueOf("2033-12-12"),
			Date.valueOf("2033-12-13"), Time.valueOf("00:00:00"), 120, 1.5, true);
	private static TCliente cliente = new TCliente(0, "35617897J", "test cliente", "test cliente", true, true);
	
	private static int idFactura;

	@BeforeClass
	public static void inicio() {
		saFactura = FactoriaNegocio.getInstance().createSAFactura();
		saActividad = FactoriaNegocio.getInstance().createSAActividad();
		saCliente = FactoriaNegocio.getInstance().createSACliente();

		actividad.setId(saActividad.create(actividad));
		cliente.setId(saCliente.create(cliente));
		Collection<TLineaFactura> carrito = new ArrayList<TLineaFactura>();
		carrito.add(new TLineaFactura(actividad.getId(), 0, 1));
		factura = new TOAFacturaConActividad(carrito, cliente.getId(), 0);
		idFactura=saFactura.create(factura);
		factura.getTFactura().setId(idFactura);
	}

	@Test
	public void buscarFacturaCorrecto() {
		Collection<TFactura> res = saFactura.readAll();
		
		try {
			assertFalse(res.isEmpty());
		} catch (Exception ae) {
			fail(ae.getMessage());
		}
	}

	@AfterClass
	public static void destroyClass() {
		saFactura.deleteFisico(idFactura);
		saActividad.deleteFisico(actividad.getId());
		saCliente.deleteFisico(cliente.getId());
	}
}
