package presentacion.seccion.mostrarSeccion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.seccion.TSeccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.seccion.mostrarSeccion.MarcoMostrarSeccion;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
public class MarcoMostrarSeccion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIDSeccion;
	private JButton botonMostrarSeccion;
	private JButton botonCerrar;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	
	public MarcoMostrarSeccion(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Mostrar Seccion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Seccion que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JLabel labelId = new JLabel("Identificador: ");
		textFieldIDSeccion = new JTextField();
		textFieldIDSeccion.setPreferredSize(new Dimension(100,20));
		
		
		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIDSeccion);
		emergent.add(selectors);
		
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setPreferredSize(new Dimension(300, 200));
		
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scrollDatos);
		emergent.add(scrollPanel);
		
		JPanel buttonsPanel = new JPanel();
		
		botonMostrarSeccion = new JButton("Mostrar");
		botonMostrarSeccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if(textFieldIDSeccion.getText().isEmpty()) id = 0;
				else id = Integer.valueOf(textFieldIDSeccion.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_SECCION, id);
				MarcoMostrarSeccion.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarSeccion);
		
		botonCerrar = new JButton("Cancelar");
		botonCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarSeccion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCerrar);
		
		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void update(Integer evento, Object datos){
		if (evento < 0){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarSeccion.this), "El ID no puede ser negativo");
		}else if(datos == null){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarSeccion.this), "El ID no existe en la base de datos");
		}else{
			TSeccion tSeccion = (TSeccion) datos;
			String text = "";
			text += "Id: " + tSeccion.getId() + "\n";
			text += "Nombre: " + tSeccion.getNombre() + "\n";
			text += "Sueldo: " + tSeccion.getSueldo() + "\n";
			text += tSeccion.getActivo() ? "ACTIVO" : "INACTIVO";
			contenedorDatos.setText(text);
		}
	}
}