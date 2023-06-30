package presentacion.ingrediente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoIngrediente extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelAltaIngrediente;
	private JPanel panelBajaIngrediente;
	private JPanel panelActualizarIngrediente;
	private JPanel panelListarIngredientes;
	private JPanel panelMostrarIngrediente;
	private JButton botonAltaIngrediente;
	private JButton botonBajaIngrediente;
	private JButton botonActualizarIngrediente;
	private JButton botonListarIngredientes;
	private JButton botonMostrarIngrediente;
	
	private Color azul = new Color(157,216,235);
	
	public MarcoIngrediente(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("INGREDIENTE");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarIngrediente = new JPanel();
		panelAltaIngrediente= new JPanel();
		panelBajaIngrediente = new JPanel();
		panelMostrarIngrediente = new JPanel();
		panelListarIngredientes= new JPanel();
		
		botonActualizarIngrediente = new JButton("ACTUALIZAR");
		botonAltaIngrediente = new JButton("ALTA");
		botonBajaIngrediente = new JButton("BAJA");
		botonMostrarIngrediente= new JButton("MOSTRAR");
		botonListarIngredientes = new JButton("LISTAR");
		
		
		panelActualizarIngrediente.add(botonActualizarIngrediente);
		panelAltaIngrediente.add(botonAltaIngrediente);
		panelBajaIngrediente.add(botonBajaIngrediente);
		panelMostrarIngrediente.add(botonMostrarIngrediente);
		panelListarIngredientes.add(botonListarIngredientes);
		
		panelActualizarIngrediente.setBackground(azul);
		panelAltaIngrediente.setBackground(azul);
		panelBajaIngrediente.setBackground(azul);
		panelMostrarIngrediente.setBackground(azul);
		panelListarIngredientes.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarIngrediente);
		paneles.add(panelAltaIngrediente);
		paneles.add(panelBajaIngrediente);
		paneles.add(panelMostrarIngrediente);
		paneles.add(panelListarIngredientes);
		
		botonActualizarIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarIngredientes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,230);
		this.pack();
		this.setVisible(true);	
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_INGREDIENTE,null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_INGREDIENTE,null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_INGREDIENTE,null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_INGREDIENTE,null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_INGREDIENTE,null);
	}

	@Override
	public void update(Integer evento, Object datos) {
		
	}

}
