package presentacion.ingrediente.bajaIngrediente;

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

public class MarcoBajaIngrediente extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JButton botonCancelBaja;
	private JLabel labelIDIngrediente;
	private JTextField textFieldIngrediente;
	private JButton botonCerrarBaja;
	private int id = -1;
	
	public MarcoBajaIngrediente(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Ingrediente");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Ingrediente al que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDIngrediente = new JLabel("Id: ");
		textFieldIngrediente = new JTextField();
		textFieldIngrediente.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIDIngrediente);
		selectors.add(textFieldIngrediente);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonCancelBaja = new JButton("Aceptar");
		botonCancelBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldIngrediente.getText());
				Controlador.getInstance().update(Evento.BAJA_INGREDIENTE, id);
				MarcoBajaIngrediente.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelBaja);
		
		botonCerrarBaja = new JButton("Cancelar");
		botonCerrarBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaIngrediente.this.setVisible(false);
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaIngrediente.this), "Error en la baja del Ingrediente");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaIngrediente.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaIngrediente.this), "El Ingrediente introducido ya esta dado de baja");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaIngrediente.this), "Usuario con ID: " + idT + " dado de baja");
			break;
			
		}
	}
}
