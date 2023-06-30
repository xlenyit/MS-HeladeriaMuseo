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

public class Modificar {
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
		tCorrect.a�adirLineaFacturaTienda(new TLineaFacturaTienda(idProducto, 0, 1, 1));
		tCorrect.a�adirTFacturaTienda(new TFacturaTienda(0, 1, 1, Date.valueOf("2021-12-14"), idEmpleado, true));
		factura_correct = tCorrect;
		idProducido = saFactura.cerrarVenta(factura_correct);
		factura_correct.getTFacturaTienda().setId(idProducido);
	}

	@Test
	public void modificarFacturaCorrecto(){
		factura_correct.getTFacturaTienda().setIdEmpleado(idEmpleado);
		factura_correct.getTFacturaTienda().setFecha(Date.valueOf("2021-12-15"));
		
		
		int res = saFactura.modificar(factura_correct.getTFacturaTienda());
		
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void modificarFacturaIncorrecto(){
		factura_correct.getTFacturaTienda().setIdEmpleado(-1);
		
		int res = saFactura.modificar(factura_correct.getTFacturaTienda());
		
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
