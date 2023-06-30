package presentacion.usuario.altaUsuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.usuario.TUsuario;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoAltaUsuario extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreUsuario;
	private JLabel labelDNIUsuario;
	private JLabel labelIdGuia;
	private JButton botonAltaUsuario;
	private JTextField textFieldNombreUsuario;
	private JTextField textFieldDNIUsuario;
	private JTextField textFieldIdGuia;
	private JButton bottonCancelarUsuario;
	
	public MarcoAltaUsuario(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Usuario");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de un nuevo Usuario");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelNombreUsuario = new JLabel("Nombre del usuario: ");
		textFieldNombreUsuario = new JTextField();
		textFieldNombreUsuario.setPreferredSize(new Dimension(100, 20));
		
		labelDNIUsuario = new JLabel("DNI: ");
		textFieldDNIUsuario = new JTextField();
		textFieldDNIUsuario.setPreferredSize(new Dimension(100, 20));
		
		labelIdGuia = new JLabel("Id guia: ");
		textFieldIdGuia= new JTextField();
		textFieldIdGuia.setPreferredSize(new Dimension(100, 20));
		

		selectors.add(labelNombreUsuario);
		selectors.add(textFieldNombreUsuario);
		selectors.add(labelDNIUsuario);
		selectors.add(textFieldDNIUsuario);
		selectors.add(labelIdGuia);
		selectors.add(textFieldIdGuia);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaUsuario = new JButton("Aceptar");
		botonAltaUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TUsuario tUsuario = new TUsuario(textFieldNombreUsuario.getText(), textFieldDNIUsuario.getText(), Integer.parseInt(textFieldIdGuia.getText()));

				Controlador.getInstance().update(Evento.ALTA_USUARIO,tUsuario);
				MarcoAltaUsuario.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaUsuario);
		
		bottonCancelarUsuario = new JButton("Cancelar");
		bottonCancelarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaUsuario.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(bottonCancelarUsuario);
		
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
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaUsuario.this), "Error en el Alta del Usuario");
			break;
		case Evento.DNI_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaUsuario.this), "El DNI ya existe en la base de datos");
			break;
		case Evento.GUIA_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaUsuario.this), "El guia introducido no existe");
			break;
		case Evento.GUIA_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaUsuario.this), "El guia introducido no esta activo");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaUsuario.this), "Usuario creado con ID: " + evento);	
			break;
		}
		
	}
}
