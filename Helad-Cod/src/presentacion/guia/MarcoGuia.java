package presentacion.guia;

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
public class MarcoGuia extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarGuia;
	private JPanel panelAltaGuia;
	private JPanel panelBajaGuia;
	private JPanel panelListarGuia;
	private JPanel panelMostrarGuia;
	private JPanel panelVincularGuia;
	private JButton botonVincularGuia;
	private JButton botonActualizarGuia;
	private JButton botonAltaGuia;
	private JButton botonBajaGuia;
	private JButton botonListarGuia;
	private JButton botonMostrarGuia;
	
	private Color azul = new Color(157,216,235);

	
	public MarcoGuia(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("GUIA");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarGuia = new JPanel();
		panelAltaGuia = new JPanel();
		panelBajaGuia = new JPanel();
		panelMostrarGuia = new JPanel();
		panelListarGuia = new JPanel();
		panelVincularGuia = new JPanel();
		
		botonActualizarGuia = new JButton("MODIFICAR");
		botonAltaGuia = new JButton("ALTA");
		botonBajaGuia = new JButton("BAJA");
		botonMostrarGuia = new JButton("MOSTRAR");
		botonListarGuia= new JButton("LISTAR");
		botonVincularGuia= new JButton("VINCULAR");
		
		panelActualizarGuia.add(botonActualizarGuia);
		panelAltaGuia.add(botonAltaGuia);
		panelBajaGuia.add(botonBajaGuia);
		panelMostrarGuia.add(botonMostrarGuia);
		panelListarGuia.add(botonListarGuia);
		panelVincularGuia.add(botonVincularGuia);
		
		panelActualizarGuia.setBackground(azul);
		panelAltaGuia.setBackground(azul);
		panelBajaGuia.setBackground(azul);
		panelMostrarGuia.setBackground(azul);
		panelListarGuia.setBackground(azul);
		panelVincularGuia.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarGuia);
		paneles.add(panelAltaGuia);
		paneles.add(panelBajaGuia);
		paneles.add(panelMostrarGuia);
		paneles.add(panelListarGuia);
		paneles.add(panelVincularGuia);
		
		
		botonActualizarGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		botonVincularGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openVincular();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,365);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_GUIA, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_GUIA, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_GUIA, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_GUIA, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_GUIA, null);
	}
	private void openVincular(){
		Controlador.getInstance().update(Evento.MOSTRAR_VINCULAR_GUIA, null);
	}

	@Override
	public void update(Integer evento, Object datos) {
	}
}