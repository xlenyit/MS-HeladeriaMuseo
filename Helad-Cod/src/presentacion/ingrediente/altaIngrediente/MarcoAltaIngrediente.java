package presentacion.ingrediente.altaIngrediente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.ingrediente.TIngrediente;
import negocio.ingrediente.TLiquido;
import negocio.ingrediente.TSolido;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoAltaIngrediente extends JFrame implements IGUI{	
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreIngrediente;
	private JLabel labelCantidadIngrediente;
	private JLabel labelTipoIngrediente;
	private JLabel labelCodigoIngrediente;
	private JButton botonAltaIngrediente;
	private JLabel labelExtra;
	private JLabel labelIdProducto;
	private JTextField textFieldExtra;
	private JTextField textFieldIDProducto;
	JTextField textFieldNombreIngrediente;
	private JTextField textFieldCantidadIngrediente;
	private JTextField textFieldCodigoIngrediente;
	private JButton botonCancelarAltaIngrediente;
	private JComboBox<String> typeCB;
	
	
	public MarcoAltaIngrediente(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Ingrediente");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de un nuevo Ingrediente");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		
		labelNombreIngrediente = new JLabel("Nombre: ");
		textFieldNombreIngrediente = new JTextField();
		textFieldNombreIngrediente.setPreferredSize(new Dimension(100, 20));
		
		labelExtra = new JLabel("Espesor: ");
		textFieldExtra = new JTextField();
		textFieldExtra.setPreferredSize(new Dimension(100, 20));
		
		labelCodigoIngrediente = new JLabel("Codigo: ");
		textFieldCodigoIngrediente  = new JTextField();
		textFieldCodigoIngrediente .setPreferredSize(new Dimension(100, 20));
		
		labelTipoIngrediente = new JLabel("Tipo: ");
		String[] tipos = {"Liquido", "Solido"};
		typeCB = new JComboBox<String>(tipos);
		typeCB.setPreferredSize(new Dimension(100, 20));
		typeCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(typeCB.getSelectedItem() == "Liquido") labelExtra.setText("Espesor: ");
				else labelExtra.setText("Tamanio: ");
				
			}
		});
		labelCantidadIngrediente  = new JLabel("Cantidad: ");
		textFieldCantidadIngrediente = new JTextField();
		textFieldCantidadIngrediente.setPreferredSize(new Dimension(100, 20));

		labelIdProducto  = new JLabel("Id Producto: ");
		textFieldIDProducto = new JTextField();
		textFieldIDProducto.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelNombreIngrediente);
		selectors.add(textFieldNombreIngrediente);
		selectors.add(labelCodigoIngrediente);
		selectors.add(textFieldCodigoIngrediente);
		selectors.add(labelTipoIngrediente);
		selectors.add(typeCB);
		selectors.add(labelCantidadIngrediente);
		selectors.add(textFieldCantidadIngrediente);
		selectors.add(labelExtra);
		selectors.add(textFieldExtra);
		selectors.add(labelIdProducto);
		selectors.add(textFieldIDProducto);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAltaIngrediente = new JButton("Aceptar");
		botonAltaIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typeCB.getSelectedItem().toString().equalsIgnoreCase("liquido")){
					TIngrediente tLiquido = new TLiquido(-1,Integer.parseInt(textFieldExtra.getText()),textFieldNombreIngrediente.getText(), Integer.parseInt(textFieldCantidadIngrediente.getText()),textFieldCodigoIngrediente.getText(), true,Integer.parseInt(textFieldIDProducto.getText()));
					Controlador.getInstance().update(Evento.ALTA_INGREDIENTE, tLiquido);
				}else{
					TIngrediente tSolido = new TSolido(-1,textFieldExtra.getText(),textFieldNombreIngrediente.getText(), Integer.parseInt(textFieldCantidadIngrediente.getText()),textFieldCodigoIngrediente.getText(), true,Integer.parseInt(textFieldIDProducto.getText()));
					Controlador.getInstance().update(Evento.ALTA_INGREDIENTE, tSolido);
				}
				MarcoAltaIngrediente.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAltaIngrediente);
		
		botonCancelarAltaIngrediente = new JButton("Cancelar");
		botonCancelarAltaIngrediente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaIngrediente.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelarAltaIngrediente);
		
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
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaIngrediente.this), "Error en el alta del Ingrediente");
			break;
		case Evento.TIPO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaIngrediente.this), "El tipo no coincide con el de la base de datos");
			break;
		case Evento.CODIGO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaIngrediente.this), "El codigo ya existe en la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaIngrediente.this), "Ingrediente con ID: " + evento + " creado correctamente");
			break;
		}
	}
}
