package presentacion.guia.bajaGuia;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoBajaGuia extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JButton botonBaja;
	private JLabel labelIDGuia;
	private JTextField textFieldGuia;
	private JButton botonCancelBaja;
	private int id = -1;
	
	public MarcoBajaGuia(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Guia");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Guia al que dar de Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDGuia = new JLabel("Id: ");
		textFieldGuia = new JTextField();
		textFieldGuia.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIDGuia);
		selectors.add(textFieldGuia);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonBaja = new JButton("Aceptar");
		botonBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldGuia.getText());
				Controlador.getInstance().update(Evento.BAJA_GUIA, id);
				MarcoBajaGuia.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonBaja);
		
		botonCancelBaja = new JButton("Cancelar");
		botonCancelBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaGuia.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelBaja);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		int idT = (int) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaGuia.this), "Error en la baja del Guia");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaGuia.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaGuia.this), "El Guia introducido ya esta dado de baja");
			break;
		case Evento.USUARIO_CON_ACTIVIDADES:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaGuia.this), "El Guia introducido tiene exposiciones vinculadas");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaGuia.this), "Guia con ID: " + idT + " dada de baja");
			break;
			
		}
	}
}
