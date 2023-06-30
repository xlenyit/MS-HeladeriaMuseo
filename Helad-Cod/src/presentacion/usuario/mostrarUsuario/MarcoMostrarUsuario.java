package presentacion.usuario.mostrarUsuario;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.trabajador.TTrabajador;
import negocio.usuario.TUsuario;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.trabajador.mostrarTrabajador.MarcoMostrarTrabajador;

public class MarcoMostrarUsuario extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JTextArea contenedorDatos;
	private JScrollPane scrolldatos;
	private JTextField textFieldId;
	private JButton botonMostrar;
	private JButton botonCerrar;
	
	public MarcoMostrarUsuario(){
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar Usuario");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Usuario que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JLabel labelId = new JLabel("Identificador: ");
		textFieldId = new JTextField();
		textFieldId.setPreferredSize(new Dimension(100,20));
		
		
		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldId);
		emergent.add(selectors);
		
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
//		contenedorDatos.setPreferredSize(new Dimension(300, 110));
		scrolldatos = new JScrollPane(contenedorDatos);
		scrolldatos.setPreferredSize(new Dimension(300, 110));
		
		scrolldatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scrolldatos);
		emergent.add(scrollPanel);
		
		JPanel buttonsPanel = new JPanel();
		
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if(textFieldId.getText().isEmpty()) id = 0;
				else id = Integer.valueOf(textFieldId.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_USUARIO, id);
				MarcoMostrarUsuario.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrar);
		
		botonCerrar = new JButton("Cancelar");
		botonCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarUsuario.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCerrar);
		
		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarUsuario.this), "El ID no puede ser negativo");
		}else if(datos == null){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarUsuario.this), "El ID no existe en la base de datos");
		}else{
			TUsuario tUsuario=(TUsuario) datos;
			String text = "";
			text += "Id: " + tUsuario.getId() + "\n";
			text += "Nombre: " + tUsuario.getNombre() + "\n";
			text += "DNI: " + tUsuario.getDni() + "\n";
			text += "Guia: " + tUsuario.getIdGuia() + "\n";
			text += tUsuario.getActivo() ? "ACTIVO" : "INACTIVO";
			contenedorDatos.setText(text);	
		}
	}

}
