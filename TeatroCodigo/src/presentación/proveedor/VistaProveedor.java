/**
 * 
 */
package presentación.proveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;


public class VistaProveedor extends JFrame implements IGUI {

	public VistaProveedor(){
		
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	setTitle("PROVEEDOR");
	JPanel panel = new JPanel();
	JButton alta = new JButton("ALTA");
	JButton buscar = new JButton("BUSCAR");
	JButton baja = new JButton("BAJA");
	JButton modificar = new JButton("MODIFICAR");
	JButton listar = new JButton("LISTAR");
	JButton mostrar_por_producto = new JButton("MOSTRAR PROVEEDORES POR PORDUCTO");
	JButton asignar = new JButton("ASIGNAR PRODUCTO A PROVEEDOR");
	JButton desasignar = new JButton("DESASIGNAR PRODUCTO A PROVEEDOR");
	JButton cancelar = new JButton("Cancelar");

	panel.add(alta);
	panel.add(buscar);
	panel.add(baja);
	panel.add(modificar);
	panel.add(listar);
	panel.add(mostrar_por_producto);
	panel.add(asignar);
	panel.add(desasignar);
	panel.add(cancelar);
	getContentPane().add(panel);
	pack();
	setLocationRelativeTo(null);

	alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_ALTA_PROVEEDOR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_BUSCAR_PROVEEDOR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MODIFICAR_PROVEEDOR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_ELIMINAR_PROVEEDOR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MOSTRAR_PROVEEDOR, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		
		
		mostrar_por_producto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MOSTRAR_PROVEEDOR_PRODUCTO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		
		
		asignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_ASIGNAR_PROVEEDOR_A_PRODUCTO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		
		desasignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_DESASIGNAR_PROVEEDOR_A_PRODUCTO, null);
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

	public void actualizar(int evento, Object datos) {
		
	}
}
