package presentacion.actividad.modificarActividad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import negocio.actividad.TActividad;
import negocio.actividad.TCharla;
import negocio.actividad.TTaller;
import presentacion.actividad.altaActividad.MarcoAltaActividad;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoModificarActividad extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JLabel labelIdActividad;
	private JLabel labelCodigoActividad;
	private JLabel labelNombreActividad;
	private JLabel labelFechaActividad;
	private JLabel labelTipoActividad;
	private JLabel labelExtra;
	private JTextField textFieldExtra;
	private JTextField textFieldIdActividad;
	private JTextField textFieldCodigoActividad;
	private JTextField textFieldNombreActividad;
	private JTextField textFieldFechaActividad;
	private JButton botonActualizar;
	private JButton botonCancelar;
	private JComboBox<String> typeCB;
	
	public MarcoModificarActividad() {
		initGUI();
	}
	
	public void initGUI() {
		this.setTitle("Modificar Exposicion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Modificar de una Actividad");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIdActividad = new JLabel("Id: ");
		textFieldIdActividad = new JTextField();
		textFieldIdActividad.setPreferredSize(new Dimension(40, 20));
		
		labelCodigoActividad = new JLabel("Código: ");
		textFieldCodigoActividad = new JTextField();
		textFieldCodigoActividad.setPreferredSize(new Dimension(40, 20));
		
		labelNombreActividad = new JLabel("Nombre: ");
		textFieldNombreActividad = new JTextField();
		textFieldNombreActividad.setPreferredSize(new Dimension(100, 20));
		
		labelFechaActividad = new JLabel("Fecha: ");
		textFieldFechaActividad = new JTextField();
		textFieldFechaActividad.setPreferredSize(new Dimension(40, 20));
		
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
		
		selectors.add(labelIdActividad);
		selectors.add(textFieldIdActividad);
		selectors.add(labelCodigoActividad);
		selectors.add(textFieldCodigoActividad);
		selectors.add(labelNombreActividad);
		selectors.add(textFieldNombreActividad);
		selectors.add(labelFechaActividad);
		selectors.add(textFieldFechaActividad);
		selectors.add(labelTipoActividad);
		selectors.add(typeCB);
		selectors.add(labelExtra);
		selectors.add(textFieldExtra);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonActualizar = new JButton("Aceptar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeCB.getSelectedItem().toString().equalsIgnoreCase("taller")){
					TTaller tTaller = new TTaller(Integer.parseInt(textFieldIdActividad.getText()),textFieldNombreActividad.getText(), Integer.parseInt(textFieldCodigoActividad.getText()),true, Date.valueOf(textFieldFechaActividad.getText()), textFieldExtra.getText());
					Controlador.getInstance().update(Evento.ACTUALIZAR_ACTIVIDAD, tTaller);
				} else{
					TCharla tCharla = new TCharla(Integer.parseInt(textFieldIdActividad.getText()),textFieldNombreActividad.getText(), Integer.parseInt(textFieldCodigoActividad.getText()),true,Date.valueOf(textFieldFechaActividad.getText()), Integer.parseInt(textFieldExtra.getText()));
					Controlador.getInstance().update(Evento.ACTUALIZAR_ACTIVIDAD, tCharla);
				}
				MarcoModificarActividad.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonActualizar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoModificarActividad.this.setVisible(false);
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
		TActividad tActividad = (TActividad) datos;
		
		switch(evento) {
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarActividad.this), "Error en la actualizacion de Actividad");
			break;
		case Evento.FECHA_ANTIGUA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarActividad.this), "No se puede modificar una Actividad a una fecha previa a la actual");
			break;
		case Evento.TIPO_INCORRECTO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarActividad.this), "El tipo a modificar no coincide con el de la base de datos");
			break;
		case Evento.CODIGO_ACTIVIDAD_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarActividad.this), "El codigo a modificar ya existe en la base de datos");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarActividad.this), "El ID no existe en la base de datos");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarActividad.this), "Actividad con ID: " + tActividad.getId() + " actualizada correctamente" );	
			break;
		}
		
	}
	
}
