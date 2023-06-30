 package presentacion.trabajador.actualizarTrabajador;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.trabajador.TTrabajador;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
public class MarcoActualizarTrabajador extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelId;
	private JTextField textFieldId;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JTextField textFieldNombre;
	private JLabel labelNombre;
	private JTextField textFieldDNI;
	private JLabel labelDNI;
	private JTextField textFieldSeccion;
	private JLabel labelSeccion;
	private JTextField textFieldTelefono;
	private JLabel labelTelefono;
	
	public MarcoActualizarTrabajador(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Actualizar Trabajador");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Actualizar de un Trabajador");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelId = new JLabel("Id: ");
		textFieldId = new JTextField();
		textFieldId.setPreferredSize(new Dimension(40, 20));

		labelNombre = new JLabel("Nombre: ");
		textFieldNombre = new JTextField();
		textFieldNombre.setPreferredSize(new Dimension(100, 20));
		
		labelDNI = new JLabel("DNI: ");
		textFieldDNI = new JTextField();
		textFieldDNI.setPreferredSize(new Dimension(100, 20));
		
		labelTelefono = new JLabel("Telefono: ");
		textFieldTelefono = new JTextField();
		textFieldTelefono.setPreferredSize(new Dimension(100, 20));
		
		labelSeccion = new JLabel("Id de la seccion: ");
		textFieldSeccion = new JTextField();
		textFieldSeccion.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelId);
		selectors.add(textFieldId);
		selectors.add(labelNombre);
		selectors.add(textFieldNombre);
		selectors.add(labelDNI);
		selectors.add(textFieldDNI);
		selectors.add(labelTelefono);
		selectors.add(textFieldTelefono);
		selectors.add(labelSeccion);
		selectors.add(textFieldSeccion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int telefono = Integer.parseInt(textFieldTelefono.getText());
				int id= Integer.parseInt(textFieldId.getText());
				TTrabajador tTrabajador = new TTrabajador(id,textFieldDNI.getText(), telefono, textFieldNombre.getText(), true,Integer.parseInt(textFieldSeccion.getText()));
				Controlador.getInstance().update(Evento.ACTUALIZAR_TRABAJADOR, tTrabajador);
				MarcoActualizarTrabajador.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoActualizarTrabajador.this.setVisible(false);
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
		TTrabajador tTrabajador = (TTrabajador) datos;
		switch(evento){	
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarTrabajador.this), "Error en la actualizacion del Trabajador");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarTrabajador.this), "El ID no existe en la base de datos");
			break;
		case Evento.DNI_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarTrabajador.this), "El DNI ya existe en la base de datos");
			break;
		case Evento.SECCION_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarTrabajador.this), "La seccion introducida no existe");
			break;
		case Evento.SECCION_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarTrabajador.this), "La seccion introducida no esta activa");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarTrabajador.this), "Usuario con ID: " + tTrabajador.getId() + " actualizado correctamente" );	
			break;
		}	
	}
}