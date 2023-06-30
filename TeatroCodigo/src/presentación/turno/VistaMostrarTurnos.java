package presentación.turno;

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

public class VistaMostrarTurnos extends JFrame implements IGUI {

	public VistaMostrarTurnos() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MOSTRAR TURNOS");
		JPanel panel = new JPanel();
		JLabel mostrar = new JLabel("¿Mostrar todos los turnos?");
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
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MOSTRAR_TURNOS, null);
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
					Controlador.getInstance().accion(Evento.TURNO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);

		if (evento == Evento.RES_MOSTRAR_TURNOS_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(this, scrollPane, "TURNOS", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_MOSTRAR_TURNOS_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ningun turno");

		setVisible(true);
	}
}