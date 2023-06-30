package presentación.factura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.factura.TLineaFactura;
import presentación.IGUI;

public class VistaAñadirOEliminarActividad extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaAñadirOEliminarActividad() {
		setTitle("AÑADIR O ELIMINAR ACTIVIDAD AL CARRITO");
		JPanel panel = new JPanel();
		JLabel lActividad = new JLabel("Id de actividad:");
		final JTextField tActividad = new JTextField(20);
		JLabel lCantidad = new JLabel("Cantidad:");
		final JTextField tCantidad = new JTextField(20);

		JButton añadir = new JButton("AÑADIR");
		JButton eliminar = new JButton("ELIMINAR");
		JButton cancelar = new JButton("CANCELAR");

		panel.add(lActividad);
		panel.add(tActividad);
		panel.add(lCantidad);
		panel.add(tCantidad);
		panel.add(añadir);
		panel.add(eliminar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idActividad = Integer.parseInt(tActividad.getText());
					int cantidad = Integer.parseInt(tCantidad.getText());

					boolean encontrado = false;
					Iterator<TLineaFactura> it = VistaAbrirVenta.carrito.iterator();
					while (!encontrado && it.hasNext()) {
						TLineaFactura linea = it.next();
						if (linea.getIdActividad() == idActividad) {
							linea.setCantidad(cantidad + linea.getCantidad());
							encontrado = true;
						}
					}

					if (!encontrado)
						VistaAbrirVenta.carrito.add(new TLineaFactura(idActividad, 0, cantidad));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idActividad = Integer.parseInt(tActividad.getText());

					boolean encontrado = false;
					Iterator<TLineaFactura> it;
					it = VistaAbrirVenta.carrito.iterator();

					if (VistaAbrirVenta.carrito.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se ha podido encontrar la actividad");
					} else
						while (!encontrado && it.hasNext()) {
							TLineaFactura linea = it.next();
							if (linea.getIdActividad() == idActividad) {
								VistaAbrirVenta.carrito.remove(linea);
								encontrado = true;
							}
						}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	public void actualizar(int evento, Object datos) {

	}
}