package presentacion.producto.actualizarProducto;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.producto.TBatido;
import negocio.producto.THelado;
import negocio.producto.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
public class MarcoActualizarProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDProducto;
	private JLabel labelNombreProducto;
	private JLabel labelSaborProducto;
	private JLabel labelTipoProducto;
	private JLabel labelIdProveedor;
	private JLabel labelExtra;
	private JLabel labelPrecio;
	private JLabel labelStock;
	private JTextField textFieldExtra;
	private JTextField textFieldIDProducto;
	private JTextField textFieldNombreProducto;
	private JTextField textFieldSaborProducto;
	private JTextField textFieldIdProveedor;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	private JButton botonActualizar;
	private JButton botonCancelarActualizacion;
	private JComboBox<String> typeCB;
	public MarcoActualizarProducto(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Actualizar Producto");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Actualizar de un Producto");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDProducto = new JLabel("Id: ");
		textFieldIDProducto = new JTextField();
		textFieldIDProducto.setPreferredSize(new Dimension(40, 20));

		labelNombreProducto = new JLabel("Nombre: ");
		textFieldNombreProducto = new JTextField();
		textFieldNombreProducto.setPreferredSize(new Dimension(100, 20));
		
		labelSaborProducto = new JLabel("Sabor: ");
		textFieldSaborProducto = new JTextField();
		textFieldSaborProducto.setPreferredSize(new Dimension(100, 20));
		
		labelTipoProducto = new JLabel("Tipo: ");
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
//		textFieldTipoProducto = new JTextField();
//		textFieldTipoProducto.setPreferredSize(new Dimension(100, 20));

		labelExtra = new JLabel("Tamanio: ");
		textFieldExtra = new JTextField();
		textFieldExtra.setPreferredSize(new Dimension(100, 20));
		
		labelIdProveedor= new JLabel("Id del Proveedor: ");
		textFieldIdProveedor= new JTextField();
		textFieldIdProveedor.setPreferredSize(new Dimension(30, 20));
				
		labelPrecio= new JLabel("Precio: ");
		textFieldPrecio= new JTextField();
		textFieldPrecio.setPreferredSize(new Dimension(30, 20));

		labelStock= new JLabel("Stock: ");
		textFieldStock= new JTextField();
		textFieldStock.setPreferredSize(new Dimension(30, 20));
		
		selectors.add(labelIDProducto);
		selectors.add(textFieldIDProducto);
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
		
		botonActualizar = new JButton("Aceptar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeCB.getSelectedItem().toString() == "Helado"){
					TProducto tHelado = new THelado(Integer.parseInt(textFieldIDProducto.getText()),textFieldExtra.getText(),textFieldNombreProducto.getText(), textFieldSaborProducto.getText(), true,Integer.parseInt(textFieldIdProveedor.getText()), Float.parseFloat(textFieldPrecio.getText()), Integer.parseInt(textFieldStock.getText()));
					Controlador.getInstance().update(Evento.ACTUALIZAR_PRODUCTO, tHelado);
				} else {
					TProducto tBatido = new TBatido(Integer.parseInt(textFieldIDProducto.getText()),textFieldExtra.getText(),textFieldNombreProducto.getText(), textFieldSaborProducto.getText(), true,Integer.parseInt(textFieldIdProveedor.getText()),  Float.parseFloat(textFieldPrecio.getText()), Integer.parseInt(textFieldStock.getText()));
					Controlador.getInstance().update(Evento.ACTUALIZAR_PRODUCTO, tBatido);
				}
				MarcoActualizarProducto.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonActualizar);
		
		botonCancelarActualizacion = new JButton("Cancelar");
		botonCancelarActualizacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoActualizarProducto.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarActualizacion);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		TProducto tProducto = (TProducto) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "Error en la actualizacion del Producto");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "El id no existe en la base de datos");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "El nombre ya existe en la base de datos");
			break;
		case Evento.PROVEEDOR_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "El proveedor introducido no existe");
			break;
		case Evento.PROVEEDOR_NO_ACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "El proveedor introducido no esta activo");
			break;
		case Evento.TIPO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "El tipo no coincide con el de la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProducto.this), "Producto con ID: " + tProducto.getId() + " actualizado correctamente");
			break;
		}
	}
}