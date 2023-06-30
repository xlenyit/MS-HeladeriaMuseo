package presentación.turno;

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

public class VistaBuscarTurno extends JFrame implements IGUI {
	public VistaBuscarTurno() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("BUSCAR TURNO");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de turno:");
		final JTextField tid = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
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
					int idT = Integer.parseInt(tid.getText());
					Controlador.getInstance().accion(Evento.BUSCAR_TURNO, idT);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
		if (evento == Evento.RES_BUSCAR_TURNO_OK)
			JOptionPane.showMessageDialog(this, datos.toString());
		else if (evento == Evento.RES_BUSCAR_TURNO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar el turno");
		setVisible(true);
	}

}
