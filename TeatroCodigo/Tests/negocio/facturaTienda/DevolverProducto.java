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

public class DevolverProducto {

	private static TFacturaConProducto factura_correct;
	private static SAFacturaTienda saFactura;
	private static int idProducto;
	private static int idEmpleado;
	private static int idTurno;
	private static int idMarca;
	
	private static int idProducido;
	private static TLineaFacturaTienda tLineaFactura;
	
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
		tLineaFactura = new TLineaFacturaTienda(idProducto, 0, 2, 1);
		tCorrect.añadirLineaFacturaTienda(tLineaFactura);
		tCorrect.añadirTFacturaTienda(new TFacturaTienda(0, 1, 1, Date.valueOf("2021-12-14"), idEmpleado, true));
		factura_correct = tCorrect;
		idProducido = saFactura.cerrarVenta(factura_correct);
		tLineaFactura.setIdFactura(idProducido);
		tLineaFactura.setCantidad(1);
	}
	
	@Test
	public void devolverProductoIncorrecto() {
		
		int res = saFactura.devolverProducto(tLineaFactura);
		
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void devolverProductoCorrecto() {
		tLineaFactura.setIdFactura(-1);
		int res = saFactura.devolverProducto(tLineaFactura);
		
		try{
			assertEquals(-1, res);
		}catch(Exception ae){
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
