package presentacion.seccion;

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
public class MarcoSeccion extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarSeccion;
	private JPanel panelAltaSeccion;
	private JPanel panelBajaSeccion;
	private JPanel panelListarSecciones;
	private JPanel panelMostrarSeccion;
	private JButton botonActualizarSeccion;
	private JButton botonAltaSeccion;
	private JButton botonBajaSeccion;
	private JButton botonListarSecciones;
	private JButton botonMostrarSeccion;
	
	private Color azul = new Color(157,216,235);

	
	public MarcoSeccion(){
		initGUI();
	}
	
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("SECCION");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarSeccion = new JPanel();
		panelAltaSeccion = new JPanel();
		panelBajaSeccion = new JPanel();
		panelMostrarSeccion = new JPanel();
		panelListarSecciones = new JPanel();
		
		botonActualizarSeccion = new JButton("ACTUALIZAR");
		botonAltaSeccion = new JButton("ALTA");
		botonBajaSeccion = new JButton("BAJA");
		botonMostrarSeccion = new JButton("MOSTRAR");
		botonListarSecciones= new JButton("LISTAR");
		
		
		panelActualizarSeccion.add(botonActualizarSeccion);
		panelAltaSeccion.add(botonAltaSeccion);
		panelBajaSeccion.add(botonBajaSeccion);
		panelMostrarSeccion.add(botonMostrarSeccion);
		panelListarSecciones.add(botonListarSecciones);
		
		panelActualizarSeccion.setBackground(azul);
		panelAltaSeccion.setBackground(azul);
		panelBajaSeccion.setBackground(azul);
		panelMostrarSeccion.setBackground(azul);
		panelListarSecciones.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarSeccion);
		paneles.add(panelAltaSeccion);
		paneles.add(panelBajaSeccion);
		paneles.add(panelMostrarSeccion);
		paneles.add(panelListarSecciones);
		
		botonActualizarSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarSecciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,620);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_SECCION, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_SECCION, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_SECCION, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_SECCION, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_SECCION, null);
	}

	@Override
	public void update(Integer evento, Object datos) {
	}
}