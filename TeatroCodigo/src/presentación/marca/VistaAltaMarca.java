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

public class VistaAltaMarca extends JFrame implements IGUI {

	public VistaAltaMarca() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ALTA MARCA");
		JPanel panel = new JPanel();
		JLabel lNombre = new JLabel("Nombre:");
		final JTextField tNombre = new JTextField(45);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

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
					String nombre = tNombre.getText();
					TMarca tMarca = new TMarca(nombre);
					Controlador.getInstance().accion(Evento.ALTA_MARCA, tMarca);
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

	@Override
	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_ALTA_MARCA_OK)
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente la marca con id " + (Integer) datos);
		else if (evento == Evento.RES_ALTA_MARCA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido crear la marca");
		setVisible(true);
	}

}