package presentacion.exposicion.altaExposicion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.exposicion.TExposicion;
import negocio.seccion.TSeccion;
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
public class MarcoAltaExposicion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreExposicion;
	private JLabel labelGeneroExposicion;
	private JButton botonAltaExposicion;
	private JTextField textFieldNombreExposicion;
	private JTextField textFieldGeneroExposicion;
	private JButton bottonCancelarExposicion;
	
	public MarcoAltaExposicion(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Exposicion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de una nueva Exposicion");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelNombreExposicion = new JLabel("Nombre de la exposicion: ");
		textFieldNombreExposicion = new JTextField();
		textFieldNombreExposicion.setPreferredSize(new Dimension(100, 20));
		
		labelGeneroExposicion = new JLabel("Genero: ");
		textFieldGeneroExposicion = new JTextField();
		textFieldGeneroExposicion.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelNombreExposicion);
		selectors.add(textFieldNombreExposicion);
		selectors.add(labelGeneroExposicion);
		selectors.add(textFieldGeneroExposicion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaExposicion = new JButton("Aceptar");
		botonAltaExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TExposicion tExposicion = new TExposicion(textFieldNombreExposicion.getText(),textFieldGeneroExposicion.getText());

				Controlador.getInstance().update(Evento.ALTA_EXPOSICION,tExposicion);
				MarcoAltaExposicion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaExposicion);
		
		bottonCancelarExposicion = new JButton("Cancelar");
		bottonCancelarExposicion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaExposicion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(bottonCancelarExposicion);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	@Override
	public void update(Integer evento, Object datos) {
		switch(evento){
		case Evento.ERROR_GENERICO: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaExposicion.this), "Error en el Alta de la Exposicion");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaExposicion.this), "Ya existe esa Exposicion");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaExposicion.this), "Exposicion creada con ID: " + evento);	
			break;
		}
		
	}
}