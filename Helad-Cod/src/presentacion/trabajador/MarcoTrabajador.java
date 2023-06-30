package presentacion.trabajador;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MarcoTrabajador extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarTrabajador;
	private JPanel panelAltaTrabajador;
	private JPanel panelBajaTrabajador;
	private JPanel panelListarTrabajador;
	private JPanel panelMostrarTrabajador;
	private JPanel panelQuery;
	private JButton botonActualizarTrabajador;
	private JButton botonAltaTrabajador;
	private JButton botonBajaTrabajador;
	private JButton botonListarTrabajadores;
	private JButton botonMostrarTrabajador;
	private JButton botonQuery;
	
	private Color azul = new Color(157,216,235);
	
	public MarcoTrabajador(){
		initGUI();
	}
	
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("TRABAJADOR");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarTrabajador = new JPanel();
		panelAltaTrabajador = new JPanel();
		panelBajaTrabajador = new JPanel();
		panelMostrarTrabajador = new JPanel();
		panelListarTrabajador = new JPanel(); 
		panelQuery= new JPanel(); 
		
		botonActualizarTrabajador = new JButton("ACTUALIZAR");
		botonAltaTrabajador = new JButton("ALTA");
		botonBajaTrabajador = new JButton("BAJA");
		botonMostrarTrabajador = new JButton("MOSTRAR");
		botonListarTrabajadores = new JButton("LISTAR");
		botonQuery = new JButton("QUERY");
		
		
		panelActualizarTrabajador.add(botonActualizarTrabajador);
		panelAltaTrabajador.add(botonAltaTrabajador);
		panelBajaTrabajador.add(botonBajaTrabajador);
		panelMostrarTrabajador.add(botonMostrarTrabajador);
		panelListarTrabajador.add(botonListarTrabajadores);
		panelQuery.add(botonQuery);
		
		panelActualizarTrabajador.setBackground(azul);
		panelAltaTrabajador.setBackground(azul);
		panelBajaTrabajador.setBackground(azul);
		panelMostrarTrabajador.setBackground(azul);
		panelListarTrabajador.setBackground(azul);
		panelQuery.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarTrabajador);
		paneles.add(panelAltaTrabajador);
		paneles.add(panelBajaTrabajador);
		paneles.add(panelMostrarTrabajador);
		paneles.add(panelListarTrabajador);
		paneles.add(panelQuery);
		
		botonActualizarTrabajador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaTrabajador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaTrabajador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarTrabajador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarTrabajadores.addActionListener(new ActionListener() {
			
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
		this.setLocation(920,490);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_TRABAJADOR, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_TRABAJADOR, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_TRABAJADOR, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_TRABAJADOR, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_TRABAJADOR, null);
	}
	private void openQuery(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_TRABAJADOR_CON_MAS_VENTAS, null);
	}


	@Override
	public void update(Integer evento, Object datos) {
		
	}
}