package presentación.temporada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaEliminarT extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaEliminarT() {
		setTitle("ELIMINAR TEMPORADA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de temporada:");
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
					int idT = Integer.parseInt(tid.getText());
					Controlador.getInstance().accion(Evento.ELIMINAR_TEMPORADA, idT);
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
		if (evento == Evento.RES_ELIMINAR_TEMPORADA_OK)
			JOptionPane.showMessageDialog(this, "Se ha borrado correctamente la temporada con id " + (Integer) datos);
		else if (evento == Evento.RES_ELIMINAR_TEMPORADA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido borrar la temporada");
	}
}