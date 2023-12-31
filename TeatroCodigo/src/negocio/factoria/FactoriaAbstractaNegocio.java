package negocio.factoria;

import negocio.actividad.SAActividad;
import negocio.cliente.SACliente;
import negocio.compaņia.SACompaņia;
import negocio.empleado.SAEmpleado;
import negocio.factura.SAFactura;
import negocio.facturaTienda.SAFacturaTienda;
import negocio.marca.SAMarca;
import negocio.miembrosdecompaņia.SAMiembrosDeCompaņia;
import negocio.obra.SAObra;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.temporada.SATemporada;
import negocio.turno.SATurno;

public abstract class FactoriaAbstractaNegocio {

	private static FactoriaAbstractaNegocio instance;

	public static FactoriaAbstractaNegocio getInstance() {
		if (instance == null)
			instance = new FactoriaNegocio();
		return instance;
	}

	public abstract SAMarca createSAMarca();

	public abstract SACompaņia createSACompaņia();

	public abstract SAObra createSAObra();

	public abstract SATemporada createSATemporada();

	public abstract SACliente createSACliente();

	public abstract SAActividad createSAActividad();

	public abstract SAFactura createSAFactura();

	public abstract SAMiembrosDeCompaņia createSAMiembrosDeCompaņia();

	public abstract SATurno createSATurno();

	public abstract SAProducto createSAProducto();

	public abstract SAProveedor createSAProveedor();

	public abstract SAEmpleado createSAEmpleado();

	public abstract SAFacturaTienda createSAFacturaTienda();
}