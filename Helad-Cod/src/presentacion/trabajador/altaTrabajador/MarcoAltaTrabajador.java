package presentacion.trabajador.altaTrabajador;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.trabajador.TTrabajador;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.JLabel;

public class MarcoAltaTrabajador extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldDNI;
	private JTextField textFieldTelefono;
	private JTextField textFieldIdSeccion;
	private JTextField textFieldNombre;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JLabel labelDNI;
	private JLabel labelTelefono;
	private JLabel labelNombre;
	private JLabel labelIdSeccion;

	public MarcoAltaTrabajador(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Trabajador");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de un nuevo Trabajador");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelNombre = new JLabel("Nombre: ");
		textFieldNombre = new JTextField();
		textFieldNombre.setPreferredSize(new Dimension(100, 20));
		
		labelDNI = new JLabel("DNI: ");
		textFieldDNI = new JTextField();
		textFieldDNI.setPreferredSize(new Dimension(100, 20));
		
		labelTelefono = new JLabel("Telefono: ");
		textFieldTelefono = new JTextField();
		textFieldTelefono.setPreferredSize(new Dimension(100, 20));

		labelIdSeccion= new JLabel("Id de la seccion: ");
		textFieldIdSeccion = new JTextField();
		textFieldIdSeccion.setPreferredSize(new Dimension(30, 20));
		
				

		selectors.add(labelNombre);
		selectors.add(textFieldNombre);
		selectors.add(labelDNI);
		selectors.add(textFieldDNI);
		selectors.add(labelTelefono);
		selectors.add(textFieldTelefono);
		selectors.add(labelIdSeccion);
		selectors.add(textFieldIdSeccion);
		
		


		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Revisar id_seccion
				TTrabajador tTrabajador = new TTrabajador(-1,textFieldDNI.getText(), Integer.parseInt(textFieldTelefono.getText()) ,textFieldNombre.getText(), true, Integer.parseInt(textFieldIdSeccion.getText()));
				
				Controlador.getInstance().update(Evento.ALTA_TRABAJADOR, tTrabajador);

				MarcoAltaTrabajador.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaTrabajador.this.setVisible(false);
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
		//Explicar esto diagrama de clase controlador
		switch(evento){	
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaTrabajador.this), "Error en el Alta del Trabajador");
			break;
		case Evento.DNI_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaTrabajador.this), "El DNI ya existe en la base de datos");
			break;
		case Evento.SECCION_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaTrabajador.this), "La seccion introducida no existe");
			break;
		case Evento.SECCION_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaTrabajador.this), "La seccion introducida no esta activa");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaTrabajador.this), "Usuario creado con ID: " + evento);	
			break;
		}		
	}
}