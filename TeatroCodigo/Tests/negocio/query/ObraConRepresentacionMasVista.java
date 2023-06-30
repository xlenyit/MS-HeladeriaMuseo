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
import negocio.actividad.TRepresentacion;
import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TOAFacturaConActividad;
import negocio.factura.TLineaFactura;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;

public class ObraConRepresentacionMasVista {
	private static SAObra saObra;
	private static SAActividad saActividad;
	private static SACompañia saComp;
	private static SATemporada saTemp;
	private static SAFactura saFactura;
	private static SACliente saCliente;

	private static TOAFacturaConActividad factura1Obra1;
	private static TOAFacturaConActividad factura2Obra1;
	private static TOAFacturaConActividad factura1Obra2;
	
	private static TCliente cliente = new TCliente(0, "38627197U", "T_QUERY_OR", "T_QUERY_OR", true, true);
	
	private static TObra tObraConMasVistas1 = new TObra(-1, "Comedia", "Va un tio y se cae", "T_QUERY1", "Mike Wazowski", 2021, true);
	private static TObra tObraConMasVistas2 = new TObra(-1, "Comedia", "Va un tio y se cae", "T_QUERY2", "Mike Wazowski", 2021, true);
	private static TObra tObraConMenosVistas = new TObra(-1, "Comedia", "Va un tio y se cae", "T_QUERY3", "Mike Wazowski", 2021, true);
	
	private static TRepresentacion tRepresentacionConMasVistas1 = new TRepresentacion(-1, Integer.MAX_VALUE, 15.0, Date.valueOf("2030-06-11"), Date.valueOf("2030-06-24"), Time.valueOf("00:00:00"), 2, -1, -1, -1, true);
	private static TRepresentacion tRepresentacionConMasVistas2 = new TRepresentacion(-1, Integer.MAX_VALUE, 15.0, Date.valueOf("2030-06-12"), Date.valueOf("2030-06-25"), Time.valueOf("00:00:00"), 2, -1, -1, -1, true);
	private static TRepresentacion tRepresentacionConMasVistas3 = new TRepresentacion(-1, Integer.MAX_VALUE, 15.0, Date.valueOf("2030-06-13"), Date.valueOf("2030-06-26"), Time.valueOf("00:00:00"), 2, -1, -1, -1, true);
	
	private static TCompañia tComp = new TCompañia(-1, "T_QUERY", "Comedia", true);
	private static TTemporada tTemporada = new TTemporada(-1, Integer.MAX_VALUE, 4, Date.valueOf("2030-06-11"), Date.valueOf("2030-06-20"), true);
	
	
	@BeforeClass
	public static void init() {
		saFactura = FactoriaAbstractaNegocio.getInstance().createSAFactura();
		saObra = FactoriaAbstractaNegocio.getInstance().createSAObra();
		saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		saTemp = FactoriaAbstractaNegocio.getInstance().createSATemporada();
		saComp = FactoriaAbstractaNegocio.getInstance().createSACompañia();
		saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
		
		tObraConMasVistas1.setId(saObra.create(tObraConMasVistas1));
		tObraConMasVistas2.setId(saObra.create(tObraConMasVistas2));
		tObraConMenosVistas.setId(saObra.create(tObraConMenosVistas));
		
		tComp.setId(saComp.create(tComp));
		tTemporada.setId(saTemp.create(tTemporada));
		
		//Representacion 1
		tRepresentacionConMasVistas1.setIdCompañia(tComp.getId());
		tRepresentacionConMasVistas1.setIdTemporada(tTemporada.getId());
		tRepresentacionConMasVistas1.setIdObra(tObraConMasVistas1.getId());
		
		tRepresentacionConMasVistas1.setId(saActividad.create(tRepresentacionConMasVistas1));
		
		//Representacion 2
		tRepresentacionConMasVistas2.setIdCompañia(tComp.getId());
		tRepresentacionConMasVistas2.setIdTemporada(tTemporada.getId());
		tRepresentacionConMasVistas2.setIdObra(tObraConMasVistas1.getId());
		
		tRepresentacionConMasVistas2.setId(saActividad.create(tRepresentacionConMasVistas2));
		
		//Representacion 3
		tRepresentacionConMasVistas3.setIdCompañia(tComp.getId());
		tRepresentacionConMasVistas3.setIdTemporada(tTemporada.getId());
		tRepresentacionConMasVistas3.setIdObra(tObraConMasVistas2.getId());
		
		tRepresentacionConMasVistas3.setId(saActividad.create(tRepresentacionConMasVistas3));
		
		Collection<TLineaFactura> carritoRepresentacion1 = new ArrayList<TLineaFactura>();
		carritoRepresentacion1.add(new TLineaFactura(tRepresentacionConMasVistas1.getId(), 0, (Integer.MAX_VALUE / 2) + 1));
		
		Collection<TLineaFactura> carritoRepresentacion2 = new ArrayList<TLineaFactura>();
		carritoRepresentacion2.add(new TLineaFactura(tRepresentacionConMasVistas2.getId(), 0, (Integer.MAX_VALUE / 2)));
		
		Collection<TLineaFactura> carritoRepresentacion3 = new ArrayList<TLineaFactura>();
		carritoRepresentacion3.add(new TLineaFactura(tRepresentacionConMasVistas3.getId(), 0, Integer.MAX_VALUE));
		
		cliente.setId(saCliente.create(cliente));
		
		factura1Obra1 = new TOAFacturaConActividad(carritoRepresentacion1, cliente.getId(), 0);
		factura1Obra1.getTFactura().setId(saFactura.create(factura1Obra1));
		
		factura2Obra1 = new TOAFacturaConActividad(carritoRepresentacion2, cliente.getId(), saFactura.create(factura2Obra1));
		factura2Obra1.getTFactura().setId(saFactura.create(factura2Obra1));
		
		factura1Obra2 = new TOAFacturaConActividad(carritoRepresentacion3, cliente.getId(), saFactura.create(factura1Obra2));
		factura1Obra2.getTFactura().setId(saFactura.create(factura1Obra2));
	}
	
	@Test
	public void test() {
		Collection<TObra> tObra = saObra.obraConRepresentacionMasVista();
		
		try {
			assertTrue(tObra.size() == 2);
			
			for (TObra obra : tObra) {
				assertTrue(obra.getId() == tObraConMasVistas1.getId() || obra.getId() == tObraConMasVistas2.getId());
			}
		}
		catch (Exception exception) {
			fail(exception.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass() {
		saFactura.deleteFisico(factura1Obra1.getTFactura().getId());
		saFactura.deleteFisico(factura2Obra1.getTFactura().getId());
		saFactura.deleteFisico(factura1Obra2.getTFactura().getId());
		
		saCliente.deleteFisico(cliente.getId());
		
		saActividad.deleteFisico(tRepresentacionConMasVistas1.getId());
		saActividad.deleteFisico(tRepresentacionConMasVistas2.getId());
		saActividad.deleteFisico(tRepresentacionConMasVistas3.getId());
		
		saComp.deleteFisico(tComp.getId());
		saTemp.deleteFisico(tTemporada.getId());
		
		saObra.deleteFisico(tObraConMasVistas1.getId());
		saObra.deleteFisico(tObraConMasVistas2.getId());
		saObra.deleteFisico(tObraConMenosVistas.getId());
	}

}
