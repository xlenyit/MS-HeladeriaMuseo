package presentación.miembrosdecompañia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.miembrosdecompañia.TLineaMiembro;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaEliminarDeCompañia extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaEliminarDeCompañia() {
		setTitle("DESASIGNAR MIEMBRO DE COMPAÑIA");
		JPanel panel = new JPanel();
		JLabel lidMiembro = new JLabel("Id de miembro:");
		final JTextField tidMiembro = new JTextField(20);
		JLabel lidCompañia = new JLabel("Id de compañia:");
		final JTextField tidCompañia = new JTextField(20);

		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lidMiembro);
		panel.add(tidMiembro);
		panel.add(lidCompañia);
		panel.add(tidCompañia);

		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idMiembro = Integer.parseInt(tidMiembro.getText());
					int idCompañia = Integer.parseInt(tidCompañia.getText());

					TLineaMiembro tomc = new TLineaMiembro(idCompañia, idMiembro, 0);

					Controlador.getInstance().accion(Evento.ELIMINAR_MIEMBRO_DE_COMPAÑIA, tomc);
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
		if (evento == Evento.RES_ELIMINAR_MIEMBRO_DE_COMPAÑIA_OK)
			JOptionPane.showMessageDialog(this, "La desasignación ha sido realizada con éxito");
		else if (evento == Evento.RES_ELIMINAR_MIEMBRO_DE_COMPAÑIA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido realizar la desasignación");
	}
}