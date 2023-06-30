package presentacion.obra.altaObra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.obra.Escultura;
import negocio.obra.Pintura;
import negocio.obra.TEscultura;
import negocio.obra.TPintura;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoAltaObra extends JFrame implements IGUI{	
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreObra;
	private JLabel labelAutorObra;
	private JLabel labelCosteObra;
	private JLabel labelTipoObra;
	private JLabel labelExtra;
	private JLabel labelIdExposicion;
	private JTextField textFieldIDExposicion;
	private JTextField textFieldNombreObra;
	private JTextField textFieldAutorObra;
	private JTextField textFieldCosteObra;
	private JButton botonAltaObra;
	private JButton botonCancelarAltaObra;
	private JComboBox<String> typeCB;
	private JComboBox<String> subtypeCB;
	
	
	public MarcoAltaObra(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Obra");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de una nueva Obra");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		
		labelNombreObra = new JLabel("Nombre: ");
		textFieldNombreObra = new JTextField();
		textFieldNombreObra.setPreferredSize(new Dimension(100, 20));
		
		labelAutorObra  = new JLabel("Autor: ");
		textFieldAutorObra = new JTextField();
		textFieldAutorObra.setPreferredSize(new Dimension(100, 20));

		labelCosteObra = new JLabel("Coste: ");
		textFieldCosteObra  = new JTextField();
		textFieldCosteObra .setPreferredSize(new Dimension(100, 20));

		labelExtra = new JLabel("Material: ");
		Set<String> subtipos =Escultura.PORCENTAJE_ESCULTURAS.keySet();
		String[] subtiposS = (String[]) subtipos.toArray(new String[subtipos.size()]);
	
		Set<String> subtipos2 =Pintura.PORCENTAJE_PINTURAS.keySet();
		String[] subtiposS2 = (String[]) subtipos2.toArray(new String[subtipos2.size()]);
		subtypeCB = new JComboBox<String>(subtiposS);
		subtypeCB.setPreferredSize(new Dimension(100, 20));
		
		labelTipoObra = new JLabel("Tipo: ");
		String[] tipos = {"Escultura","Pintura"};
		typeCB = new JComboBox<String>(tipos);
		typeCB.setPreferredSize(new Dimension(100, 20));
		typeCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(typeCB.getSelectedItem() == "Pintura") {
					labelExtra.setText("Tipo: ");
					subtypeCB.setModel(new DefaultComboBoxModel<String>(subtiposS2)); 
				}
				else {
					labelExtra.setText("Material: ");
					subtypeCB.setModel(new DefaultComboBoxModel<String>(subtiposS)); 
				}
				
			}
		});
		

		labelIdExposicion  = new JLabel("Id Expo: ");
		textFieldIDExposicion = new JTextField();
		textFieldIDExposicion.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelNombreObra);
		selectors.add(textFieldNombreObra);
		selectors.add(labelAutorObra);
		selectors.add(textFieldAutorObra);
		selectors.add(labelCosteObra);
		selectors.add(textFieldCosteObra);
		selectors.add(labelTipoObra);
		selectors.add(typeCB);
		selectors.add(labelExtra);
		selectors.add(subtypeCB);
		selectors.add(labelIdExposicion);
		selectors.add(textFieldIDExposicion);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaObra = new JButton("Aceptar");
		botonAltaObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeCB.getSelectedItem().toString().equalsIgnoreCase("pintura")){
					TPintura tPintura = new TPintura(-1,subtypeCB.getSelectedItem().toString(),true,textFieldNombreObra.getText(),textFieldAutorObra.getText(),Double.parseDouble(textFieldCosteObra.getText()),Integer.parseInt(textFieldIDExposicion.getText()));
					Controlador.getInstance().update(Evento.ALTA_OBRA, tPintura);
				}else{
					TEscultura tEscultura = new TEscultura(-1,subtypeCB.getSelectedItem().toString(),true,textFieldNombreObra.getText(),textFieldAutorObra.getText(),Double.parseDouble(textFieldCosteObra.getText()),Integer.parseInt(textFieldIDExposicion.getText()));
					Controlador.getInstance().update(Evento.ALTA_OBRA, tEscultura);
				}
				MarcoAltaObra.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaObra);
		
		botonCancelarAltaObra = new JButton("Cancelar");
		botonCancelarAltaObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaObra.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarAltaObra);
		
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "Error en el alta del Obra");
			break;
		case Evento.TIPO_INCORRECTO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "No se puede cambiar el tipo de la obra ya existente");
			break;
		case Evento.ENTIDAD_NO_EXISTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "No existe esa exposicion");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "Esa exposicion esta inactiva");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "El nombre y autor ya existen en la base de datos");
			break;
		case Evento.REACTIVACION:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "Se ha reactivado con exito");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaObra.this), "Obra con ID: " + evento + " creado correctamente");
			break;
		}
	}
}
