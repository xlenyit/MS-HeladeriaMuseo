
package presentación.proveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.proveedor.TLineaProveedorProducto;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaDesasignarProveedorAProducto extends JFrame implements IGUI {

	public VistaDesasignarProveedorAProducto() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("DESASIGNAR PROVEEDOR A PRODUCTO");
		JPanel panel = new JPanel();
		JLabel lidprov = new JLabel("Id de proveedor:");
		final JTextField tidprov = new JTextField(20);
		JLabel lidprod = new JLabel("Id de producto:");
		final JTextField tidprod = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lidprod);
		panel.add(tidprod);
		panel.add(lidprov);
		panel.add(tidprov);
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

					int idTprov = Integer.parseInt(tidprov.getText());
					int idTprod = Integer.parseInt(tidprod.getText());
					TLineaProveedorProducto tLinea = new TLineaProveedorProducto(idTprod, idTprov);

					Controlador.getInstance().accion(Evento.DESASIGNAR_PROVEEDOR_A_PRODUCTO, tLinea);
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
		if (evento == Evento.RES_DESASIGNAR_PROVEEDOR_A_PRODUCTO_OK)
			JOptionPane.showMessageDialog(this,
					"Se han desasignado el producto y el proveedor con:" + '\n' + datos.toString());
		else if (evento == Evento.RES_DESASIGNAR_PROVEEDOR_A_PRODUCTO_KO)
			JOptionPane.showMessageDialog(this, "No se han podido desasignar el producto y el proveedor");
		setVisible(true);
	}
}