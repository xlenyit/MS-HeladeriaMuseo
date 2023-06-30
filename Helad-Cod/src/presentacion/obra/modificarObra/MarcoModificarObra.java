package presentacion.obra.modificarObra;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.obra.Escultura;
import negocio.obra.Pintura;
import negocio.obra.TEscultura;
import negocio.obra.TObra;
import negocio.obra.TPintura;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MarcoModificarObra extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelIDObra;
	private JLabel labelNombreObra;
	private JLabel labelAutorObra;
	private JLabel labelCosteObra;
	private JLabel labelExpoObra;
	private JLabel labelExtra;
	private JLabel labelTipoObra;
	private JTextField textFieldNombreObra;
	private JTextField textFieldAutorObra;
	private JTextField textFieldCosteObra;
	private JTextField textFieldIDObra;
	private JTextField textFieldExpoObra;
	private JButton botonActualizar;
	private JButton botonCancelar;
	private JComboBox<String> typeCB;
	private JComboBox<String> subtypeCB;
	
	public MarcoModificarObra(){
		initGUI();
	}
	private void initGUI() {
		this.setTitle("Actualizar Obra");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Actualizar de una Obra");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDObra = new JLabel("Id: ");
		textFieldIDObra = new JTextField();
		textFieldIDObra.setPreferredSize(new Dimension(40, 20));

		labelNombreObra = new JLabel("Nombre: ");
		textFieldNombreObra = new JTextField();
		textFieldNombreObra.setPreferredSize(new Dimension(100, 20));
		
		labelAutorObra = new JLabel("Autor: ");
		textFieldAutorObra = new JTextField();
		textFieldAutorObra.setPreferredSize(new Dimension(100, 20));
		
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
				if(typeCB.getSelectedItem() == "Pintura"){
					labelExtra.setText("Tipo: ");
					subtypeCB.setModel(new DefaultComboBoxModel<String>(subtiposS2)); 
				}
				else {
					labelExtra.setText("Material: ");
					subtypeCB.setModel(new DefaultComboBoxModel<String>(subtiposS)); 
				}
				
			}
		});
		
		
		labelCosteObra = new JLabel("Coste: ");
		textFieldCosteObra = new JTextField();
		textFieldCosteObra.setPreferredSize(new Dimension(100, 20));
		
		labelExpoObra = new JLabel("Exposicion: ");
		textFieldExpoObra = new JTextField();
		textFieldExpoObra.setPreferredSize(new Dimension(100, 20));	

		selectors.add(labelIDObra);
		selectors.add(textFieldIDObra);
		selectors.add(labelNombreObra);
		selectors.add(textFieldNombreObra);
		selectors.add(labelAutorObra);
		selectors.add(textFieldAutorObra);
		selectors.add(labelTipoObra);
		selectors.add(typeCB);
		selectors.add(labelExtra);
		selectors.add(subtypeCB);
		selectors.add(labelCosteObra);
		selectors.add(textFieldCosteObra);
		selectors.add(labelExpoObra);
		selectors.add(textFieldExpoObra);
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonActualizar = new JButton("Aceptar");
		botonActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(textFieldIDObra.getText());
				if (typeCB.getSelectedItem().toString().equalsIgnoreCase("pintura")){
					TPintura tPintura = new TPintura(id,subtypeCB.getSelectedItem().toString(),true,textFieldNombreObra.getText(),textFieldAutorObra.getText(),Double.parseDouble(textFieldCosteObra.getText()),Integer.parseInt(textFieldExpoObra.getText()));
					Controlador.getInstance().update(Evento.ACTUALIZAR_OBRA, tPintura);
				}else{
					TEscultura tEscultura = new TEscultura(id,subtypeCB.getSelectedItem().toString(),true,textFieldNombreObra.getText(),textFieldAutorObra.getText(),Double.parseDouble(textFieldCosteObra.getText()),Integer.parseInt(textFieldExpoObra.getText()));
					Controlador.getInstance().update(Evento.ACTUALIZAR_OBRA, tEscultura);
				}
				
				MarcoModificarObra.this.setVisible(false);

			}
			
		});
		buttonsPanel.add(botonActualizar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoModificarObra.this.setVisible(false);
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
		TObra tObra = (TObra) datos;
		switch(evento){
		case Evento.ERROR_GENERICO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "Error en la actualizacion de Obra");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "El ID no existe en la base de datos");
			break;
		case Evento.ENTIDAD_NO_EXISTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "No existe esa exposicion");
			break;
		case Evento.ENTIDAD_NO_ACTIVA:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "Esa exposicion esta inactiva");
			break;
		case Evento.TIPO_INCORRECTO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "Tipo incorrecto, no puede cambiar el tipo de la obra");
			break;
		case Evento.OBRA_EX_AUTOR_Y_NOMBRE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "La obra de ese autor con ese nombre ya existe en la base de datos");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "La obra esta inactiva");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoModificarObra.this), "Obra con ID: " + tObra.getId() + " actualizada correctamente" );	
			break;
		}	
		
	}
}