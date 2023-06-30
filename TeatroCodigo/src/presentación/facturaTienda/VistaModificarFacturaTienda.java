package presentación.facturaTienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.facturaTienda.TFacturaTienda;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarFacturaTienda extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;

	public VistaModificarFacturaTienda() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ACTUALIZAR FACTURA");
		JPanel panel = new JPanel();
		JLabel lide = new JLabel("Id de empleado:");
		final JTextField tide = new JTextField(20);
		JLabel lFecha = new JLabel("Fecha (YYYY-MM-DD):");
		final JTextField tFecha = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lide);
		panel.add(tide);
		panel.add(lFecha);
		panel.add(tFecha);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.DEVOLVER_PRODUCTO_FACTURA_TIENDA, new TFacturaTienda(0, 0, 0, Date.valueOf(tFecha.getText()), 
							Integer.parseInt(tide.getText()), true));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
		if (evento == Evento.RES_MODIFICAR_FACTURA_OK_TIENDA)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente la factura");
		else if (evento == Evento.RES_MODIFICAR_FACTURA_KO_TIENDA)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la factura");
	}
}