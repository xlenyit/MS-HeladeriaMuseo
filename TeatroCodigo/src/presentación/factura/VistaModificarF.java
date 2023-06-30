package presentación.factura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.factura.TOAFacturaConActividad;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarF extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaModificarF() {
		setTitle("ACTUALIZAR FACTURA");
		JPanel panel = new JPanel();
		JLabel lna = new JLabel("Se modificará con el carrito actual.");
		JLabel lidc = new JLabel("Id de cliente:");
		final JTextField tidc = new JTextField(20);
		JLabel lidf = new JLabel("Id de factura:");
		final JTextField tidf = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lna);
		panel.add(lidc);
		panel.add(tidc);
		panel.add(lidf);
		panel.add(tidf);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MODIFICAR_FACTURA,
							new TOAFacturaConActividad(VistaAbrirVenta.carrito, Integer.parseInt(tidc.getText()),
									Integer.parseInt(tidf.getText())));
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
		if (evento == Evento.RES_MODIFICAR_FACTURA_OK) {
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente la factura con id " + (Integer) datos);
		} else if (evento == Evento.RES_MODIFICAR_FACTURA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la factura");
	}
}