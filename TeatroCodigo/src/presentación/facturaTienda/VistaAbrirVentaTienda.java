package presentación.facturaTienda;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import negocio.facturaTienda.imp.TLineaFacturaTienda;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaAbrirVentaTienda extends JFrame implements IGUI {

	static final long serialVersionUID = 1L;
	protected static Collection<TLineaFacturaTienda> carrito;

	public VistaAbrirVentaTienda() {
		try {
			carrito = new ArrayList<TLineaFacturaTienda>();
			Controlador.getInstance().accion(Evento.FACTURA_TIENDA, null);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizar(int evento, Object datos) {
	}
}