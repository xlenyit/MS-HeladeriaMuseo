package presentación.producto;

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
import javax.swing.JTextField;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaMostrarProductosPorProveedor extends JFrame implements IGUI {

	public VistaMostrarProductosPorProveedor() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MOSTRAR PRODUCTOS DE UN PROVEEDOR");
		JPanel panel = new JPanel();
		JLabel lIdProv = new JLabel("ID Proveedor:");
		final JTextField tIdProv = new JTextField(10);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lIdProv);
		panel.add(tIdProv);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MOSTRAR_PRODUCTO_PROVEEDOR, Integer.parseInt(tIdProv.getText()));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}


			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				try {
					Controlador.getInstance().accion(Evento.PRODUCTO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);

		if (evento == Evento.MOSTRAR_PRODUCTO_PROVEEDOR_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(this, scrollPane, "PRODUCTOS", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.MOSTRAR_PRODUCTO_PROVEEDOR_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ningun producto");

		setVisible(true);
	}
}
