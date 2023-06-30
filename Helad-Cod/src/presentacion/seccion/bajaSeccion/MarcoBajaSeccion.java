package presentacion.seccion.bajaSeccion;

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
public class MarcoBajaSeccion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDSeccion;
	private JTextField textFieldIDSeccion;
	private JButton botonBajaSeccion;
	private JButton botonCancelBaja;
	private int id;
	
	public MarcoBajaSeccion(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Baja Seccion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Seccion a la que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDSeccion = new JLabel("Id: ");
		textFieldIDSeccion = new JTextField();
		textFieldIDSeccion.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIDSeccion);
		selectors.add(textFieldIDSeccion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonBajaSeccion = new JButton("Aceptar");
		botonBajaSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldIDSeccion.getText());
				Controlador.getInstance().update(Evento.BAJA_SECCION,id);
				MarcoBajaSeccion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonBajaSeccion);
		
		botonCancelBaja = new JButton("Cancelar");
		botonCancelBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaSeccion.this.setVisible(false);
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
		int idT = (Integer) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaSeccion.this), "Error en la Baja de la Seccion");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaSeccion.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaSeccion.this), "La seccion introducida ya estaba dada de baja");
			break;
		case Evento.SECCION_CON_TRABAJADOR:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaSeccion.this), "La seccion introducida contiene trabajadores");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaSeccion.this), "Seccion con ID: " + idT + " dada de baja");	
			break;
		}
		
	}
}