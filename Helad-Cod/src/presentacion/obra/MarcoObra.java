package presentacion.obra;

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
public class MarcoObra extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarObra;
	private JPanel panelAltaObra;
	private JPanel panelBajaObra;
	private JPanel panelListarObra;
	private JPanel panelMostrarObra;
	private JPanel panelMostrarCosteObra;
	private JButton botonActualizarObra;
	private JButton botonAltaObra;
	private JButton botonBajaObra;
	private JButton botonListarObra;
	private JButton botonMostrarObra;
	private JButton botonMostrarCosteObra;
	
	private Color azul = new Color(157,216,235);

	
	public MarcoObra(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("OBRA");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarObra = new JPanel();
		panelAltaObra = new JPanel();
		panelBajaObra = new JPanel();
		panelMostrarObra = new JPanel();
		panelListarObra = new JPanel();
		panelMostrarCosteObra = new JPanel();
		
		botonActualizarObra = new JButton("MODIFICAR");
		botonAltaObra = new JButton("ALTA");
		botonBajaObra = new JButton("BAJA");
		botonMostrarObra = new JButton("MOSTRAR");
		botonListarObra= new JButton("LISTAR");
		botonMostrarCosteObra= new JButton("COSTE");
		
		
		panelActualizarObra.add(botonActualizarObra);
		panelAltaObra.add(botonAltaObra);
		panelBajaObra.add(botonBajaObra);
		panelMostrarObra.add(botonMostrarObra);
		panelListarObra.add(botonListarObra);
		panelMostrarCosteObra.add(botonMostrarCosteObra);
		
		panelActualizarObra.setBackground(azul);
		panelAltaObra.setBackground(azul);
		panelBajaObra.setBackground(azul);
		panelMostrarObra.setBackground(azul);
		panelListarObra.setBackground(azul);
		panelMostrarCosteObra.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarObra);
		paneles.add(panelAltaObra);
		paneles.add(panelBajaObra);
		paneles.add(panelMostrarObra);
		paneles.add(panelListarObra);
		paneles.add(panelMostrarCosteObra);
		
		botonActualizarObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		botonMostrarCosteObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrarCoste();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,100);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_OBRA, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_OBRA, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_OBRA, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_OBRA, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_OBRA, null);
	}
	private void openMostrarCoste(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_COSTE_OBRA, null);
	}

	@Override
	public void update(Integer evento, Object datos) {
	}
}