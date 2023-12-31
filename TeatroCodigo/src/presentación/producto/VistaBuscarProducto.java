package presentación.producto;

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

public class VistaBuscarProducto extends JFrame implements IGUI {

	public VistaBuscarProducto() {
		setTitle("BUSCAR PRODUCTO");
		JPanel panel = new JPanel();
		JLabel lId = new JLabel("ID:");
		final JTextField tId = new JTextField(10);

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
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.BUSCAR_PRODUCTO, Integer.parseInt(tId.getText()));
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
					Controlador.getInstance().accion(Evento.PRODUCTO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}


	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_BUSCAR_PRODUCTO_OK)
			JOptionPane.showMessageDialog(this, datos.toString());
		else if (evento == Evento.RES_BUSCAR_PRODUCTO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar el producto");
		setVisible(true);
	}
}