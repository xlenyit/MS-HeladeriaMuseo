package presentación.turno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.turno.TTurno;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarTurno extends JFrame implements IGUI {

	public VistaModificarTurno() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MODIFICAR TURNO");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de turno:");
		final JTextField tid = new JTextField(20);
		JLabel lHorario = new JLabel("Horario:");
		final JTextField tHorario = new JTextField(20);
		JButton aceptar = new JButton("ACEPTAR");
		JButton cancelar = new JButton("CANCELAR");

		panel.add(lid);
		panel.add(tid);
		panel.add(lHorario);
		panel.add(tHorario);
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
					int id = Integer.parseInt(tid.getText());
					String horario = tHorario.getText();

					TTurno tot = new TTurno(id, horario, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_TURNO, tot);
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

		if (evento == Evento.RES_MODIFICAR_TURNO_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente el turno con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_TURNO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el turno");

		setVisible(true);
	}
}
