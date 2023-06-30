package presentación.factura;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;

import negocio.factura.TLineaFactura;
import presentación.IGUI;

public class VistaAbrirVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	protected static Collection<TLineaFactura> carrito;

	public VistaAbrirVenta() {
		carrito = new ArrayList<TLineaFactura>();
	}

	@Override
	public void actualizar(int evento, Object datos) {
	}

}