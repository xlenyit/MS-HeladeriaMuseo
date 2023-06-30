package presentación.marca;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaMostrarMarcas extends JFrame implements IGUI {

	public VistaMostrarMarcas() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MOSTRAR MARCAS");
		JPanel panel = new JPanel();
		JLabel mostrar = new JLabel("¿Mostrar todas las marcas?");
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(mostrar);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MOSTRAR_MARCAS, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MARCA, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_MOSTRAR_MARCAS_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(this, scrollPane, "MARCAS", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_MOSTRAR_MARCAS_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ninguna marca");
		setVisible(true);
	}
}