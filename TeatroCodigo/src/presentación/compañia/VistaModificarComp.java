package presentaci�n.compa�ia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.compa�ia.TCompa�ia;
import presentaci�n.IGUI;
import presentaci�n.controlador.Controlador;
import presentaci�n.controlador.Evento;

public class VistaModificarComp extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	public VistaModificarComp() {
		setTitle("MODIFICAR COMPA�IA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de compa�ia:");
		final JTextField tid = new JTextField(20);
		JLabel lNom = new JLabel("Nombre compa�ia:");
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
					TCompa�ia toc = new TCompa�ia(id, nombre, tipo, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_COMPA�IA, toc);
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
		if (evento == Evento.RES_MODIFICAR_COMPA�IA_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente la compa�ia con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_COMPA�IA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la compa�ia");
	}
}