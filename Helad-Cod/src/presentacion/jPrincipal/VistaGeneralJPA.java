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

public class VistaGeneralJPA extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelObra;
	private JButton botonObra;
	private JPanel panelExposicion;
	private JButton botonExposicion;
	private JPanel panelGuia;
	private JButton botonGuia;
	private JPanel panelUsuario;
	private JButton botonUsuario;
	private JPanel panelActividad;
	private JButton botonActividad;
	
	private Color azul = new Color(157,216,235);
	
	public VistaGeneralJPA(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("MuseoJPA");
		JPanel mainPanel = new JPanel(new FlowLayout());
		this.setContentPane(mainPanel);
		// BUTTONSPANEL:
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.setPreferredSize(new Dimension(300,600));
		this.getContentPane().setBackground(azul);
		
		botonObra = new JButton("OBRA");
		panelObra = new JPanel();
		panelObra.setBackground(azul);
		panelObra.add(botonObra);
		botonObra.setPreferredSize((new Dimension(150,40)));
		botonObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openObra();
			}
		});
		
		buttonsPanel.add(panelObra);
		
		botonExposicion = new JButton("EXPOSICION");
		panelExposicion = new JPanel();
		panelExposicion.setBackground(azul);
		panelExposicion.add(botonExposicion);
		botonExposicion.setPreferredSize((new Dimension(150,40)));
		botonExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openExposicion();
			}
		});
		
		buttonsPanel.add(panelExposicion);
		
		
		botonGuia = new JButton("GUIA");
		panelGuia = new JPanel();
		panelGuia.setBackground(azul);
		panelGuia.add(botonGuia);
		botonGuia.setPreferredSize((new Dimension(150,40)));
		botonGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openGuia();
			}
		});
		
		buttonsPanel.add(panelGuia);
		
		botonUsuario = new JButton("USUARIO");
		panelUsuario = new JPanel();
		panelUsuario.setBackground(azul);
		panelUsuario.add(botonUsuario);
		botonUsuario.setPreferredSize((new Dimension(150,40)));
		botonUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openUsuario();
			}
		});
		
		buttonsPanel.add(panelUsuario);
		
		botonActividad = new JButton("ACTIVIDAD");
		panelActividad = new JPanel();
		panelActividad.setBackground(azul);
		panelActividad.add(botonActividad);
		botonActividad.setPreferredSize((new Dimension(150,40)));
		botonActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActividad();
			}
		});
		buttonsPanel.add(panelActividad);
		
		
		mainPanel.add(buttonsPanel);
		// LOGOSPANEL:
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
		logoPanel.setBackground(Color.white);
		
		
		JLabel heladeria = new JLabel("MUSEO");
		heladeria.setFont(new Font("Serif", Font.PLAIN, 40));
		JPanel texto = new JPanel();
		texto.setLayout(new BoxLayout(texto, BoxLayout.Y_AXIS));
		texto.add(heladeria);
		texto.setBackground(Color.white);
		
		JLabel deleite = new JLabel("DELEITE JPA",SwingConstants.RIGHT);
		deleite.setFont(new Font("Serif", Font.PLAIN, 40));
	
		texto.add(deleite);
		deleite.setAlignmentX(Component.RIGHT_ALIGNMENT);
		logoPanel.add(texto);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\logojpa.jpg"));
		logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("resources\\logoJPA.jpg"));
		logo.setPreferredSize(new Dimension(200,200));
		logoPanel.add(logo);

		
		
		logoPanel.setPreferredSize(new Dimension(500,600));
		
		
		mainPanel.add(logoPanel);
		this.setResizable(false);
		this.setLocation(100,100);
		this.pack();
		this.setVisible(true);
	}
	
	private void openObra(){
		Controlador.getInstance().update(Evento.OBRA, null);
	}
	private void openExposicion(){
		Controlador.getInstance().update(Evento.EXPOSICION, null);
	}
	private void openGuia(){
		Controlador.getInstance().update(Evento.GUIA, null);
	}
	private void openUsuario(){
		Controlador.getInstance().update(Evento.USUARIO, null);
	}
	private void openActividad(){
		Controlador.getInstance().update(Evento.ACTIVIDAD, null);
	}


	@Override
	public void update(Integer evento, Object datos) {
	}
}