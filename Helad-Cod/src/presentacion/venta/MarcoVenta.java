package presentacion.venta;

import javax.swing.JPanel;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MarcoVenta extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelAltaVenta;
	private JPanel panelDevolucionVenta;
	private JPanel panelMostrarVenta;
	private JButton botonAltaVenta;
	private JButton botonDevolucionVenta;
	private JButton botonMostrarVenta;
	private Color azul = new Color(157,216,235);
	
	public MarcoVenta(){
		initGUI();
	}

	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("VENTA");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelAltaVenta = new JPanel();
		panelDevolucionVenta = new JPanel();
		panelMostrarVenta = new JPanel();
		
		botonAltaVenta = new JButton("ALTA");
		botonDevolucionVenta = new JButton("DEVOLUCION");
		botonMostrarVenta = new JButton("MOSTRAR");
		
		
		panelAltaVenta.add(botonAltaVenta);
		panelDevolucionVenta.add(botonDevolucionVenta);
		panelMostrarVenta.add(botonMostrarVenta);
		
		panelAltaVenta.setBackground(azul);
		panelDevolucionVenta.setBackground(azul);
		panelMostrarVenta.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelAltaVenta);
		paneles.add(panelDevolucionVenta);
		paneles.add(panelMostrarVenta);
		
		botonAltaVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonDevolucionVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarVenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,100);
		this.pack();
		this.setVisible(true);
	}
	
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_VENTA, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_VENTA, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_VENTA, null);
	}

	@Override
	public void update(Integer evento, Object datos) {
		
	}
}