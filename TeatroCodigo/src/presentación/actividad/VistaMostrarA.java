package presentación.actividad;

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

public class VistaMostrarA extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaMostrarA() {
		setTitle("MOSTRAR ACTIVIDADES");
		JPanel panel = new JPanel();
		JLabel mostrar = new JLabel("¿Mostrar todas las actividades?");
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(mostrar);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MOSTRAR_ACTIVIDAD, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getLocalizedMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
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
		if (evento == Evento.RES_MOSTRAR_ACTIVIDAD_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(this, scrollPane, "ACTIVIDADES", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_MOSTRAR_ACTIVIDAD_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ninguna actividad");
	}
}