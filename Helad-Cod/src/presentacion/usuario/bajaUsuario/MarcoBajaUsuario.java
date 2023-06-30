package presentacion.usuario.bajaUsuario;

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

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;


public class MarcoBajaUsuario extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelId;
	private JTextField textFieldId;
	private JButton botonAceptar;
	private JButton botonCancelar;
	
	public MarcoBajaUsuario(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Usuario");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Usuario al que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelId = new JLabel("Id: ");
		textFieldId = new JTextField();
		textFieldId.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelId);
		selectors.add(textFieldId);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().update(Evento.BAJA_USUARIO, Integer.parseInt(textFieldId.getText()));
				MarcoBajaUsuario.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaUsuario.this.setVisible(false);
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
		int idT = (Integer) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaUsuario.this), "Error en la Baja del Usuario");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaUsuario.this), "El ID introducido no existe");
			break;
		case Evento.ENTIDAD_YA_INACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaUsuario.this), "El usuario introducido ya estaba dado de baja");
			break;
		case Evento.USUARIO_CON_ACTIVIDADES:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaUsuario.this), "El usuario introducido tiene actividades activas");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaUsuario.this), "Usuario con ID: " + idT + " dado de baja");	
			break;
		}		
	}
}
