package presentación.producto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import negocio.producto.TProducto;
import negocio.producto.TProductoConsumible;
import negocio.producto.TProductoDurable;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarProducto extends JFrame implements IGUI {

	public VistaModificarProducto() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MODIFICAR PRODUCTO");
        JButton durable = new JButton("Durable");
        JButton consumible = new JButton("Consumible");
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        JTextField tId = new JTextField(5);
        JTextField tIdMarca = new JTextField(5);
        JTextField tNombre = new JTextField(23);
        JTextField tPrecio = new JTextField(10);
        JTextField tStock = new JTextField(5);
        JTextField tTCaducidad = new JTextField(20);
        JTextField tDurable = new JTextField(9);
        JLabel lId = new JLabel("ID:");
        JLabel lIdMarca = new JLabel("ID marca:");
        JLabel lNombre = new JLabel("Nombre:");
        JLabel lPrecio = new JLabel("Precio:");
        JLabel lStock = new JLabel("Stock:");
        JLabel lTCaducidad = new JLabel("Fecha Caducidad (YYYY-MM-DD):");
        JLabel lDurable = new JLabel("Tipo:");
        JPanel cards = new JPanel();
      
        JPanel vista = new JPanel(new BorderLayout(50,20));
        vista.setBorder(new TitledBorder("Seleccion de Tipo de Empleado para su alta"));
        cards.setBorder(new TitledBorder("Datos"));
        vista.add(cards);

        JPanel lineStart = new JPanel(new GridLayout(0, 1, 5, 5));
        lineStart.setBorder(new TitledBorder("Tipo de Empleado"));
        vista.add(lineStart, BorderLayout.LINE_START);
        lineStart.add(durable);
        lineStart.add(consumible);
        lineStart.add(cancelar);
        setContentPane(vista);
        
        pack();
        setMinimumSize(getSize());
        setSize(900, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        JFrame aux = this;
        durable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.removeAll();	
				cards.add(lId);
				cards.add(tId);
				cards.add(lIdMarca);
				cards.add(tIdMarca);
				cards.add(lNombre);
				cards.add(tNombre);
				cards.add(lPrecio);
				cards.add(tPrecio);
				cards.add(lStock);
				cards.add(tStock);
				cards.add(lDurable);
				cards.add(tDurable);
				cards.add(aceptar);
				cards.setLocale(null);
				SwingUtilities.updateComponentTreeUI(aux);
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						try {
							int id = Integer.parseInt(tId.getText());
							int idMarca = Integer.parseInt(tIdMarca.getText());
							double precio = Double.parseDouble(tPrecio.getText());
							int stock = Integer.parseInt(tStock.getText());
							
							TProducto producto = new TProductoDurable(id, idMarca, tNombre.getText(), stock, precio, tDurable.getText(), true);
							Controlador.getInstance().accion(Evento.MODIFICAR_PRODUCTO, producto);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							setVisible(true);
						}
					}
				});
				
				
			}
        });
        
        consumible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.removeAll();
				cards.add(lId);
				cards.add(tId);
				cards.add(lIdMarca);
				cards.add(tIdMarca);
				cards.add(lNombre);
				cards.add(tNombre);
				cards.add(lPrecio);
				cards.add(tPrecio);
				cards.add(lStock);
				cards.add(tStock);			
				cards.add(lTCaducidad);
				cards.add(tTCaducidad);
				cards.add(aceptar);		
						
				SwingUtilities.updateComponentTreeUI(aux);
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						try {
							int id = Integer.parseInt(tId.getText());
							int idMarca = Integer.parseInt(tIdMarca.getText());
							double precio = Double.parseDouble(tPrecio.getText());
							int stock = Integer.parseInt(tStock.getText());
							Date fecha = Date.valueOf(tTCaducidad.getText());
							
							TProducto producto = new TProductoConsumible(id, idMarca, tNombre.getText(), stock, precio, fecha, true);
							Controlador.getInstance().accion(Evento.MODIFICAR_PRODUCTO, producto);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							setVisible(true);
						}

					}
				});
			}
        });
        cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.PRODUCTO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});

	}

	public void actualizar(int evento, Object datos) {
		setVisible(false);
		if (evento == Evento.RES_MODIFICAR_PRODUCTO_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado el producto con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_PRODUCTO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el producto");
		setVisible(true);
	}
}