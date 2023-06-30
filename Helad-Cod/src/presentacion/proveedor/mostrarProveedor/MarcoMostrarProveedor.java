package presentacion.proveedor.mostrarProveedor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.proveedor.TProveedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MarcoMostrarProveedor extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JButton botonMostrar;
	private JButton botonCancel;
	private JTextField textFieldID;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public MarcoMostrarProveedor(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Mostrar Proveedor");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Proveedor que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JLabel labelId = new JLabel("Identificador: ");
		textFieldID = new JTextField();
		textFieldID.setPreferredSize(new Dimension(100,20));
		
		
		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldID);
		emergent.add(selectors);
		
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setPreferredSize(new Dimension(300, 200));
		
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scrollDatos);
		emergent.add(scrollPanel);
		
		JPanel buttonsPanel = new JPanel();
		
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if(textFieldID.getText().isEmpty()) id = 0;
				else id = Integer.valueOf(textFieldID.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_PROVEEDOR, id);
				MarcoMostrarProveedor.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrar);
		
		botonCancel = new JButton("Cancelar");
		botonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarProveedor.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancel);
		
		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarProveedor.this), "El ID no puede ser negativo");
		}else if(datos == null){
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarProveedor.this), "El ID no existe en la base de datos");
		}else{
			TProveedor tProveedor = (TProveedor) datos;
			String text = "";
			text += "Id: " + tProveedor.getId() + "\n";
			text += "Nombre: " + tProveedor.getNombre() + "\n";
			text += "Sueldo: " + tProveedor.getNIF() + "\n";
			text += "Numero de cuenta: " + tProveedor.getNumCuenta() + "\n";
			text += tProveedor.getActivo() ? "ACTIVO" : "INACTIVO";
			contenedorDatos.setText(text);
		}
	}
}