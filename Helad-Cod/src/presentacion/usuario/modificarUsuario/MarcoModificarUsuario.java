package presentacion.usuario.modificarUsuario;

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

import negocio.trabajador.TTrabajador;
import negocio.usuario.TUsuario;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoModificarUsuario extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JLabel labelId;
	private JTextField textFieldId;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JTextField textFieldNombre;
	private JLabel labelNombre;
	private JTextField textFieldDNI;
	private JLabel labelDNI;
	private JTextField textFieldGuia;
	private JLabel labelGuia;
	
	public MarcoModificarUsuario(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Modificar Usuario");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Modificar de un Usuario");
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
		
		labelGuia = new JLabel("Id del guia: ");
		textFieldGuia = new JTextField();
		textFieldGuia.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelId);
		selectors.add(textFieldId);
		selectors.add(labelNombre);
		selectors.add(textFieldNombre);
		selectors.add(labelDNI);
		selectors.add(textFieldDNI);
		selectors.add(labelGuia);
		selectors.add(textFieldGuia);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idGuia = Integer.parseInt(textFieldGuia.getText());
				int id= Integer.parseInt(textFieldId.getText());
				TUsuario tUsuario = new TUsuario(id,textFieldNombre.getText(),textFieldDNI.getText(),idGuia);
				Controlador.getInstance().update(Evento.ACTUALIZAR_USUARIO, tUsuario);
				MarcoModificarUsuario.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoModificarUsuario.this.setVisible(false);
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
		TUsuario tUsuario= (TUsuario) datos;
		switch(evento){	
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "Error en la actualizacion del Usuario");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "El ID no existe en la base de datos");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "La entidad introducida no esta activa");
			break;
		case Evento.DNI_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "El DNI ya existe en la base de datos");
			break;
		case Evento.GUIA_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "La guia introducida no existe");
			break;
		case Evento.GUIA_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "El guia introducido no esta activo");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarUsuario.this), "Usuario con ID: " + tUsuario.getId() + " actualizado correctamente" );	
			break;
		}	
	}
}
