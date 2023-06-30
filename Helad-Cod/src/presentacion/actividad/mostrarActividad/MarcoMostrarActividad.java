package presentacion.actividad.mostrarActividad;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.actividad.TActividad;
import negocio.actividad.TCharla;
import negocio.actividad.TTaller;
import negocio.producto.TBatido;
import negocio.producto.THelado;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoMostrarActividad extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdActividad;
	private JButton botonMostrarActividad;
	private JButton botonCerrar;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	
	public MarcoMostrarActividad(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Mostrar Actividad");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Actividad que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JLabel labelId = new JLabel("Identificador: ");
		textFieldIdActividad = new JTextField();
		textFieldIdActividad.setPreferredSize(new Dimension(100,20));
		
		
		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIdActividad);
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
		
		botonMostrarActividad = new JButton("Mostrar");
		botonMostrarActividad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				
				if (textFieldIdActividad.getText().isEmpty()) id = 0;
				else id = Integer.valueOf(textFieldIdActividad.getText());
				
				Controlador.getInstance().update(Evento.MOSTRAR_ACTIVIDAD, id);
				MarcoMostrarActividad.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarActividad);
		
		botonCerrar = new JButton("Cancelar");
		botonCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCerrar);
		
		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void update(Integer evento, Object datos){
		
		if (evento < 0) {
			
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarActividad.this), "El ID no puede ser negativo");
			
		} else if(datos == null) {
			
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarActividad.this), "El ID no existe en la base de datos");
			
		} else {
			
			TActividad tActividad = (TActividad) datos;
			
			String text = "";
			text += "Id: " + tActividad.getId() + "\n";
			text += "Nombre: " + tActividad.getNombre() + "\n";
			text += "Fecha: " + tActividad.getFecha() + "\n";
			text += "Codigo: " + tActividad.getCodigo() + "\n";
			if (tActividad instanceof TTaller) {
				text+= "Taller\n";
				text += "Utensilios: " + ((TTaller) tActividad).getUtensilios()+"\n";
			} else {
				text+="Charla\n";
				text += "Nivel: " + ((TCharla) tActividad).getNivel() +"\n";
			}
			text += tActividad.getActivo() ? "ACTIVO" : "INACTIVO";
			contenedorDatos.setText(text);
			
		}
	}
}
