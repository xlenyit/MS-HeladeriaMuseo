package presentacion.proveedor.bajaProveedor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MarcoBajaProveedor extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDProveedor;
	private JButton botonBaja;
	private JButton botonCancelar;
	private JTextField textfieldIDProveedor;
	
	public MarcoBajaProveedor(Integer evento, Object datos){
		update(evento, datos);
		
	}
	
	public MarcoBajaProveedor(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Proveedor");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Proveedor al que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDProveedor = new JLabel("Id: ");
		textfieldIDProveedor = new JTextField();
		textfieldIDProveedor.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIDProveedor);
		selectors.add(textfieldIDProveedor);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonBaja = new JButton("Aceptar");
		botonBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().update(Evento.BAJA_PROVEEDOR, Integer.parseInt(textfieldIDProveedor.getText()));
				MarcoBajaProveedor.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonBaja);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				estado = 0;
				MarcoBajaProveedor.this.setVisible(false);
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
		int idT = (Integer) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProveedor.this), "Error en la Baja del Proveedor");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProveedor.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProveedor.this), "El Proveedor introducida ya estaba dado de baja");
			break;
		case Evento.PROVEEDOR_CON_PRODUCTO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProveedor.this), "El Proveedor introducido contiene productos");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProveedor.this), "Proveedor con ID: " + idT + " dado de baja");	
			break;
		}
		
	}
}