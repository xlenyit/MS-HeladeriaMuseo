kpackage presentaci�n.cliente;

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

import presentaci�n.IGUI;
import presentaci�n.controlador.Controlador;
import presentaci�n.controlador.Evento;

public class VistaClienteConMasFacturacion extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;

	public VistaClienteConMasFacturacion(){
		setTitle("CLIENTE CON M�S FACTURACI�N");
		JPanel panel = new JPanel();
		JLabel mostrar = new JLabel("�Est� seguro de ver el cliente con m�s facturaci�n?");
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(mostrar);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.CLIENTE_CON_MAS_FACTURACION, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "No se ha podido encontrar el cliente");
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_CLIENTE_MAS_FACTURACION_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
			JOptionPane.showMessageDialog(this, scrollPane, "CLIENTES", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_CLIENTE_MAS_FACTURACION_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ningun cliente");
	}

}
