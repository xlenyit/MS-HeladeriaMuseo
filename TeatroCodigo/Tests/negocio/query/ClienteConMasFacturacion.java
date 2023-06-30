package negocio.query;

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
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TOAFacturaConActividad;
import negocio.factura.TLineaFactura;

public class ClienteConMasFacturacion {

	private static SAFactura saFactura;
	private static SAActividad saActividad;
	private static SACliente saCliente;

	private static TOAFacturaConActividad facturaConMayorPrecio1;
	private static TOAFacturaConActividad facturaConMayorPrecio2;
	private static TCliente clienteConMayorFactura1 = new TCliente(0, "38627198M", "T_QUERY_CF", "T_QUERY_CF", true,
			true);
	private static TCliente clienteConMayorFactura2 = new TCliente(0, "38423198K", "T_QUERY_CF", "T_QUERY_CF", true,
			true);

	private static TOAFacturaConActividad facturaConMenorPrecio;
	private static TCliente clienteConMenorFactura = new TCliente(0, "38627194N", "T_QUERY_CF", "T_QUERY_CF", true,
			true);

	private static TActividad actividadMasCara = new TActCultural(0, "T_QUERY_CF_CARA", 2, Date.valueOf("2059-12-12"),
			Date.valueOf("2059-12-13"), Time.valueOf("00:00:00"), 120, 1000000000, true);

	private static TActividad actividadMenosCara = new TActCultural(0, "T_QUERY_CF_BARATA", 1,
			Date.valueOf("2050-12-14"), Date.valueOf("2050-12-18"), Time.valueOf("00:00:00"), 120, 1, true);

	@BeforeClass
	public static void init() {
		saFactura = FactoriaAbstractaNegocio.getInstance().createSAFactura();
		saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();

		actividadMasCara.setId(saActividad.create(actividadMasCara));

		clienteConMayorFactura1.setId(saCliente.create(clienteConMayorFactura1));
		clienteConMayorFactura2.setId(saCliente.create(clienteConMayorFactura2));

		Collection<TLineaFactura> carritoCaro = new ArrayList<TLineaFactura>();
		carritoCaro.add(new TLineaFactura(actividadMasCara.getId(), 0, 1));

		facturaConMayorPrecio1 = new TOAFacturaConActividad(carritoCaro, clienteConMayorFactura1.getId(), 0);
		facturaConMayorPrecio2 = new TOAFacturaConActividad(carritoCaro, clienteConMayorFactura2.getId(), 0);

		facturaConMayorPrecio1.getTFactura().setId(saFactura.create(facturaConMayorPrecio1));
		facturaConMayorPrecio2.getTFactura().setId(saFactura.create(facturaConMayorPrecio2));

		actividadMenosCara.setId(saActividad.create(actividadMenosCara));

		clienteConMenorFactura.setId(saCliente.create(clienteConMenorFactura));

		Collection<TLineaFactura> carritoBarato = new ArrayList<TLineaFactura>();
		carritoCaro.add(new TLineaFactura(actividadMenosCara.getId(), 0, 1));

		facturaConMenorPrecio= new TOAFacturaConActividad(carritoBarato, clienteConMenorFactura.getId(), 0);

		facturaConMenorPrecio.getTFactura().setId(saFactura.create(facturaConMenorPrecio));
	}

	@Test
	public void clienteConMasFacturacion() {
		Collection<TCliente> clientes = saCliente.clienteConMasFacturacion();

		try {
			assertTrue(clientes.size() == 2);

			for (TCliente cliente : clientes) {
				assertTrue(cliente.getId() == clienteConMayorFactura1.getId()
						|| cliente.getId() == clienteConMayorFactura2.getId());
			}
		} catch (Exception ae) {
			fail(ae.getMessage());
		}
	}

	@AfterClass
	public static void destroyClass() {
		saFactura.deleteFisico(facturaConMayorPrecio1.getTFactura().getId());
		saFactura.deleteFisico(facturaConMayorPrecio2.getTFactura().getId());
		saFactura.deleteFisico(facturaConMenorPrecio.getTFactura().getId());

		saActividad.deleteFisico(actividadMasCara.getId());
		saActividad.deleteFisico(actividadMenosCara.getId());

		saCliente.deleteFisico(clienteConMayorFactura1.getId());
		saCliente.deleteFisico(clienteConMayorFactura2.getId());
		saCliente.deleteFisico(clienteConMenorFactura.getId());
	}
}
