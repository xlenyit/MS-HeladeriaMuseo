package presentacion.exposicion.bajaExposicion;

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
public class MarcoBajaExposicion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIdExposicion;
	private JTextField textFieldIdExposicion;
	private JButton botonBajaExposicion;
	private JButton botonCancelarExposicion;
	private int id;
	
	public MarcoBajaExposicion(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Baja Exposicion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Exposicion a la que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIdExposicion = new JLabel("Id: ");
		textFieldIdExposicion = new JTextField();
		textFieldIdExposicion.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIdExposicion);
		selectors.add(textFieldIdExposicion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonBajaExposicion = new JButton("Aceptar");
		botonBajaExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldIdExposicion.getText());
				Controlador.getInstance().update(Evento.BAJA_EXPOSICION,id);
				MarcoBajaExposicion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonBajaExposicion);
		
		botonCancelarExposicion = new JButton("Cancelar");
		botonCancelarExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaExposicion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarExposicion);
		
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
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaExposicion.this), "Error en la Baja de la Exposicion");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaExposicion.this), "El ID introducido no existe");
			break;
		case Evento.ENTIDAD_YA_INACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaExposicion.this), "La Exposicion introducida ya estaba dada de baja");
			break;
		case Evento.EXPOSICION_CON_OBRAS:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaExposicion.this), "La Exposicion introducida contiene obras asociadas");
			break;
		case Evento.EXPOSICION_CON_GUIAS:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaExposicion.this), "La Exposicion introducida contiene guias asociadas");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaExposicion.this), "Exposicion con ID: " + idT + " dada de baja");	
			break;
		}
		
	}
}