package presentacion.actividad;

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

public class MarcoActividad extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelAltaActividad;
	private JPanel panelBajaActividad;
	private JPanel panelActualizarActividad;
	private JPanel panelListarActividades;
	private JPanel panelMostrarActividad;
	private JPanel panelVincular;
	private JButton botonAltaActividad;
	private JButton botonBajaActividad;
	private JButton botonActualizarActividad;
	private JButton botonListarActividades;
	private JButton botonMostrarActividad;
	private JButton botonVincular;
	
	private Color azul = new Color(157,216,235);
	
	public MarcoActividad(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("ACTIVIDAD");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarActividad = new JPanel();
		panelAltaActividad= new JPanel();
		panelBajaActividad = new JPanel();
		panelMostrarActividad = new JPanel();
		panelListarActividades = new JPanel();
		panelVincular= new JPanel();
		
		botonActualizarActividad = new JButton("MODIFICAR");
		botonAltaActividad = new JButton("ALTA");
		botonBajaActividad = new JButton("BAJA");
		botonMostrarActividad = new JButton("MOSTRAR");
		botonListarActividades = new JButton("LISTAR");
		botonVincular = new JButton("VINCULAR");
		
		panelActualizarActividad.add(botonActualizarActividad);
		panelAltaActividad.add(botonAltaActividad);
		panelBajaActividad.add(botonBajaActividad);
		panelMostrarActividad.add(botonMostrarActividad);
		panelListarActividades.add(botonListarActividades);
		panelVincular.add(botonVincular);
		
		panelActualizarActividad.setBackground(azul);
		panelAltaActividad.setBackground(azul);
		panelBajaActividad.setBackground(azul);
		panelMostrarActividad.setBackground(azul);
		panelListarActividades.setBackground(azul);
		panelVincular.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarActividad);
		paneles.add(panelAltaActividad);
		paneles.add(panelBajaActividad);
		paneles.add(panelMostrarActividad);
		paneles.add(panelListarActividades);
		paneles.add(panelVincular);
		
		botonActualizarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarActividades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		botonVincular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openVincular();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,620);
		this.pack();
		this.setVisible(true);	
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_ACTIVIDAD,null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_ACTIVIDAD,null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_ACTIVIDAD,null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_ACTIVIDAD,null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_ACTIVIDAD,null);
	}
	private void openVincular(){
		Controlador.getInstance().update(Evento.MOSTRAR_VINCULAR_ACTIVIDAD, null);
	}


	@Override
	public void update(Integer evento, Object datos) {
		
	}
}