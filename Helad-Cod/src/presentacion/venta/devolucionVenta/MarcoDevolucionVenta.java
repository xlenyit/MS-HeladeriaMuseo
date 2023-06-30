package presentacion.venta.devolucionVenta;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.venta.TVenta;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class MarcoDevolucionVenta extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDProducto;
	private JTextField textFieldIDProducto;
	private JButton botonDevolver;
	private JButton botonCancelar;
	private TVenta tVenta;
	public MarcoDevolucionVenta(Integer evento, Object datos){
		
		update(evento, datos);
		
	}
	public MarcoDevolucionVenta(){
		initGUI();
		tVenta = new TVenta();
	}
	
	private void initGUI() {
		this.setTitle("Devolucion Venta");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el ID para la Devolucion de la Venta");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		
		labelIDProducto = new JLabel("Id: ");
		textFieldIDProducto = new JTextField();
		textFieldIDProducto.setPreferredSize(new Dimension(100, 20));
		
		selectors.add(labelIDProducto);
		selectors.add(textFieldIDProducto);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonDevolver = new JButton("Aceptar");
		botonDevolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().update(Evento.BAJA_VENTA, Integer.parseInt(textFieldIDProducto.getText()));
				MarcoDevolucionVenta.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonDevolver);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoDevolucionVenta.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelar);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		Integer id = (Integer) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoDevolucionVenta.this), "Error en la Devolucion de la Venta");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoDevolucionVenta.this), "El ID introducido no existe");
		
		break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoDevolucionVenta.this), "La venta introducida ya estaba dada de baja");
		break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoDevolucionVenta.this), "Venta con id: " + id + " dada de baja");
			break;
		}
		
	}
}