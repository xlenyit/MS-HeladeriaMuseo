package presentacion.seccion.altaSeccion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.seccion.TSeccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
public class MarcoAltaSeccion extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelNombreSeccion;
	private JLabel labelSueldoSeccion;
	private JButton bottonAltaSeccion;
	private JTextField textFieldNombreSeccion;
	private JTextField textFieldSueldoSeccion;
	private JButton bottonCancelarAltaSeccion;
	
	public MarcoAltaSeccion(){
		initGUI();
	}
	
	private void initGUI() {
		this.setTitle("Alta Seccion");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
	
		
		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Rellena los campos para el Alta de una nueva Seccion");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);
		
		JPanel selectors = new JPanel();

		labelNombreSeccion = new JLabel("Nombre de la seccion: ");
		textFieldNombreSeccion = new JTextField();
		textFieldNombreSeccion.setPreferredSize(new Dimension(100, 20));
		
		labelSueldoSeccion = new JLabel("Sueldo: ");
		textFieldSueldoSeccion = new JTextField();
		textFieldSueldoSeccion.setPreferredSize(new Dimension(100, 20));

		selectors.add(labelNombreSeccion);
		selectors.add(textFieldNombreSeccion);
		selectors.add(labelSueldoSeccion);
		selectors.add(textFieldSueldoSeccion);

		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		bottonAltaSeccion = new JButton("Aceptar");
		bottonAltaSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double sueldo = Double.parseDouble(textFieldSueldoSeccion.getText());
				TSeccion tSeccion = new TSeccion(-1,textFieldNombreSeccion.getText(), sueldo, true);

				Controlador.getInstance().update(Evento.ALTA_SECCION,tSeccion);
				MarcoAltaSeccion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(bottonAltaSeccion);
		
		bottonCancelarAltaSeccion = new JButton("Cancelar");
		bottonCancelarAltaSeccion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoAltaSeccion.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(bottonCancelarAltaSeccion);
		
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
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaSeccion.this), "Error en el Alta de la Seccion");
			break;
		case Evento.NOMBRE_EXISTENTE:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaSeccion.this), "El nombre ya existe en la base de datos");
			break;
		default:
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoAltaSeccion.this), "Seccion creada con ID: " + evento);	
			break;
		}
		
	}
}