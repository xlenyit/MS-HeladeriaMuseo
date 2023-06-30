package presentacion.producto;

import javax.swing.JPanel;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcoProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelAltaProducto;
	private JPanel panelBajaProducto;
	private JPanel panelActualizarProducto;
	private JPanel panelListarProductos;
	private JPanel panelMostrarProducto;
	private JPanel panelQuery;
	private JButton botonAltaProducto;
	private JButton botonBajaProducto;
	private JButton botonActualizarProducto;
	private JButton botonListarProductos;
	private JButton botonMostrarProducto;
	private JButton botonQuery;
	
	private Color azul = new Color(157,216,235);
	
	public MarcoProducto(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("PRODUCTO");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarProducto = new JPanel();
		panelAltaProducto= new JPanel();
		panelBajaProducto = new JPanel();
		panelMostrarProducto = new JPanel();
		panelListarProductos = new JPanel();
		panelQuery= new JPanel();
		
		botonActualizarProducto = new JButton("ACTUALIZAR");
		botonAltaProducto = new JButton("ALTA");
		botonBajaProducto = new JButton("BAJA");
		botonMostrarProducto = new JButton("MOSTRAR");
		botonListarProductos = new JButton("LISTAR");
		botonQuery = new JButton("QUERY");
		
		panelActualizarProducto.add(botonActualizarProducto);
		panelAltaProducto.add(botonAltaProducto);
		panelBajaProducto.add(botonBajaProducto);
		panelMostrarProducto.add(botonMostrarProducto);
		panelListarProductos.add(botonListarProductos);
		panelQuery.add(botonQuery);
		
		panelActualizarProducto.setBackground(azul);
		panelAltaProducto.setBackground(azul);
		panelBajaProducto.setBackground(azul);
		panelMostrarProducto.setBackground(azul);
		panelListarProductos.setBackground(azul);
		panelQuery.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarProducto);
		paneles.add(panelAltaProducto);
		paneles.add(panelBajaProducto);
		paneles.add(panelMostrarProducto);
		paneles.add(panelListarProductos);
		paneles.add(panelQuery);
		
		botonActualizarProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarProductos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		botonQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openQuery();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,230);
		this.pack();
		this.setVisible(true);	
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_PRODUCTO,null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_PRODUCTO,null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_PRODUCTO,null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_PRODUCTO,null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_PRODUCTO,null);
	}
	private void openQuery(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_PRODUCTO_MAS_VENDIDO, null);
	}


	@Override
	public void update(Integer evento, Object datos) {
		
	}
}