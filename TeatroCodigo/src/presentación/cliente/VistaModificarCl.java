
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

public class VistaModificarCl extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaModificarCl() {
		setTitle("MODIFICAR CLIENTE");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de cliente:");
		final JTextField tid = new JTextField(20);
		JLabel ldni = new JLabel("Dni cliente:");
		final JTextField tdni = new JTextField(20);
		JLabel lnombre = new JLabel("Nombre:");
		final JTextField tnombre = new JTextField(20);
		JLabel lapellidos = new JLabel("Apellidos:");
		final JTextField tapellidos = new JTextField(20);
		JLabel lsocio = new JLabel("Socio");
		final JRadioButton rsocio = new JRadioButton();
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(ldni);
		panel.add(tdni);
		panel.add(lnombre);
		panel.add(tnombre);
		panel.add(lapellidos);
		panel.add(tapellidos);
		panel.add(lsocio);
		panel.add(rsocio);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int id = Integer.parseInt(tid.getText());
					String DNIT = tdni.getText();
					String nombre = tnombre.getText();
					String fechaInicio = tapellidos.getText();
					boolean socio = rsocio.isSelected();

					TCliente toc = new TCliente(id, DNIT, nombre, fechaInicio, socio, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_CLIENTE, toc);
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
		if (evento == Evento.RES_MODIFICAR_CLIENTE_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente el cliente con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_CLIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el cliente");
	}
}