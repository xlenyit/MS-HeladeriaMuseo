package presentacion.exposicion.modificarExposicion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.exposicion.TExposicion;
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

public class MarcoModificarExposicion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIdExposicion;
	private JLabel labelNombreExposicion;
	private JLabel labelGeneroExposicion;
	private JTextField textFieldNombreExposicion;
	private JTextField textFieldGeneroExposicion;
	private JTextField textFieldIdExposicion;
	private JButton botonActualizar;
	private JButton botonCancelar;
	
	public MarcoModificarExposicion(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Modificar Exposicion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Modificar de una Exposicion");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIdExposicion = new JLabel("Id: ");
		textFieldIdExposicion = new JTextField();
		textFieldIdExposicion.setPreferredSize(new Dimension(40, 20));

		labelNombreExposicion = new JLabel("Nombre de la exposicion: ");
		textFieldNombreExposicion = new JTextField();
		textFieldNombreExposicion.setPreferredSize(new Dimension(100, 20));
		
		labelGeneroExposicion = new JLabel("Genero: ");
		textFieldGeneroExposicion = new JTextField();
		textFieldGeneroExposicion.setPreferredSize(new Dimension(100, 20));
		

		selectors.add(labelIdExposicion);
		selectors.add(textFieldIdExposicion);
		selectors.add(labelNombreExposicion);
		selectors.add(textFieldNombreExposicion);
		selectors.add(labelGeneroExposicion);
		selectors.add(textFieldGeneroExposicion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonActualizar = new JButton("Aceptar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(textFieldIdExposicion.getText());
				TExposicion tExposicion= new TExposicion(id,textFieldNombreExposicion.getText(),textFieldGeneroExposicion.getText());
				Controlador.getInstance().update(Evento.ACTUALIZAR_EXPOSICION, tExposicion);
				MarcoModificarExposicion.this.setVisible(false);

			}
			
		});
		buttonsPanel.add(botonActualizar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoModificarExposicion.this.setVisible(false);
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
		TExposicion tExposicion = (TExposicion) datos;
		switch(evento){
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarExposicion.this), "Error en la actualizacion de Exposicion");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarExposicion.this), "El nombre ya existe en la base de datos");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarExposicion.this), "La Exposicion esta inactiva");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarExposicion.this), "El id no existe en la base de datos");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarExposicion.this), "Exposicion con ID: " + tExposicion.getId() + " actualizada correctamente" );	
			break;
		}	
		
	}
}