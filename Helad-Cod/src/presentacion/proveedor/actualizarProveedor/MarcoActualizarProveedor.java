package presentacion.proveedor.actualizarProveedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.proveedor.TProveedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MarcoActualizarProveedor extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JLabel labelidProveedor;
	private JLabel labelNombreProveedor;
	private JLabel labelNumCuentaProveedor;
	private JLabel labelNIFProveedor;
	private JTextField textFieldidProveedor;
	private JTextField textFieldNIFProveedor;
	private JTextField textFieldNombreProveedor;
	private JTextField textFieldNumCuentaProveedor;
	private JButton botonAceptar;
	private JButton botonCancelar;

	public MarcoActualizarProveedor(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Actualizar Proveedor");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos que quieras Actualizar de un Proveedor");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelidProveedor = new JLabel("Id: ");
		textFieldidProveedor = new JTextField();
		textFieldidProveedor.setPreferredSize(new Dimension(40, 20));

		labelNombreProveedor = new JLabel("Nombre: ");
		textFieldNombreProveedor = new JTextField();
		textFieldNombreProveedor.setPreferredSize(new Dimension(100, 20));
		
		labelNIFProveedor = new JLabel("NIF: ");
		textFieldNIFProveedor = new JTextField();
		textFieldNIFProveedor.setPreferredSize(new Dimension(100, 20));
		
		labelNumCuentaProveedor = new JLabel("Numero de cuenta: ");
		textFieldNumCuentaProveedor = new JTextField();
		textFieldNumCuentaProveedor.setPreferredSize(new Dimension(100, 20));
		
				

		selectors.add(labelidProveedor);
		selectors.add(textFieldidProveedor);
		selectors.add(labelNombreProveedor);
		selectors.add(textFieldNombreProveedor);
		selectors.add(labelNIFProveedor);
		selectors.add(textFieldNIFProveedor);
		selectors.add(labelNumCuentaProveedor);
		selectors.add(textFieldNumCuentaProveedor);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TProveedor t = new TProveedor(Integer.parseInt(textFieldidProveedor.getText()),textFieldNIFProveedor.getText(), textFieldNombreProveedor.getText(), textFieldNumCuentaProveedor.getText(), true);
				Controlador.getInstance().update(Evento.ACTUALIZAR_PROVEEDOR, t);
				MarcoActualizarProveedor.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				estado = 0;
				MarcoActualizarProveedor.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelar);
		
		emergent.add(buttonsPanel);
		
//		toptextPanel.setBackground(Color.white);
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		TProveedor tProveedor = (TProveedor) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProveedor.this), "Error en la actualizacion de Seccion");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProveedor.this), "El ID no existe en la base de datos");
			break;
		case Evento.NIF_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProveedor.this), "El NIF ya existe en la base de datos");
			break;
		default: 
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoActualizarProveedor.this), "Proveedor con ID: " + tProveedor.getId() + " actualizado correctamente" );	
			break;
		}	
		
	}
	
}