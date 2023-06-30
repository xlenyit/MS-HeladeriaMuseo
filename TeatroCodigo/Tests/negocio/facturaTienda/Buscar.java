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

public class Buscar {
	
	private static TFacturaConProducto factura_correct;
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
		factura_correct = tCorrect;
		idProducido = saFactura.cerrarVenta(factura_correct);
	}

	@Test
	public void buscarFacturaCorrecto(){
		TFacturaConProducto res = saFactura.buscar(idProducido);
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarFacturaIncorrecto(){
		TFacturaConProducto res = saFactura.buscar(-1);
		
		try{
			assertNull(res);
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
