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

public class VistaAltaTurno extends JFrame implements IGUI {

	public VistaAltaTurno() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ALTA TURNO");
		JPanel panel = new JPanel();
		JLabel lHorario = new JLabel("Horario:");
		final JTextField tHorario = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
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
					String horario = tHorario.getText();
					TTurno tot = new TTurno(horario);
					Controlador.getInstance().accion(Evento.ALTA_TURNO, tot);
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

	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_ALTA_TURNO_OK)
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente el turno con id " + (Integer) datos);
		else if (evento == Evento.RES_ALTA_TURNO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido crear el turno");
		setVisible(true);
	}
}
