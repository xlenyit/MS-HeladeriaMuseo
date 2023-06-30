package integración.factoria;

import integración.actividad.DAOActividad;
import integración.actividad.imp.DAOActividadImp;
import integración.cliente.DAOCliente;
import integración.cliente.imp.DAOClienteImp;
import integración.compañia.DAOCompañia;
import integración.compañia.imp.DAOCompañiaImp;
import integración.factura.DAOFactura;
import integración.factura.DAOLineaFactura;
import integración.factura.imp.DAOFacturaImp;
import integración.factura.imp.DAOLineaFacturaImp;
import integración.miembrosdecompañia.DAOLineaMiembros;
import integración.miembrosdecompañia.DAOMiembrosDeCompañia;
import integración.miembrosdecompañia.imp.DAOLineaMiembrosImp;
import integración.miembrosdecompañia.imp.DAOMiembrosDeCompañiaImp;
import integración.obra.DAOObra;
import integración.obra.imp.DAOObraimp;
import integración.temporada.DAOTemporada;
import integración.temporada.imp.DAOTemporadaImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion {

	public DAOMiembrosDeCompañia createDAOMiembrosDeCompañia() {
		return new DAOMiembrosDeCompañiaImp();
	}

	public DAOCompañia createDAOCompañia() {
		return new DAOCompañiaImp();
	}

	public DAOObra createDAOObra() {
		return new DAOObraimp();
	}

	public DAOTemporada createDAOTemporada() {
		return new DAOTemporadaImp();
	}

	public DAOCliente createDAOCliente() {
		return new DAOClienteImp();
	}

	public DAOActividad createDAOActividad() {
		return new DAOActividadImp();
	}

	public DAOFactura createDAOFactura() {
		return new DAOFacturaImp();
	}

	public DAOLineaMiembros createDAOLineaMiembros() {
		return new DAOLineaMiembrosImp();
	}

	public DAOLineaFactura createDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}
}