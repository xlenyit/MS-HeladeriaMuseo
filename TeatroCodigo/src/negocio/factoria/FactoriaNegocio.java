package negocio.factoria;

import negocio.actividad.SAActividad;
import negocio.actividad.imp.SAActividadImp;
import negocio.cliente.SACliente;
import negocio.cliente.imp.SAClienteImp;
import negocio.compaņia.SACompaņia;
import negocio.compaņia.imp.SACompaņiaImp;
import negocio.empleado.SAEmpleado;
import negocio.empleado.imp.SAEmpleadoImp;
import negocio.factura.SAFactura;
import negocio.factura.imp.SAFacturaImp;
import negocio.facturaTienda.SAFacturaTienda;
import negocio.facturaTienda.imp.SAFacturaTiendaImp;
import negocio.marca.SAMarca;
import negocio.marca.imp.SAMarcaImp;
import negocio.miembrosdecompaņia.SAMiembrosDeCompaņia;
import negocio.miembrosdecompaņia.imp.SAMiembrosDeCompaņiaImp;
import negocio.obra.SAObra;
import negocio.obra.imp.SAObraImp;
import negocio.producto.SAProducto;
import negocio.producto.imp.SAProductoImp;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.imp.SAProveedorImp;
import negocio.temporada.SATemporada;
import negocio.temporada.imp.SATemporadaImp;
import negocio.turno.SATurno;
import negocio.turno.imp.SATurnoImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio {

	public SACompaņia createSACompaņia() {
		return new SACompaņiaImp();
	}

	public SAObra createSAObra() {
		return new SAObraImp();
	}

	public SATemporada createSATemporada() {
		return new SATemporadaImp();
	}

	public SACliente createSACliente() {
		return new SAClienteImp();
	}

	public SAActividad createSAActividad() {
		return new SAActividadImp();
	}

	public SAFactura createSAFactura() {
		return new SAFacturaImp();
	}

	public SAMiembrosDeCompaņia createSAMiembrosDeCompaņia() {
		return new SAMiembrosDeCompaņiaImp();
	}

	public SATurno createSATurno() {
		return new SATurnoImp();
	}
	
	public SAProducto createSAProducto() {
		return new SAProductoImp();
	}

	public SAProveedor createSAProveedor() {
		return new SAProveedorImp();
	}

	public SAEmpleado createSAEmpleado() {
		return new SAEmpleadoImp();
	}

	public SAFacturaTienda createSAFacturaTienda() {
		return new SAFacturaTiendaImp();
	}

	@Override
	public SAMarca createSAMarca() {
		return new SAMarcaImp();
	}
}