package presentación.facturaTienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.facturaTienda.imp.TLineaFacturaTienda;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaDevolverProducto extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaDevolverProducto() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("DEVOLVER PRODUCTO");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de factura:");
		final JTextField tid = new JTextField(20);
		JLabel lidP = new JLabel("Id de producto:");
		final JTextField tidP = new JTextField(20);
		JLabel lcantidad = new JLabel("Cantidad:");
		final JTextField tcantidad = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(lidP);
		panel.add(tidP);
		panel.add(lcantidad);
		panel.add(tcantidad);
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
					Controlador.getInstance().accion(Evento.DEVOLVER_PRODUCTO_FACTURA_TIENDA,
							new TLineaFacturaTienda(Integer.parseInt(tidP.getText()), Integer.parseInt(tid.getText()),
									Integer.parseInt(tcantidad.getText()), 0));
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
		if (evento == Evento.RES_DEVOLVER_PRODUCTO_FACTURA_OK_TIENDA)
			JOptionPane.showMessageDialog(this, "Se ha devuelto correctamente el producto");
		else if (evento == Evento.RES_DEVOLVER_PRODUCTO_FACTURA_KO_TIENDA)
			JOptionPane.showMessageDialog(this, "No se ha podido devolver el producto");
	}
}