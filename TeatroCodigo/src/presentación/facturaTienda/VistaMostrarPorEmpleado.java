/**
 * 
 */
package presentación.facturaTienda;

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

public class VistaMostrarPorEmpleado extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;

	public VistaMostrarPorEmpleado() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("BUSCAR FACTURA POR EMPLEADO");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de empleado:");
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
					int idT = Integer.parseInt(tid.getText());
					Controlador.getInstance().accion(Evento.BUSCAR_FACTURA_EMPLEADO, Integer.parseInt(tid.getText()));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}

			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_BUSCAR_FACTURA_EMPLEADO_OK_TIENDA)
			JOptionPane.showMessageDialog(this, datos.toString());
		else if (evento == Evento.RES_BUSCAR_FACTURA_EMPLEADO_KO_TIENDA)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar la factura asociada al empleado");
		setVisible(true);
	}
}