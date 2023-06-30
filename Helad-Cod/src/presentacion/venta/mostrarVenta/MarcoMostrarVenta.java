package presentacion.venta.mostrarVenta;



import negocio.venta.TLineaVenta;
import negocio.venta.TVenta;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.venta.mostrarVenta.MarcoMostrarVenta;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MarcoMostrarVenta extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelID;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	private String text = "";
	public MarcoMostrarVenta(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Mostrar Venta");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Venta que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		labelID = new JLabel("Identificador: ");
		textFieldID = new JTextField();
		textFieldID.setPreferredSize(new Dimension(100,20));
		
		
		JPanel selectors = new JPanel();
		selectors.add(labelID);
		selectors.add(textFieldID);
		emergent.add(selectors);
		
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setPreferredSize(new Dimension(700, 200));
		
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scrollDatos);
		emergent.add(scrollPanel);
		
		JPanel buttonsPanel = new JPanel();
		
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().update(Evento.MOSTRAR_VENTA, Integer.parseInt(textFieldID.getText()));
				MarcoMostrarVenta.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrar);
		
		botonOcultar = new JButton("Cancelar");
		botonOcultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarVenta.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonOcultar);
		
		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarVenta.this), "El ID no puede ser negativo");
		}else if(datos == null){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarVenta.this), "El ID no existe en la base de datos");
		}else{
			List<Object> objectL = (List<Object>) datos;
			if(!objectL.isEmpty()){
				TVenta tVenta = (TVenta) objectL.get(0);
				List<TLineaVenta> tLineaVentas = (List<TLineaVenta>) tVenta.getProductos();
				for (TLineaVenta t: tLineaVentas){
					text += "- ID del producto: " + t.getProducto().getId() + " || Cantidad: " + t.getCantidad() + " || Precio: " + t.getProducto().getPrecio() + "\n";
				}
				text += "-- ID de la venta: " + tVenta.getId() + " || Estado: " + (tVenta.getActivo()? "VENDIDO":"DEVUELTO") + "\n"; 
				text += "-- Precio total: " + tVenta.getPrecioTotal() + " || Venta realizada por el trabajador con ID: " + tVenta.getTrabajador().getId();
				text += " Hora y fecha de la venta: " + tVenta.getFecha(); 
				contenedorDatos.setText(text);
			}
		}
	}
}