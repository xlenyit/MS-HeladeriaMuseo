package presentacion.actividad.vincularActividad;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.actividad.TLineaActividad;
import negocio.guia.TLineaGuia;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MarcoVincularActividad extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelsLineaDeVenta;
	private JLabel labelAddUsuarioId;
	private JLabel labelActividadId;
	private JButton botonVincular;
	private JButton botonDesvincular;
	private JTextField textFieldActividadId;
	private JTextField textFieldAddUsuarioId;
	private JTextArea textAreaDatos; 
	private JScrollPane scroll;
	private JPanel panel;
	private String text = "";
	private List<TLineaGuia> tLineaGuias = new ArrayList<TLineaGuia>();
	
	public MarcoVincularActividad(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Vincular Actividad");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para vincular usuarios a Actividad");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelsLineaDeVenta = new JLabel("Añadir usuario: ");
		
		labelAddUsuarioId = new JLabel("Id: ");
		textFieldAddUsuarioId = new JTextField();
		textFieldAddUsuarioId.setPreferredSize(new Dimension(100, 20));
		
		labelActividadId = new JLabel("Id de Actividad: ");
		textFieldActividadId= new JTextField();
		textFieldActividadId.setPreferredSize(new Dimension(40, 20));
		
		selectors.add(labelsLineaDeVenta);
		
		selectors.add(labelAddUsuarioId);
		selectors.add(textFieldAddUsuarioId);
		selectors.add(labelActividadId);
		selectors.add(textFieldActividadId);

		emergent.add(selectors);
		
		panel = new JPanel();
		textAreaDatos = new JTextArea(text);
		textAreaDatos.setEditable(false);
		textAreaDatos.setBounds(25, 75, 500, 200);
		scroll = new JScrollPane(textAreaDatos);
		scroll.setPreferredSize(new Dimension(500, 200));
		
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));
		panel.add(scroll);
		panel.add(panelAux);
		
		panel.setBackground(Color.white);
		emergent.add(panel);
		
		
		JPanel buttonsPanel = new JPanel();
		
		botonVincular = new JButton("Vincular");
		botonVincular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TLineaActividad tLineaAct = new TLineaActividad(Integer.parseInt(textFieldActividadId.getText()),Integer.parseInt(textFieldAddUsuarioId.getText()));
				Controlador.getInstance().update(Evento.ALTA_LINEA_ACTIVIDAD, tLineaAct);
				MarcoVincularActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(labelActividadId);
		buttonsPanel.add(textFieldActividadId);
		buttonsPanel.add(botonVincular);
		buttonsPanel.add(botonVincular);
		
		botonDesvincular = new JButton("Desvincular");
		botonDesvincular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TLineaActividad tLineaAct = new TLineaActividad(Integer.parseInt(textFieldActividadId.getText()),Integer.parseInt(textFieldAddUsuarioId.getText()));
				Controlador.getInstance().update(Evento.BAJA_LINEA_ACTIVIDAD, tLineaAct);
				MarcoVincularActividad.this.setVisible(false);
			}
			
		});
		
		buttonsPanel.add(botonDesvincular);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Integer evento, Object datos) {
		switch(evento){
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "Error al vincular en guia");
			break;
		case Evento.VINCULADO_OK:
			TLineaActividad a = (TLineaActividad) datos;
				text += "Actividad: " + a.getActividad() + " Usuario: " +a.getUsuario() +"\n";
			textAreaDatos.setText(text);
			break;
		case Evento.DESVINCULADO_OK:
			TLineaActividad a2 = (TLineaActividad) datos;
				text += "DESVINCULADO Actividad: " + a2.getActividad() + " Usuario: " + a2.getUsuario() + "\n";
			textAreaDatos.setText(text);
			break;
		case Evento.ACTIVIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "La actividad esta inactiva");
			break;
		case Evento.ACTIVIDAD_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "La actividad no existe en la base de datos");
			break;
		case Evento.USUARIO_YA_VINCULADO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "El usuario ya esta vinculado a la actividad");
			break;
		case Evento.YA_DESVINCULADO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "El usuario ya esta desvinculado de la actividad");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "El usuario esta inactivo");
			break;
		case Evento.ENTIDAD_NO_EXISTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "El usuario no existe en la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularActividad.this), "bien");
			break;
		}
	}
}