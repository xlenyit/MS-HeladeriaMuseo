package presentacion.producto.bajaProducto;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
public class MarcoBajaProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JButton botonCancelBaja;
	private JLabel labelIDProducto;
	private JTextField textFieldProducto;
	private JButton botonCerrarBaja;
	private int id = -1;
	
	public MarcoBajaProducto(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Baja Producto");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Producto al que dar la Baja");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelIDProducto = new JLabel("Id: ");
		textFieldProducto = new JTextField();
		textFieldProducto.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelIDProducto);
		selectors.add(textFieldProducto);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonCancelBaja = new JButton("Aceptar");
		botonCancelBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = Integer.parseInt(textFieldProducto.getText());
				Controlador.getInstance().update(Evento.BAJA_PRODUCTO, id);
				MarcoBajaProducto.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelBaja);
		
		botonCerrarBaja = new JButton("Cancelar");
		botonCerrarBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoBajaProducto.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCerrarBaja);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		int idT = (int) datos;
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProducto.this), "Error en la baja del producto");
			break;
		case Evento.ID_NO_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProducto.this), "El ID introducido no existe");
			break;
		case Evento.YA_INACTIVO:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProducto.this), "El producto introducido ya esta dado de baja");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoBajaProducto.this), "Usuario con ID: " + idT + " dado de baja");
			break;
			
		}
	}
}