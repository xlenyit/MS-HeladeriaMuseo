package negocio.facturaTienda.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import negocio.facturaTienda.TFacturaTienda;

public class TFacturaConProducto {
	
	private Collection<TLineaFacturaTienda> tLineaFacturaTienda = new ArrayList<TLineaFacturaTienda>();
	
	private TFacturaTienda tFacturaTienda;

	public TFacturaConProducto() {

	}

	public void añadirLineaFacturaTienda(TLineaFacturaTienda lineaFacturaTienda) {
		tLineaFacturaTienda.add(lineaFacturaTienda);
	}

	public void eliminarLineaFacturaTienda(int idProducto) {
		Iterator<TLineaFacturaTienda> it = tLineaFacturaTienda.iterator();
		boolean encontrado = false;
		while (it.hasNext() && !encontrado) {
			TLineaFacturaTienda a = it.next();
			if (a.getIdProducto() == idProducto) {
				encontrado = true;
				tLineaFacturaTienda.remove(a);
			}
		}
	}

	public void añadirTFacturaTienda(TFacturaTienda facturaTienda) {
		this.tFacturaTienda = facturaTienda;
	}

	public TFacturaTienda getTFacturaTienda() {
		return tFacturaTienda;
	}

	public Collection<TLineaFacturaTienda> getTLineaFacturaTienda() {
		return tLineaFacturaTienda;
	}

	public void setTLineaFacturaTienda(Collection<TLineaFacturaTienda> lineaFactura) {
		tLineaFacturaTienda = lineaFactura;
	}

	public String toString() {
		String r = tFacturaTienda.toString() + '\n';
		Iterator<TLineaFacturaTienda> it = tLineaFacturaTienda.iterator();
		while (it.hasNext()) {
			r = r + it.next().toString() + '\n';
		}
		return r;
	}
}