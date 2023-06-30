package presentación.marca;

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

public class VistaEliminarMarca extends JFrame implements IGUI {

	public VistaEliminarMarca() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ELIMINAR MARCA");
		JPanel panel = new JPanel();
		JLabel lId = new JLabel("Id de marca:");
		final JTextField tId = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lId);
		panel.add(tId);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				setVisible(false);
				try {
					int id = Integer.parseInt(tId.getText());
					Controlador.getInstance().accion(Evento.ELIMINAR_MARCA, id);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MARCA, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_ELIMINAR_MARCA_OK)
			JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente la marca con id " + (Integer) datos);
		else if (evento == Evento.RES_ELIMINAR_MARCA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido eliminado la marca");
		setVisible(true);
	}

}