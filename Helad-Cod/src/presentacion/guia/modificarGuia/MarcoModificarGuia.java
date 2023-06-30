package presentacion.guia.modificarGuia;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.guia.TGuia;
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

public class MarcoModificarGuia extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDGuia;
	private JLabel labelNombreGuia;
	private JTextField textFieldIDGuia;
	private JTextField textFieldNombreGuia;
	private JButton botonActualizar;
	private JButton botonCancelar;
	
	public MarcoModificarGuia(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Actualizar Guia");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Actualizar de un Guia");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDGuia = new JLabel("Id: ");
		textFieldIDGuia = new JTextField();
		textFieldIDGuia.setPreferredSize(new Dimension(40, 20));

		labelNombreGuia = new JLabel("Nombre: ");
		textFieldNombreGuia = new JTextField();
		textFieldNombreGuia.setPreferredSize(new Dimension(100, 20));		

		selectors.add(labelIDGuia);
		selectors.add(textFieldIDGuia);
		selectors.add(labelNombreGuia);
		selectors.add(textFieldNombreGuia);
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonActualizar = new JButton("Aceptar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TGuia tGuia = new TGuia();
				tGuia.setNombre(textFieldNombreGuia.getText());
				tGuia.setId(Integer.parseInt(textFieldIDGuia.getText()));
				
				Controlador.getInstance().update(Evento.ACTUALIZAR_GUIA, tGuia);
				MarcoModificarGuia.this.setVisible(false);

			}
			
		});
		buttonsPanel.add(botonActualizar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoModificarGuia.this.setVisible(false);
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
		TGuia tGuia = (TGuia) datos;
		switch(evento){
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarGuia.this), "Error en la actualizacion del Guia");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarGuia.this), "El ID no existe en la base de datos");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarGuia.this), "El nombre ya existe en la base de datos");
			break;
		case Evento.GUIA_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarGuia.this), "El guia esta inactivo");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarGuia.this), "Guia con ID: " + tGuia.getId() + " actualizado correctamente" );	
			break;
		}	
		
	}
}