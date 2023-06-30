package presentacion.proveedor.altaProveedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.proveedor.TProveedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
public class MarcoAltaProveedor extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JLabel labelNUMCuenta;
	private JLabel labelNombre;
	private JLabel labelNIFProveedor;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JTextField textFieldNombreProveedor;
	private JTextField textFieldNIF;
	private JTextField textFieldNUMCuenta;
	
	public MarcoAltaProveedor(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Proveedor");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de un nuevo Proveedor");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelNombre = new JLabel("Nombre: ");
		textFieldNombreProveedor = new JTextField();
		textFieldNombreProveedor.setPreferredSize(new Dimension(100, 20));
		
		labelNIFProveedor = new JLabel("NIF: ");
		textFieldNIF = new JTextField();
		textFieldNIF.setPreferredSize(new Dimension(100, 20));
		
		labelNUMCuenta = new JLabel("Numero de cuenta: ");
		textFieldNUMCuenta = new JTextField();
		textFieldNUMCuenta.setPreferredSize(new Dimension(100, 20));
		
				

		selectors.add(labelNombre);
		selectors.add(textFieldNombreProveedor);
		selectors.add(labelNIFProveedor);
		selectors.add(textFieldNIF);
		selectors.add(labelNUMCuenta);
		selectors.add(textFieldNUMCuenta);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TProveedor t = new TProveedor(0,textFieldNIF.getText(),textFieldNombreProveedor.getText(),textFieldNUMCuenta.getText() ,true);
				
				Controlador.getInstance().update(Evento.ALTA_PROVEEDOR, t);
				MarcoAltaProveedor.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonAceptar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaProveedor.this.setVisible(false);
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
		switch(evento){
		case -1:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProveedor.this), "Error en el Alta del Proveedor");
			break;
		case Evento.NIF_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProveedor.this), "El NIF ya existe en la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaProveedor.this), "Proveedor creado con ID: " + evento);	
			break;
		}
	}
}