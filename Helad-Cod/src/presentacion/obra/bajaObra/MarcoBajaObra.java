package presentacion.obra.bajaObra;

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

public class MarcoBajaObra extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JButton botonCancelBaja;
	private JLabel labelIDObra;
	private JTextField textFieldObra;
	private JButton botonCerrarBaja;
	private int id = -1;
	
	public MarcoBajaObra(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Obra");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Obra a la que dar de Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDObra = new JLabel("Id: ");
		textFieldObra = new JTextField();
		textFieldObra.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIDObra);
		selectors.add(textFieldObra);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonCancelBaja = new JButton("Aceptar");
		botonCancelBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldObra.getText());
				Controlador.getInstance().update(Evento.BAJA_OBRA, id);
				MarcoBajaObra.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelBaja);
		
		botonCerrarBaja = new JButton("Cancelar");
		botonCerrarBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaObra.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCerrarBaja);
		
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaObra.this), "Error en la baja de Obra");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaObra.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaObra.this), "La Obra introducida ya esta dada de baja");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaObra.this), "Obra con ID: " + idT + " dada de baja");
			break;
			
		}
	}
}
