package presentacion.actividad.altaActividad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.actividad.TCharla;
import negocio.actividad.TTaller;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoAltaActividad extends JFrame implements IGUI{	
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreActividad;
	private JLabel labelCodigoActividad;
	private JLabel labelFechaActividad;
	private JLabel labelTipoActividad;
	private JLabel labelExtra;
	private JTextField textFieldExtra;
	private JTextField textFieldNombreActividad;
	private JTextField textFieldCodigoActividad;
	private JTextField textFieldFechaActividad;
	private JButton botonAltaActividad;
	private JButton botonCancelarAltaActividad;
	private JComboBox<String> typeCB;
	
	
	public MarcoAltaActividad(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Actividad");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de una nueva Actividad");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		
		labelNombreActividad = new JLabel("Nombre: ");
		textFieldNombreActividad = new JTextField();
		textFieldNombreActividad.setPreferredSize(new Dimension(100, 20));
		
		labelCodigoActividad  = new JLabel("Codigo: ");
		textFieldCodigoActividad = new JTextField();
		textFieldCodigoActividad.setPreferredSize(new Dimension(100, 20));
		
		labelFechaActividad = new JLabel("Fecha: ");
		textFieldFechaActividad  = new JTextField();
		textFieldFechaActividad .setPreferredSize(new Dimension(100, 20));

		labelExtra = new JLabel("Utensilios: ");
		textFieldExtra = new JTextField();
		textFieldExtra.setPreferredSize(new Dimension(100, 20));
		
		labelTipoActividad = new JLabel("Tipo: ");
		String[] tipos = {"Taller","Charla"};
		typeCB = new JComboBox<String>(tipos);
		typeCB.setPreferredSize(new Dimension(100, 20));
		typeCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(typeCB.getSelectedItem() == "Taller") labelExtra.setText("Utensilios: ");
				else labelExtra.setText("Nivel: ");
				
			}
		});
		


		selectors.add(labelNombreActividad);
		selectors.add(textFieldNombreActividad);
		selectors.add(labelCodigoActividad);
		selectors.add(textFieldCodigoActividad);
		selectors.add(labelFechaActividad);
		selectors.add(textFieldFechaActividad);
		selectors.add(labelTipoActividad);
		selectors.add(typeCB);
		selectors.add(labelExtra);
		selectors.add(textFieldExtra);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaActividad = new JButton("Aceptar");
		botonAltaActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeCB.getSelectedItem().toString().equalsIgnoreCase("taller")){
					TTaller tTaller = new TTaller(textFieldNombreActividad.getText(), Integer.parseInt(textFieldCodigoActividad.getText()), Date.valueOf(textFieldFechaActividad.getText()), textFieldExtra.getText());
					Controlador.getInstance().update(Evento.ALTA_ACTIVIDAD, tTaller);
				} else{
					TCharla tCharla = new TCharla(textFieldNombreActividad.getText(), Integer.parseInt(textFieldCodigoActividad.getText()), Date.valueOf(textFieldFechaActividad.getText()), Integer.parseInt(textFieldExtra.getText()));
					Controlador.getInstance().update(Evento.ALTA_ACTIVIDAD, tCharla);
				}
				MarcoAltaActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaActividad);
		
		botonCancelarAltaActividad = new JButton("Cancelar");
		botonCancelarAltaActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarAltaActividad);
		
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
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "Error en el alta de la actividad");
			break;
		case Evento.FECHA_ANTIGUA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "No se puede crear una Actividad en una fecha previa a la actual");
			break;
		case Evento.ENTIDAD_NO_EXISTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "No existe esa actividad");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "Esa actividad esta inactiva");
			break;
		case Evento.TIPO_INCORRECTO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "El tipo no coincide con el de la base de datos");
			break;
		case Evento.CODIGO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "El codigo ya existe en la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaActividad.this), "Actividad con ID: " + evento + " creado correctamente");
			break;
		}
	}
}
