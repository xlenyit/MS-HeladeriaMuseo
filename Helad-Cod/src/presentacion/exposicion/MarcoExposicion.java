package presentacion.exposicion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MarcoExposicion extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarExposicion;
	private JPanel panelAltaExposicion;
	private JPanel panelBajaExposicion;
	private JPanel panelListarExposicion;
	private JPanel panelMostrarExposicion;
	private JPanel panelMostrarCosteExposicion;
	private JButton botonActualizarExposicion;
	private JButton botonAltaExposicion;
	private JButton botonBajaExposicion;
	private JButton botonListarExposicion;
	private JButton botonMostrarExposicion;
	private JButton botonMostrarCosteExposicion;
	
	private Color azul = new Color(157,216,235);

	
	public MarcoExposicion(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("EXPOSICION");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarExposicion = new JPanel();
		panelAltaExposicion = new JPanel();
		panelBajaExposicion = new JPanel();
		panelMostrarExposicion = new JPanel();
		panelListarExposicion = new JPanel();
		panelMostrarCosteExposicion = new JPanel();
		
		botonActualizarExposicion = new JButton("MODIFICAR");
		botonAltaExposicion = new JButton("ALTA");
		botonBajaExposicion = new JButton("BAJA");
		botonMostrarExposicion = new JButton("MOSTRAR");
		botonListarExposicion= new JButton("LISTAR");
		botonMostrarCosteExposicion = new JButton("COSTE");
		
		
		panelActualizarExposicion.add(botonActualizarExposicion);
		panelAltaExposicion.add(botonAltaExposicion);
		panelBajaExposicion.add(botonBajaExposicion);
		panelMostrarExposicion.add(botonMostrarExposicion);
		panelListarExposicion.add(botonListarExposicion);
		panelMostrarCosteExposicion.add(botonMostrarCosteExposicion);
		
		panelActualizarExposicion.setBackground(azul);
		panelAltaExposicion.setBackground(azul);
		panelBajaExposicion.setBackground(azul);
		panelMostrarExposicion.setBackground(azul);
		panelListarExposicion.setBackground(azul);
		panelMostrarCosteExposicion.setBackground(azul);
		
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarExposicion);
		paneles.add(panelAltaExposicion);
		paneles.add(panelBajaExposicion);
		paneles.add(panelMostrarExposicion);
		paneles.add(panelListarExposicion);
		paneles.add(panelMostrarCosteExposicion);
		
		botonActualizarExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		botonMostrarCosteExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openCoste();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,230);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_EXPOSICION, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_EXPOSICION, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_EXPOSICION, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_EXPOSICION, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_EXPOSICION, null);
	}
	private void openCoste(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_COSTE_EXPOSICION, null);
	}

	@Override
	public void update(Integer evento, Object datos) {
	}
}