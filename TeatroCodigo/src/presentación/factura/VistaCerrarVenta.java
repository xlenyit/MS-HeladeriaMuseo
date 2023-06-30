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

public class VistaCerrarVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaCerrarVenta() {
		setTitle("CERRAR VENTA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de cliente:");
		final JTextField tid = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.ALTA_FACTURA,
							new TOAFacturaConActividad(VistaAbrirVenta.carrito, Integer.parseInt(tid.getText()), 0));
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
		if (evento == Evento.RES_ALTA_FACTURA_OK)
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente la factura con id " + (Integer) datos);
		else if (evento == Evento.RES_ALTA_FACTURA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido generar la factura");
	}
}