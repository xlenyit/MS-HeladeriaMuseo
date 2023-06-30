package presentacion.guia.altaGuia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.guia.TGuia;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.obra.altaObra.MarcoAltaObra;

public class MarcoAltaGuia extends JFrame implements IGUI{	
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreGuia;
	private JTextField textFieldNombreGuia;
	private JButton botonAltaGuia;
	private JButton botonCancelarAltaGuia;
	private JComboBox<String> typeCB;
	
	
	public MarcoAltaGuia(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Guia");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de un guia");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		
		labelNombreGuia = new JLabel("Nombre: ");
		textFieldNombreGuia = new JTextField();
		textFieldNombreGuia.setPreferredSize(new Dimension(100, 20));
		
	
		selectors.add(labelNombreGuia);
		selectors.add(textFieldNombreGuia);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaGuia = new JButton("Aceptar");
		botonAltaGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TGuia tGuia = new TGuia();
				tGuia.setNombre(textFieldNombreGuia.getText());
				Controlador.getInstance().update(Evento.ALTA_GUIA, tGuia);
				MarcoAltaGuia.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaGuia);
		
		botonCancelarAltaGuia = new JButton("Cancelar");
		botonCancelarAltaGuia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaGuia.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarAltaGuia);
		
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaGuia.this), "Error en el alta del Guia");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaGuia.this), "El nombre ya existe en la base de datos");
			break;
		case Evento.REACTIVACION:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaGuia.this), "Se ha reactivado con exito");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaGuia.this), "Guia con ID: " + evento + " creado correctamente");
			break;
		}
	}
}
