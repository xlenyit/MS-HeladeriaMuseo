package presentaci�n.miembrosdecompa�ia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.miembrosdecompa�ia.TLineaMiembro;
import presentaci�n.IGUI;
import presentaci�n.controlador.Controlador;
import presentaci�n.controlador.Evento;

public class VistaAsignarACompa�ia extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaAsignarACompa�ia() {
		setTitle("ASIGNAR MIEMBRO A COMPA�IA");
		JPanel panel = new JPanel();
		JLabel lidMiembro = new JLabel("Id de miembro:");
		final JTextField tidMiembro = new JTextField(20);
		JLabel lidCompa�ia = new JLabel("Id de compa�ia:");
		final JTextField tidCompa�ia = new JTextField(20);
		JLabel lnMeses = new JLabel("N�mero de meses:");
		final JTextField tnMeses = new JTextField(20);

		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lidMiembro);
		panel.add(tidMiembro);
		panel.add(lidCompa�ia);
		panel.add(tidCompa�ia);
		panel.add(lnMeses);
		panel.add(tnMeses);

		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idMiembro = Integer.parseInt(tidMiembro.getText());
					int idCompa�ia = Integer.parseInt(tidCompa�ia.getText());
					int nMeses = Integer.parseInt(tnMeses.getText());

					TLineaMiembro tomc = new TLineaMiembro(idCompa�ia, idMiembro, nMeses);
					Controlador.getInstance().accion(Evento.ASIGNAR_MIEMBRO_A_COMPA�IA, tomc);
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
		if (evento == Evento.RES_ASIGNAR_MIEMBRO_A_COMPA�IA_OK)
			JOptionPane.showMessageDialog(this, "La asignaci�n ha sido realizada con �xito");
		else if (evento == Evento.RES_ASIGNAR_MIEMBRO_A_COMPA�IA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido realizar la asignaci�n");
	}
}