package presentación.marca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.marca.TMarca;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarMarca extends JFrame implements IGUI {

	public VistaModificarMarca() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MODIFICAR MARCA");
		JPanel panel = new JPanel();
		JLabel lId = new JLabel("Id de la marca:");
		final JTextField tId = new JTextField(20);
		JLabel lNombre = new JLabel("Nombre:");
		final JTextField tNombre = new JTextField(20);
		JButton aceptar = new JButton("ACEPTAR");
		JButton cancelar = new JButton("CANCELAR");

		panel.add(lId);
		panel.add(tId);
		panel.add(lNombre);
		panel.add(tNombre);
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
					String nombre = tNombre.getText();

					TMarca tMarca = new TMarca(id, nombre, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_MARCA, tMarca);
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
		if (evento == Evento.RES_MODIFICAR_MARCA_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente la marca con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_MARCA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la marca");
		setVisible(true);
	}

}