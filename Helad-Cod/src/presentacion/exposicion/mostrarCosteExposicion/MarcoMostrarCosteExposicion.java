package presentacion.exposicion.mostrarCosteExposicion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.exposicion.TExposicion;
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
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
public class MarcoMostrarCosteExposicion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdExposicion;
	private JButton botonMostrarExposicion;
	private JButton botonCerrar;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	
	public MarcoMostrarCosteExposicion(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Mostrar Coste Exposicion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Exposicion que desee Mostrar su Coste");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JLabel labelId = new JLabel("Identificador: ");
		textFieldIdExposicion = new JTextField();
		textFieldIdExposicion.setPreferredSize(new Dimension(100,20));
		
		
		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIdExposicion);
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
		
		botonMostrarExposicion = new JButton("Calcular");
		botonMostrarExposicion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if(textFieldIdExposicion.getText().isEmpty()) id = 0;
				else id = Integer.valueOf(textFieldIdExposicion.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_COSTE_EXPOSICION, id);
				MarcoMostrarCosteExposicion.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarExposicion);
		
		botonCerrar = new JButton("Cancelar");
		botonCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarCosteExposicion.this.setVisible(false);
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarCosteExposicion.this), "El ID no puede ser negativo");
		}else if(((List<Object>) datos).get(1) == null){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarCosteExposicion.this), "El ID no existe en la base de datos");
		}else{
			TExposicion tExposicion = (TExposicion) ((List<Object>) datos).get(1);
			Double coste = (Double) ((List<Object>) datos).get(0);

			String text = "";

			text += "La exposicion  " + tExposicion.getNombre();
			text += " cuyo genero es " + tExposicion.getGenero() + "\n";
			text += "tiene un coste total de " + coste + "\n";
			contenedorDatos.setText(text);
		}
	}
}