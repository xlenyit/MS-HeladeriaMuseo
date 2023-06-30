package presentacion.producto.altaProducto;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.producto.TBatido;
import negocio.producto.THelado;
import negocio.producto.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
public class MarcoAltaProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreProducto;
	private JLabel labelSaborProducto;
	private JLabel labelTipoProducto;
	private JLabel labelIdProveedor;
	private JLabel labelPrecio;
	private JLabel labelStock;
	private JButton botonAltaProducto;
	private JLabel labelExtra;
	private JTextField textFieldExtra;
	private JTextField textFieldSaborProducto;
	private JTextField textFieldNombreProducto;
	private JTextField textFieldIdProveedor;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	private JButton botonCancelarAltaProducto;
	private JComboBox<String> typeCB;
	public MarcoAltaProducto(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Producto");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de un nuevo Producto");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelNombreProducto = new JLabel("Nombre: ");
		textFieldNombreProducto = new JTextField();
		textFieldNombreProducto.setPreferredSize(new Dimension(100, 20));
		
		labelSaborProducto = new JLabel("Sabor: ");
		textFieldSaborProducto = new JTextField();
		textFieldSaborProducto.setPreferredSize(new Dimension(100, 20));
		
		labelTipoProducto = new JLabel("Tipo: ");
		//---------------------------------------
		String[] tipos = {"Batido", "Helado"};
		typeCB = new JComboBox<String>(tipos);
		typeCB.setPreferredSize(new Dimension(100, 20));
		typeCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(typeCB.getSelectedItem() == "Helado") labelExtra.setText("Envase: ");
				else labelExtra.setText("Tamanio: ");
				
			}
		});
		//textFieldTipoProducto = new JTextField();
		//textFieldTipoProducto.setPreferredSize(new Dimension(100, 20));
		
		labelIdProveedor= new JLabel("Id del Proveedor: ");
		textFieldIdProveedor= new JTextField();
		textFieldIdProveedor.setPreferredSize(new Dimension(30, 20));
		
		labelExtra = new JLabel("Tamanio: ");
		textFieldExtra = new JTextField();
		textFieldExtra.setPreferredSize(new Dimension(100, 20));
	
		labelPrecio= new JLabel("Precio: ");
		textFieldPrecio= new JTextField();
		textFieldPrecio.setPreferredSize(new Dimension(30, 20));

		labelStock= new JLabel("Stock: ");
		textFieldStock= new JTextField();
		textFieldStock.setPreferredSize(new Dimension(30, 20));

		selectors.add(labelNombreProducto);
		selectors.add(textFieldNombreProducto);
		selectors.add(labelSaborProducto);
		selectors.add(textFieldSaborProducto);
		selectors.add(labelTipoProducto);
		selectors.add(typeCB);
//		selectors.add(textFieldTipoProducto);
		selectors.add(labelExtra);
		selectors.add(textFieldExtra);
		selectors.add(labelIdProveedor);
		selectors.add(textFieldIdProveedor);
		selectors.add(labelPrecio);
		selectors.add(textFieldPrecio);
		selectors.add(labelStock);
		selectors.add(textFieldStock);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaProducto = new JButton("Aceptar");
		botonAltaProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeCB.getSelectedItem().toString() == "Helado"){
					TProducto tHelado = new THelado(-1,textFieldExtra.getText(),textFieldNombreProducto.getText(), textFieldSaborProducto.getText(), true,Integer.parseInt(textFieldIdProveedor.getText()), Float.parseFloat(textFieldPrecio.getText()), Integer.parseInt(textFieldStock.getText()));
					Controlador.getInstance().update(Evento.ALTA_PRODUCTO, tHelado);
				} else {
					TProducto tBatido = new TBatido(-1,textFieldExtra.getText(),textFieldNombreProducto.getText(), textFieldSaborProducto.getText(), true,Integer.parseInt(textFieldIdProveedor.getText()),  Float.parseFloat(textFieldPrecio.getText()), Integer.parseInt(textFieldStock.getText()));
					Controlador.getInstance().update(Evento.ALTA_PRODUCTO, tBatido);
				}
				MarcoAltaProducto.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaProducto);
		
		botonCancelarAltaProducto = new JButton("Cancelar");
		botonCancelarAltaProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaProducto.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarAltaProducto);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProducto.this), "Error en el alta del Producto");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProducto.this), "El nombre ya existe en la base de datos");
			break;
		case Evento.PROVEEDOR_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProducto.this), "El proveedor introducido no existe");
			break;
		case Evento.PROVEEDOR_NO_ACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProducto.this), "El proveedor introducido no esta activo");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProducto.this), "Producto con ID: " + evento + " creado correctamente");
			break;
		}
	}
}