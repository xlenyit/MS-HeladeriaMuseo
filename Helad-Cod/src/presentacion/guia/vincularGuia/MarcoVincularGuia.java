package presentacion.guia.vincularGuia;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.guia.TGuia;
import negocio.guia.TLineaGuia;
import negocio.venta.TLineaVenta;
import negocio.venta.TVenta;
import presentacion.actividad.vincularActividad.MarcoVincularActividad;
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

public class MarcoVincularGuia extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelsLineaDeVenta;
	private JLabel labelAddExposicionId;
	private JLabel labelGuiaId;
	private JLabel labelHoraIni;
	private JButton botonVincular;
	private JButton botonDesvincular;
	private JTextField textFieldGuiaId;
	private JTextField textFieldAddExposicionId;
	private JTextField textFieldHoraIni;
	private JTextArea textAreaDatos; 
	private JScrollPane scroll;
	private JPanel panel;
	private String text = "";
	private List<TLineaGuia> tLineaGuias = new ArrayList<TLineaGuia>();
	
	public MarcoVincularGuia(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Vincular Guia");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para vincular exposiciones a Guia");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelsLineaDeVenta = new JLabel("Añadir exposicion: ");
		
		labelAddExposicionId = new JLabel("Id: ");
		textFieldAddExposicionId = new JTextField();
		textFieldAddExposicionId.setPreferredSize(new Dimension(100, 20));
		
		labelGuiaId = new JLabel("Id de guia: ");
		textFieldGuiaId= new JTextField();
		textFieldGuiaId.setPreferredSize(new Dimension(40, 20));
		
		
		labelHoraIni = new JLabel("Hora de inicio: ");
		textFieldHoraIni = new JTextField();
		textFieldHoraIni.setPreferredSize(new Dimension(100, 20));
		
		
		
		
		selectors.add(labelsLineaDeVenta);
		
		selectors.add(labelAddExposicionId);
		selectors.add(textFieldAddExposicionId);
		selectors.add(labelHoraIni);
		selectors.add(textFieldHoraIni);
		selectors.add(labelGuiaId);
		selectors.add(textFieldGuiaId);

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
				TLineaGuia tLineaGuia = new TLineaGuia(Integer.parseInt(textFieldGuiaId.getText()),Integer.parseInt(textFieldAddExposicionId.getText()),Integer.parseInt(textFieldHoraIni.getText()));
				Controlador.getInstance().update(Evento.ALTA_LINEA_GUIA, tLineaGuia);
				text+= "Guia: "+tLineaGuia.getIdGuia()+"\n";
				
				MarcoVincularGuia.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(labelGuiaId);
		buttonsPanel.add(textFieldGuiaId);
		buttonsPanel.add(botonVincular);
		buttonsPanel.add(botonVincular);
		
		botonDesvincular = new JButton("Desvincular");
		botonDesvincular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TLineaGuia tLineaGuia = new TLineaGuia(Integer.parseInt(textFieldGuiaId.getText()),Integer.parseInt(textFieldAddExposicionId.getText()),Integer.parseInt(textFieldHoraIni.getText()));
				Controlador.getInstance().update(Evento.BAJA_LINEA_GUIA, tLineaGuia);
				MarcoVincularGuia.this.setVisible(false);
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "Error al vincular en guia");
			break;
		case Evento.VINCULADO_OK:
			TLineaGuia g = (TLineaGuia) datos;
				text += "Guia: " + g.getIdGuia() + " Exposicion: " + g.getIdExpo() + " Hora de inicio: " + g.getHoraIni()+ "\n";
			textAreaDatos.setText(text);
			break;
		case Evento.DESVINCULADO_OK:
			TLineaGuia g2 = (TLineaGuia) datos;
				text += "DESVINCULADO Guia: " + g2.getIdGuia() + " Exposicion: " + g2.getIdExpo() + " Hora de inicio: " + g2.getHoraIni()+ "\n";
			textAreaDatos.setText(text);
			break;
		case Evento.YA_DESVINCULADO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "La exposicion ya esta desvinculada del guia");
			break;
		case Evento.GUIA_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "El guia esta inactivo");
			break;
		case Evento.GUIA_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "El guia no existe en la base de datos");
			break;
		case Evento.GUIA_OCUPADO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "El guia ya tiene una exposicion a esa hora");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "La exposicion esta inactiva");
			break;
		case Evento.ENTIDAD_NO_EXISTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "La exposicion no existe en la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoVincularGuia.this), "bien");
			break;
		}
	}
}