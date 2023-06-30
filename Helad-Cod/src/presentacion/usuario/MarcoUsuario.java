package presentacion.usuario;

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

public class MarcoUsuario extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarUsuario;
	private JPanel panelAltaUsuario;
	private JPanel panelBajaUsuario;
	private JPanel panelListarUsuario;
	private JPanel panelMostrarUsuario;
	private JButton botonActualizarUsuario;
	private JButton botonAltaUsuario;
	private JButton botonBajaUsuario;
	private JButton botonListarUsuario;
	private JButton botonMostrarUsuario;
	
	private Color azul = new Color(157,216,235);

	
	public MarcoUsuario(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("USUARIO");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarUsuario = new JPanel();
		panelAltaUsuario = new JPanel();
		panelBajaUsuario = new JPanel();
		panelMostrarUsuario = new JPanel();
		panelListarUsuario = new JPanel();
		
		botonActualizarUsuario = new JButton("MODIFICAR");
		botonAltaUsuario = new JButton("ALTA");
		botonBajaUsuario = new JButton("BAJA");
		botonMostrarUsuario = new JButton("MOSTRAR");
		botonListarUsuario= new JButton("LISTAR");
		
		
		panelActualizarUsuario.add(botonActualizarUsuario);
		panelAltaUsuario.add(botonAltaUsuario);
		panelBajaUsuario.add(botonBajaUsuario);
		panelMostrarUsuario.add(botonMostrarUsuario);
		panelListarUsuario.add(botonListarUsuario);
		
		panelActualizarUsuario.setBackground(azul);
		panelAltaUsuario.setBackground(azul);
		panelBajaUsuario.setBackground(azul);
		panelMostrarUsuario.setBackground(azul);
		panelListarUsuario.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarUsuario);
		paneles.add(panelAltaUsuario);
		paneles.add(panelBajaUsuario);
		paneles.add(panelMostrarUsuario);
		paneles.add(panelListarUsuario);
		
		botonActualizarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,490);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_USUARIO, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_USUARIO, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_USUARIO, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_USUARIO, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_USUARIO, null);
	}

	@Override
	public void update(Integer evento, Object datos) {
	}
}
