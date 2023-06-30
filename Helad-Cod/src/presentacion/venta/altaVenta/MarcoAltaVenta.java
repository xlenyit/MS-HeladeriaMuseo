package presentacion.venta.altaVenta;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.producto.TProducto;
import negocio.trabajador.TTrabajador;
import negocio.venta.TLineaVenta;
import negocio.venta.TVenta;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MarcoAltaVenta extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelsLineaDeVenta;
	private JLabel labelAddProductoIDProducto;
	private JLabel labelAddProductoCantidad;
	private JLabel labelIdTrabajador;
	private JButton botonVaciarCarrito;
	private JButton botonAbrirVenta;
	private JButton botonCancelarVenta;
	private JButton botonAddProductoAVenta;
	private JTextField textFieldIdTrabajador;
	private JTextField textFieldAddProductoIDProducto;
	private JTextField textFieldAddProductoCantidad;
	private JTable tablaDatos; 
	private JScrollPane scrollProductos;
	private JPanel panelProductos;
	private String text = "CARRITO: \n";
	private List<TLineaVenta> tLineaVentas = new ArrayList<TLineaVenta>();
	private TVenta tVenta;
	private TablaDatosModel tablaDatosModel;
	
	public MarcoAltaVenta(){
		initGUI();
		tVenta = new TVenta();
	}
	
	private void initGUI() {
		this.setTitle("Alta Venta");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de una nueva Venta");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelsLineaDeVenta = new JLabel("Añadir producto: ");
		
		labelAddProductoIDProducto = new JLabel("Id: ");
		textFieldAddProductoIDProducto = new JTextField();
		textFieldAddProductoIDProducto.setPreferredSize(new Dimension(100, 20));
		
		labelAddProductoCantidad = new JLabel("Cantidad: ");
		textFieldAddProductoCantidad = new JTextField();
		textFieldAddProductoCantidad.setPreferredSize(new Dimension(100, 20));
		
		labelIdTrabajador = new JLabel("Id del trabajador: ");
		textFieldIdTrabajador= new JTextField();
		textFieldIdTrabajador.setPreferredSize(new Dimension(40, 20));
		
		botonAddProductoAVenta = new JButton("Añadir");
		botonAddProductoAVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TLineaVenta tLineaVenta = new TLineaVenta(Integer.parseInt(textFieldAddProductoCantidad.getText()), new TProducto (Integer.parseInt(textFieldAddProductoIDProducto.getText())));
				tVenta.addProduct(tLineaVenta);
				tablaDatosModel.addProduct(Integer.parseInt(textFieldAddProductoIDProducto.getText()), Integer.parseInt(textFieldAddProductoCantidad.getText()));
			}
			
		});
		
		botonVaciarCarrito = new JButton("<html><p>Eliminar</p><p>producto</p></html>");
		botonVaciarCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panelEliminar = new JPanel();
				panelEliminar.add(new JLabel("ID Producto: "));
				
				JTextField idEliminar = new JTextField();
				idEliminar.setPreferredSize(new Dimension(50,20));
				panelEliminar.add(idEliminar);
				
				String[] options = {"ELIMINAR", "CANCELAR"};
				int op = JOptionPane.showOptionDialog(MarcoAltaVenta.this, panelEliminar, "Eliminar producto",
														JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, options, options[1]);
				
				if (op == JOptionPane.YES_OPTION) {
					try {
						if (!tVenta.removeProduct(Integer.parseInt(idEliminar.getText()))) {
							JOptionPane.showMessageDialog(null, "El producto no se encuentra en el carrito",
																"ERROR Eliminar del carrito", JOptionPane.ERROR_MESSAGE);
						}else
							tablaDatosModel.removeProducts(Integer.parseInt(idEliminar.getText()));
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "ID producto tiene que ser un numero entero", "ERROR Eliminar del Carrito", JOptionPane.ERROR_MESSAGE);
					}
				}
				idEliminar.setText("");
			}
			
		});
		
		
		selectors.add(labelsLineaDeVenta);
		selectors.add(labelAddProductoIDProducto);
		selectors.add(textFieldAddProductoIDProducto);
		selectors.add(labelAddProductoCantidad);
		selectors.add(textFieldAddProductoCantidad);
		selectors.add(botonAddProductoAVenta);
		selectors.add(labelIdTrabajador);
		selectors.add(textFieldIdTrabajador);

		emergent.add(selectors);
		

		panelProductos = new JPanel();
		tablaDatosModel = new TablaDatosModel();
		tablaDatos = new JTable(tablaDatosModel);
		panelProductos.add(new JScrollPane(tablaDatos));
		tablaDatos.setBounds(25, 75, 500, 200);
		scrollProductos = new JScrollPane(tablaDatos);
		scrollProductos.setPreferredSize(new Dimension(500, 200));
		
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));
		panelProductos.add(scrollProductos);
		panelAux.add(botonVaciarCarrito);
		panelProductos.add(panelAux);
		
		panelProductos.setBackground(Color.white);
		emergent.add(panelProductos);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAbrirVenta = new JButton("Aceptar");
		botonAbrirVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tVenta.setTrabajador(new TTrabajador(Integer.parseInt(textFieldIdTrabajador.getText())));
				Controlador.getInstance().update(Evento.ALTA_VENTA, tVenta);
				MarcoAltaVenta.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(labelIdTrabajador);
		buttonsPanel.add(textFieldIdTrabajador);
		buttonsPanel.add(botonAbrirVenta);
		buttonsPanel.add(botonAbrirVenta);
		
		botonCancelarVenta = new JButton("Cancelar");
		botonCancelarVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaVenta.this.setVisible(false);
			}
			
		});
		
		buttonsPanel.add(botonCancelarVenta);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(true);
		this.pack();
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Integer evento, Object datos) {
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "Error en el Alta de la Venta");
			break;
		case 0:
			tLineaVentas = (List<TLineaVenta>) datos;
			for(TLineaVenta tLineaVenta: tLineaVentas){
				text += "Id: " + tLineaVenta.getProducto().getId() + " || Cantidad: " + tLineaVenta.getCantidad()+ " || Precio: " + tLineaVenta.getProducto().getPrecio() + "\n";
			}
			break;
		case Evento.PRODUCTO_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "El producto introducido no existe");
			tLineaVentas = (List<TLineaVenta>) datos;
			if(!tLineaVentas.isEmpty()) tLineaVentas.remove(tLineaVentas.size()-1);
			for(TLineaVenta tLineaVenta: tLineaVentas){
				text += "Id: " + tLineaVenta.getProducto().getId() + " || Cantidad: " + tLineaVenta.getCantidad()+ " || Precio: " + tLineaVenta.getProducto().getPrecio() + "\n";
			}
			break;
		case Evento.PRODUCTO_NO_ACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "El producto introducido no esta activo");
			tLineaVentas = (List<TLineaVenta>) datos;
			if(!tLineaVentas.isEmpty()) tLineaVentas.remove(tLineaVentas.size()-1);
			for(TLineaVenta tLineaVenta: tLineaVentas){
				text += "Id: " + tLineaVenta.getProducto().getId() + " || Cantidad: " + tLineaVenta.getCantidad()+ " || Precio: " + tLineaVenta.getProducto().getPrecio() + "\n";
			}
			break;
		case Evento.NO_STOCK:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "No hay stock suficiente de ese producto");
			tLineaVentas = (List<TLineaVenta>) datos;
			if(!tLineaVentas.isEmpty()) tLineaVentas.remove(tLineaVentas.size()-1);
			for(TLineaVenta tLineaVenta: tLineaVentas){
				text += "Id: " + tLineaVenta.getProducto().getId() + " || Cantidad: " + tLineaVenta.getCantidad()+ " || Precio: " + (tLineaVenta.getCantidad() * tLineaVenta.getProducto().getPrecio()) + "\n";
			}
			break;
		case Evento.TRABAJADOR_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "El trabajador introducido no existe");
			break;
		case Evento.TRABAJADOR_NO_ACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "El trabajador introducido no esta activo");
			break;
		case Evento.LINEA_VACIA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "No has creado ningun pedido");
			break;
		default:
			TVenta tVenta = (TVenta) datos;
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaVenta.this), "Venta creada con Id: " + evento + " precio total: " + tVenta.getPrecioTotal());
			break;
		}
	}
}