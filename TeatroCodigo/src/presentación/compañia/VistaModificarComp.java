package presentación.compañia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.compañia.TCompañia;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarComp extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	public VistaModificarComp() {
		setTitle("MODIFICAR COMPAÑIA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de compañia:");
		final JTextField tid = new JTextField(20);
		JLabel lNom = new JLabel("Nombre compañia:");
		final JTextField tNom = new JTextField(20);
		JLabel lTipo = new JLabel("Tipo:");
		final JTextField tTipo = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(lNom);
		panel.add(tNom);
		panel.add(lTipo);
		panel.add(tTipo);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int id = Integer.parseInt(tid.getText());
					String nombre = tNom.getText();
					String tipo = tTipo.getText();
					TCompañia toc = new TCompañia(id, nombre, tipo, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_COMPAÑIA, toc);
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
		if (evento == Evento.RES_MODIFICAR_COMPAÑIA_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente la compañia con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_COMPAÑIA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la compañia");
	}
}