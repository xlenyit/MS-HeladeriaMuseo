package presentacion.proveedor;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoProveedor extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel panelActualizarProveedor;
	private JPanel panelAltaProveedor;
	private JPanel panelBajaProveedor;
	private JPanel panelListarProveedores;
	private JPanel panelMostrarProveedor;
	private JButton botonActualizarProveedor;
	private JButton botonAltaProveedor;
	private JButton botonBajaProveedor;
	private JButton botonListarProveedores;
	private JButton botonMostrarProveedor;
	
	private Color azul = new Color(157,216,235);

	
	public MarcoProveedor(){
		initGUI();
	}
	private void initGUI() {
		JPanel contentPane = new JPanel();
		setTitle("PROVEEDOR");
		this.setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(600,100));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JPanel espacio = new JPanel();
		espacio.setBackground(azul);
		contentPane.add(espacio);
		contentPane.setBackground(azul);
		
		panelActualizarProveedor = new JPanel();
		panelAltaProveedor = new JPanel();
		panelBajaProveedor = new JPanel();
		panelMostrarProveedor = new JPanel();
		panelListarProveedores = new JPanel();
		
		botonActualizarProveedor = new JButton("ACTUALIZAR");
		botonAltaProveedor= new JButton("ALTA");
		botonBajaProveedor = new JButton("BAJA");
		botonMostrarProveedor = new JButton("MOSTRAR");
		botonListarProveedores = new JButton("LISTAR");
		
		
		panelActualizarProveedor.add(botonActualizarProveedor);
		panelAltaProveedor.add(botonAltaProveedor);
		panelBajaProveedor.add(botonBajaProveedor);
		panelMostrarProveedor.add(botonMostrarProveedor);
		panelListarProveedores.add(botonListarProveedores);
		
		panelActualizarProveedor.setBackground(azul);
		panelAltaProveedor.setBackground(azul);
		panelBajaProveedor.setBackground(azul);
		panelMostrarProveedor.setBackground(azul);
		panelListarProveedores.setBackground(azul);
		
		JPanel paneles = new JPanel();
		paneles.setLayout(new BoxLayout(paneles, BoxLayout.X_AXIS));
		
		paneles.add(panelActualizarProveedor);
		paneles.add(panelAltaProveedor);
		paneles.add(panelBajaProveedor);
		paneles.add(panelMostrarProveedor);
		paneles.add(panelListarProveedores);
		
		botonActualizarProveedor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openActualizar();
			}
		});
		botonAltaProveedor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openAlta();
			}
		});
		botonBajaProveedor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBaja();
			}
		});
		botonMostrarProveedor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMostrar();
			}
		});
		botonListarProveedores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openListar();
			}
		});
		
		contentPane.add(paneles);
		this.setResizable(false);
		this.setLocation(920,360);
		this.pack();
		this.setVisible(true);
	}
	
	private void openActualizar(){
		Controlador.getInstance().update(Evento.MOSTRAR_ACTUALIZAR_PROVEEDOR, null);
	}
	private void openAlta(){
		Controlador.getInstance().update(Evento.MOSTRAR_ALTA_PROVEEDOR, null);
	}
	private void openBaja(){
		Controlador.getInstance().update(Evento.MOSTRAR_BAJA_PROVEEDOR, null);
	}
	private void openMostrar(){
		Controlador.getInstance().update(Evento.MOSTRAR_MOSTRAR_PROVEEDOR, null);
	}
	private void openListar(){
		Controlador.getInstance().update(Evento.MOSTRAR_LISTAR_PROVEEDOR, null);
	}
	@Override
	public void update(Integer evento, Object datos) {
		
	}
	
}