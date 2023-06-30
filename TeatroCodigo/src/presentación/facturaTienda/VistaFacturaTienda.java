package presentación.facturaTienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaFacturaTienda extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;


	public VistaFacturaTienda() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("FACTURA");
		JPanel panel = new JPanel();
		JButton abrir = new JButton("ABRIR VENTA");
		JButton añadirOEliminar = new JButton("AÑADIR O ELIMINAR PRODUCTO");
		JButton cerrar = new JButton("CERRAR VENTA");
		JButton buscar = new JButton("BUSCAR FACTURA");
		JButton devolver = new JButton("DEVOLVER PRODUCTO");
		JButton modificar = new JButton("MODIFICAR FACTURA");
		JButton mostrar = new JButton("MOSTRAR FACTURAS");
		JButton mostrarEm = new JButton("MOSTRAR FACTURAS ASOCIADAS A UN EMPLEADO");
		JButton cancelar = new JButton("CANCELAR");

		panel.add(abrir);
		panel.add(añadirOEliminar);
		panel.add(cerrar);
		panel.add(buscar);
		panel.add(devolver);
		panel.add(modificar);
		panel.add(mostrar);
		panel.add(mostrarEm);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);

		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_ABRIR_VENTA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		añadirOEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_AÑADIR_O_ELIMINAR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_CERRAR_VENTA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_BUSCAR_FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		devolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_DEVOLVER_PRODUCTO_FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MODIFICAR_FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MOSTRAR_FACTURA_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mostrarEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_BUSCAR_FACTURA_EMPLEADO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MENU_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}


	@Override
	public void actualizar(int evento, Object datos) {		
	}

}
