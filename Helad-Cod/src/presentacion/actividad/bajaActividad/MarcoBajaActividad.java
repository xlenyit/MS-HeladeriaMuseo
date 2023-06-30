package presentacion.actividad.bajaActividad;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
import javax.swing.JOptionPane;
public class MarcoBajaActividad extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIdActividad;
	private JTextField textFieldIdActividad;
	private JButton botonBajaActividad;
	private JButton botonCancelarActividad;
	private int id;
	
	public MarcoBajaActividad(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Baja Actividad");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la actividad a la que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIdActividad = new JLabel("Id: ");
		textFieldIdActividad = new JTextField();
		textFieldIdActividad.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIdActividad);
		selectors.add(textFieldIdActividad);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonBajaActividad = new JButton("Aceptar");
		botonBajaActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldIdActividad.getText());
				Controlador.getInstance().update(Evento.BAJA_ACTIVIDAD,id);
				MarcoBajaActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonBajaActividad);
		
		botonCancelarActividad = new JButton("Cancelar");
		botonCancelarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarActividad);
		
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaActividad.this), "Error en la Baja de la actividad");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaActividad.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaActividad.this), "La actividad introducida ya estaba dada de baja");
			break;
		case Evento.USUARIO_CON_ACTIVIDADES:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaActividad.this), "La actividad introducida no se puede dar de baja, tiene usuarios vinculados");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaActividad.this), "Actividad con ID: " + idT + " dada de baja");	
			break;
		}
		
	}
}