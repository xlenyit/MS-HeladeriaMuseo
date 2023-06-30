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
import negocio.facturaTienda.imp.TFacturaConProducto;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaCerrarVentaTienda extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;

	public VistaCerrarVentaTienda() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("CERRAR VENTA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de empleado:");
		final JTextField tid = new JTextField(20);
		JLabel lFecha = new JLabel("Fecha (YYYY-MM-DD):");
		final JTextField tFecha = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
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
					TFacturaConProducto tFactura =new TFacturaConProducto();
					tFactura.setTLineaFacturaTienda(VistaAbrirVentaTienda.carrito);
					tFactura.añadirTFacturaTienda(new TFacturaTienda(0, 0, 0, Date.valueOf(tFecha.getText()), 
							Integer.parseInt(tid.getText()), true));
					Controlador.getInstance().accion(Evento.ALTA_FACTURA_TIENDA,
							tFactura);
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
		if (evento == Evento.RES_ALTA_FACTURA_OK_TIENDA)
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente la factura con id " + (Integer) datos);
		else if (evento == Evento.RES_ALTA_FACTURA_KO_TIENDA)
			JOptionPane.showMessageDialog(this, "No se ha podido generar la factura");
	}
}