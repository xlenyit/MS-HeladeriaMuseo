package presentaci�n.facturaTienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.facturaTienda.imp.TLineaFacturaTienda;
import presentaci�n.IGUI;
import presentaci�n.controlador.Controlador;
import presentaci�n.controlador.Evento;

public class VistaA�adirOEliminarProducto extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaA�adirOEliminarProducto() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("A�ADIR O ELIMINAR PRODUCTO AL CARRITO");
		JPanel panel = new JPanel();
		JLabel lProducto = new JLabel("Id del producto:");
		final JTextField tProducto = new JTextField(20);
		JLabel lCantidad = new JLabel("Cantidad:");
		final JTextField tCantidad = new JTextField(20);

		JButton a�adir = new JButton("A�ADIR");
		JButton eliminar = new JButton("ELIMINAR");
		JButton cancelar = new JButton("CANCELAR");

		panel.add(lProducto);
		panel.add(tProducto);
		panel.add(lCantidad);
		panel.add(tCantidad);
		panel.add(a�adir);
		panel.add(eliminar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		a�adir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idProducto = Integer.parseInt(tProducto.getText());
					int cantidad = Integer.parseInt(tCantidad.getText());

					boolean encontrado = false;
					Iterator<TLineaFacturaTienda> it = VistaAbrirVentaTienda.carrito.iterator();
					while (!encontrado && it.hasNext()) {
						TLineaFacturaTienda linea = it.next();
						if (linea.getIdProducto() == idProducto) {
							linea.setCantidad(cantidad + linea.getCantidad());
							encontrado = true;
						}
					}

					if (!encontrado)
						VistaAbrirVentaTienda.carrito.add(new TLineaFacturaTienda(idProducto, 0, cantidad, 0));
					Controlador.getInstance().accion(Evento.FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idProducto = Integer.parseInt(tProducto.getText());

					boolean encontrado = false;
					Iterator<TLineaFacturaTienda> it;
					it = VistaAbrirVentaTienda.carrito.iterator();

					if (VistaAbrirVentaTienda.carrito.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se ha podido encontrar la actividad");
					} else
						while (!encontrado && it.hasNext()) {
							TLineaFacturaTienda linea = it.next();
							if (linea.getIdProducto() == idProducto) {
								VistaAbrirVentaTienda.carrito.remove(linea);
								encontrado = true;
							}
						}
					Controlador.getInstance().accion(Evento.FACTURA_TIENDA, null);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {

	}
}