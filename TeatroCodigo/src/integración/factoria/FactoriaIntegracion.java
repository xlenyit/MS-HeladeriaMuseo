package integraci�n.factoria;

import integraci�n.actividad.DAOActividad;
import integraci�n.actividad.imp.DAOActividadImp;
import integraci�n.cliente.DAOCliente;
import integraci�n.cliente.imp.DAOClienteImp;
import integraci�n.compa�ia.DAOCompa�ia;
import integraci�n.compa�ia.imp.DAOCompa�iaImp;
import integraci�n.factura.DAOFactura;
import integraci�n.factura.DAOLineaFactura;
import integraci�n.factura.imp.DAOFacturaImp;
import integraci�n.factura.imp.DAOLineaFacturaImp;
import integraci�n.miembrosdecompa�ia.DAOLineaMiembros;
import integraci�n.miembrosdecompa�ia.DAOMiembrosDeCompa�ia;
import integraci�n.miembrosdecompa�ia.imp.DAOLineaMiembrosImp;
import integraci�n.miembrosdecompa�ia.imp.DAOMiembrosDeCompa�iaImp;
import integraci�n.obra.DAOObra;
import integraci�n.obra.imp.DAOObraimp;
import integraci�n.temporada.DAOTemporada;
import integraci�n.temporada.imp.DAOTemporadaImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion {

	public DAOMiembrosDeCompa�ia createDAOMiembrosDeCompa�ia() {
		return new DAOMiembrosDeCompa�iaImp();
	}

	public DAOCompa�ia createDAOCompa�ia() {
		return new DAOCompa�iaImp();
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