package presentación.factura;

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

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaMostrarFacturasPorCliente extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;

	public VistaMostrarFacturasPorCliente() {
		setTitle("BUSCAR FACTURAS ASIGNADAS A UN CLIENTE");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de cliente:");
		final JTextField tid = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				try {
					int idC = Integer.parseInt(tid.getText());
					Controlador.getInstance().accion(Evento.BUSCAR_FACTURA_CL, idC);
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
		if (evento == Evento.RES_BUSCAR_FACTURA_CL_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
			JOptionPane.showMessageDialog(this, scrollPane, "FACTURAS", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_BUSCAR_FACTURA_CL_KO)
			JOptionPane.showMessageDialog(this, "No existen facturas asociadas al cliente seleccionado");
	}
}