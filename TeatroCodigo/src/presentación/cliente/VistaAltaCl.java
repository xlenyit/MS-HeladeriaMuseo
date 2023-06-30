
package presentación.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.cliente.TCliente;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;


public class VistaAltaCl extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;


	public VistaAltaCl() {
		setTitle("AÑADIR CLIENTE");
		JPanel panel = new JPanel();
		JLabel lDNI = new JLabel("DNI:");
		final JTextField tDNI = new JTextField(20);
		JLabel lNombre = new JLabel("Nombre:");
		final JTextField tNombre = new JTextField(20);
		JLabel lApellidos = new JLabel("Apellidos:");
		final JTextField tApellidos = new JTextField(20);
		final JRadioButton rSocio = new JRadioButton("Socio");
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lDNI);
		panel.add(tDNI);
		panel.add(lNombre);
		panel.add(tNombre);
		panel.add(lApellidos);
		panel.add(tApellidos);
		panel.add(rSocio);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					String DNIT = tDNI.getText();
					String nombre = tNombre.getText();
					String fechaInicio = tApellidos.getText();
					boolean socio = rSocio.isSelected();

					TCliente toc = new TCliente(0, DNIT, nombre, fechaInicio, socio, true);
					Controlador.getInstance().accion(Evento.ALTA_CLIENTE, toc);
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
		if (evento == Evento.RES_ALTA_CLIENTE_OK) 
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente el cliente con id " + (Integer) datos);
		else if(evento == Evento.RES_ALTA_CLIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido crear el cliente");
	}
}