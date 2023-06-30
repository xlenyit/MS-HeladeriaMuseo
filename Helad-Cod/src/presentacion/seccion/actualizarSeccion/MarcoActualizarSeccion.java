package presentacion.seccion.actualizarSeccion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.seccion.TSeccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MarcoActualizarSeccion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDSeccion;
	private JLabel labelNombreSeccion;
	private JLabel labelSueldoSeccion;
	private JTextField textFieldNombreSeccion;
	private JTextField textFieldSueldoSeccion;
	private JTextField textFieldIDSeccion;
	private JButton botonActualizar;
	private JButton botonCancelar;
	
	public MarcoActualizarSeccion(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Actualizar Seccion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Actualizar de una Seccion");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDSeccion = new JLabel("Id: ");
		textFieldIDSeccion = new JTextField();
		textFieldIDSeccion.setPreferredSize(new Dimension(40, 20));

		labelNombreSeccion = new JLabel("Nombre de la seccion: ");
		textFieldNombreSeccion = new JTextField();
		textFieldNombreSeccion.setPreferredSize(new Dimension(100, 20));
		
		labelSueldoSeccion = new JLabel("Sueldo: ");
		textFieldSueldoSeccion = new JTextField();
		textFieldSueldoSeccion.setPreferredSize(new Dimension(100, 20));
		
		
				

		selectors.add(labelIDSeccion);
		selectors.add(textFieldIDSeccion);
		selectors.add(labelNombreSeccion);
		selectors.add(textFieldNombreSeccion);
		selectors.add(labelSueldoSeccion);
		selectors.add(textFieldSueldoSeccion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonActualizar = new JButton("Aceptar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double sueldo = Double.parseDouble(textFieldSueldoSeccion.getText());
				int id= Integer.parseInt(textFieldIDSeccion.getText());
				TSeccion tSeccion = new TSeccion(id,textFieldNombreSeccion.getText(), sueldo, true);
				Controlador.getInstance().update(Evento.ACTUALIZAR_SECCION, tSeccion);
				MarcoActualizarSeccion.this.setVisible(false);

			}
			
		});
		buttonsPanel.add(botonActualizar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoActualizarSeccion.this.setVisible(false);
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
		TSeccion tSeccion = (TSeccion) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarSeccion.this), "Error en la actualizacion de Seccion");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarSeccion.this), "El ID no existe en la base de datos");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarSeccion.this), "El nombre ya existe en la base de datos");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarSeccion.this), "Seccion con ID: " + tSeccion.getId() + " actualizada correctamente" );	
			break;
		}	
		
	}
}