package presentación.facturaTienda;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaMostrarTodasFacturasTienda extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;

	public VistaMostrarTodasFacturasTienda() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MOSTRAR FACTURAS");
		JPanel panel = new JPanel();
		JLabel mostrar = new JLabel("¿Mostrar todas las facturas?");
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(mostrar);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MOSTRAR_FACTURA_TIENDA, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getLocalizedMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MOSTRAR_FACTURA_OK_TIENDA) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(this, scrollPane, "FACTURAS", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_MOSTRAR_FACTURA_KO_TIENDA)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ninguna factura");
	}
}