/**
 * 
 */
package presentación.proveedor;

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

public class VistaElminarProveedor extends JFrame implements IGUI {

	public VistaElminarProveedor() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ELIMINAR PROVEEDOR");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de proveedor:");
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
					int id = Integer.parseInt(tid.getText());
					Controlador.getInstance().accion(Evento.ELIMINAR_PROVEEDOR, id);
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
					Controlador.getInstance().accion(Evento.PROVEEDOR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_ELIMINAR_PROVEEDOR_OK)
			JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente el proveedor con id " + (Integer) datos);
		else if (evento == Evento.RES_ELIMINAR_PROVEEDOR_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido eliminado el proveedor");

		setVisible(true);
	}
}