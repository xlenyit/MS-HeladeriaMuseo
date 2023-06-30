package presentacion.jPrincipal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.factoriaGUI.FactoriaGUI;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGeneral extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelVenta;
	private JButton botonVenta;
	private JPanel panelProducto;
	private JButton botonProducto;
	private JPanel panelProveedor;
	private JButton botonProveedor;
	private JPanel panelTrabajador;
	private JButton botonTrabajador;
	private JPanel panelSeccion;
	private JButton botonSeccion;
	private JPanel panelIngrediente;
	private JButton botonIngrediente;
	private Color azul = new Color(157,216,235);
	
	public VistaGeneral(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Heladeria");
		JPanel mainPanel = new JPanel(new FlowLayout());
		this.setContentPane(mainPanel);
		// BUTTONSPANEL:
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.setPreferredSize(new Dimension(300,600));
		this.getContentPane().setBackground(azul);
		
		botonVenta = new JButton("VENTA");
		panelVenta = new JPanel();
		panelVenta.setBackground(azul);
		panelVenta.add(botonVenta);
		botonVenta.setPreferredSize((new Dimension(150,40)));
		botonVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openVenta();
			}
		});
		
		buttonsPanel.add(panelVenta);
		
		botonProducto = new JButton("PRODUCTO");
		panelProducto = new JPanel();
		panelProducto.setBackground(azul);
		panelProducto.add(botonProducto);
		botonProducto.setPreferredSize((new Dimension(150,40)));
		botonProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Controlador.getInstance().update(evento, datos);
				openProducto();
			}
		});
		
		buttonsPanel.add(panelProducto);
		
		botonIngrediente = new JButton("INGREDIENTE");
		panelIngrediente = new JPanel();
		panelIngrediente.setBackground(azul);
		panelIngrediente.add(botonIngrediente);
		botonIngrediente.setPreferredSize((new Dimension(150,40)));
		botonIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Controlador.getInstance().update(evento, datos);
				openIngrediente();
			}
		});
		
		buttonsPanel.add(panelIngrediente);
		
		botonProveedor = new JButton("PROVEEDOR");
		panelProveedor = new JPanel();
		panelProveedor.setBackground(azul);
		panelProveedor.add(botonProveedor);
		botonProveedor.setPreferredSize((new Dimension(150,40)));
		botonProveedor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openProveedor();
			}
		});
		
		buttonsPanel.add(panelProveedor);
		
		botonTrabajador = new JButton("TRABAJADOR");
		panelTrabajador = new JPanel();
		panelTrabajador.setBackground(azul);
		panelTrabajador.add(botonTrabajador);
		botonTrabajador.setPreferredSize((new Dimension(150,40)));
		botonTrabajador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openTrabajador();
			}
		});
		
		buttonsPanel.add(panelTrabajador);
		
		botonSeccion = new JButton("SECCION");
		panelSeccion = new JPanel();
		panelSeccion.setBackground(azul);
		panelSeccion.add(botonSeccion);
		botonSeccion.setPreferredSize((new Dimension(150,40)));
		botonSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openSeccion();
			}
		});
		buttonsPanel.add(panelSeccion);
		
		
		mainPanel.add(buttonsPanel);
		// LOGOSPANEL:
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
		logoPanel.setBackground(Color.white);
		
		
		JLabel heladeria = new JLabel("HELADERIA");
		heladeria.setFont(new Font("Serif", Font.PLAIN, 40));
		JPanel texto = new JPanel();
		texto.setLayout(new BoxLayout(texto, BoxLayout.Y_AXIS));
		texto.add(heladeria);
		texto.setBackground(Color.white);
		
		JLabel deleite = new JLabel("DELEITE",SwingConstants.RIGHT);
		deleite.setFont(new Font("Serif", Font.PLAIN, 40));
	
		texto.add(deleite);
		deleite.setAlignmentX(Component.RIGHT_ALIGNMENT);
		logoPanel.add(texto);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\logo.jpg"));
		logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("resources\\logo.jpg"));
		logo.setPreferredSize(new Dimension(200,200));
		logoPanel.add(logo);

		
		
		logoPanel.setPreferredSize(new Dimension(500,600));
		
		
		mainPanel.add(logoPanel);
		this.setResizable(false);
		this.setLocation(100,100);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void openVenta(){
		Controlador.getInstance().update(Evento.VENTA, null);
	}
	private void openProducto(){
		Controlador.getInstance().update(Evento.PRODUCTO, null);
	}
	private void openIngrediente(){
		Controlador.getInstance().update(Evento.INGREDIENTE, null);
	}
	private void openProveedor(){
		Controlador.getInstance().update(Evento.PROVEEDOR, null);
	}
	private void openTrabajador(){
		Controlador.getInstance().update(Evento.TRABAJADOR, null);
	}
	private void openSeccion(){
		FactoriaGUI.getInstance().createVista(Evento.SECCION);
	}

	@Override
	public void update(Integer evento, Object datos) {
	}
}