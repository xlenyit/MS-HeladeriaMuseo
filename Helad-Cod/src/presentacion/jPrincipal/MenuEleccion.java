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

public class MenuEleccion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelSeleccion;
	private JButton botonDAO;
	private JButton botonJPA;
	private Color azul = new Color(157,216,235);
	
	public MenuEleccion(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Bienvenido");
		JPanel panelSeleccion = new JPanel(new FlowLayout());
		this.setContentPane(panelSeleccion);
		this.getContentPane().setBackground(azul);
		panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.X_AXIS));
		panelSeleccion.setPreferredSize(new Dimension(300,600));
		
		botonDAO = new JButton("DAO");
		botonJPA = new JButton("JPA");
	
		panelSeleccion.add(botonDAO);
		panelSeleccion.add(botonJPA);
		botonDAO.setPreferredSize((new Dimension(150,40)));
		botonJPA.setPreferredSize((new Dimension(150,40)));
		
		botonDAO.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDAO();
			}
		});
		botonJPA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openJPA();
			}
		});
	
		this.setResizable(false);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\logo.jpg"));
	}
	
	private void openDAO(){
		Controlador.getInstance().update(Evento.INICIO_PROGRAMA, null);
	}
	private void openJPA(){
		Controlador.getInstance().update(Evento.INICIO_PROGRAMA_JPA, null);
	}
	
	@Override
	public void update(Integer evento, Object datos) {
	}
}