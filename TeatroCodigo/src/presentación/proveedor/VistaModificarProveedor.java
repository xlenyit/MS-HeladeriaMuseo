/**
 * 
 */
package presentación.proveedor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.proveedor.TProveedor;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarProveedor extends JFrame implements IGUI {

	public VistaModificarProveedor() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MODIFICAR PROVEEDOR");
		JPanel panel = new JPanel();
		JLabel lId = new JLabel("Id de proveedor:");
		final JTextField tId = new JTextField(20);
		JLabel ltelefono = new JLabel("TELEFONO:");
		final JTextField tTelefono = new JTextField(20);
		JLabel lNIF = new JLabel("NIF:");
		final JTextField tNIF = new JTextField(20);
		JLabel lDireccion = new JLabel("DIRECCION:");
		final JTextField tDireccion = new JTextField(20);

		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lId);
		panel.add(tId);
		panel.add(ltelefono);
		panel.add(tTelefono);
		panel.add(lNIF);
		panel.add(tNIF);
		panel.add(lDireccion);
		panel.add(tDireccion);
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
					int id = Integer.parseInt(tId.getText());
					String telefono = tTelefono.getText();
					String NIF = tNIF.getText();
					String direccion = tDireccion.getText();

					TProveedor tproveedor = new TProveedor(id, NIF, telefono, direccion, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_PROVEEDOR, tproveedor);
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

		if (evento == Evento.RES_MODIFICAR_PROVEEDOR_OK)
			JOptionPane.showMessageDialog(this,
					"Se ha modificado correctamente el proveedor con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_PROVEEDOR_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el proveedor");

		setVisible(true);
	}
}
