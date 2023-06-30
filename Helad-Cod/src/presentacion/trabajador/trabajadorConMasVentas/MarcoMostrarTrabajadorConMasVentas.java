package presentacion.trabajador.trabajadorConMasVentas;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import negocio.trabajador.TTrabajador;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
public class MarcoMostrarTrabajadorConMasVentas extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JLabel labelFechaIni;
	private JLabel labelFechaFin;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextField textFieldFechaIni;
	private JTextField textFieldFechaFin;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	public MarcoMostrarTrabajadorConMasVentas(){
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar trabajador con mas ventas en un tiempo");
		this.setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce la fecha(YYYY/MM/DD HH:mm:ss.sss) a buscar el trabajador con mas ventas");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);

		//---------------------------------------------------
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		//---------------------------------------------------
		
		labelFechaIni = new JLabel("Fecha inicial: ");
		textFieldFechaIni = new JTextField(timeStamp);
		textFieldFechaIni.setPreferredSize(new Dimension(150,20));
		
		labelFechaFin = new JLabel("Fecha final: ");
		textFieldFechaFin = new JTextField(timeStamp);
		textFieldFechaFin.setPreferredSize(new Dimension(150,20));
		
		JPanel selectors = new JPanel();
		selectors.add(labelFechaIni);
		selectors.add(textFieldFechaIni);
		selectors.add(labelFechaFin);
		selectors.add(textFieldFechaFin);
		emergent.add(selectors);
		
		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setPreferredSize(new Dimension(700, 200));
		
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scrollDatos);
		emergent.add(scrollPanel);
		
		JPanel buttonsPanel = new JPanel();
		
		botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 List<String> lista = new ArrayList<String>();
				 lista.add(textFieldFechaIni.getText());
				 lista.add(textFieldFechaFin.getText());
				 
				Controlador.getInstance().update(Evento.MOSTRAR_TRABAJADOR_CON_MAS_VENTAS, lista);
				MarcoMostrarTrabajadorConMasVentas.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrar);
		
		botonOcultar = new JButton("Cancelar");
		botonOcultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarTrabajadorConMasVentas.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonOcultar);
		
		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if(datos != null){ 
			TTrabajador tTrabajador=(TTrabajador) datos;
			String text = "";
			text += "Id: " + tTrabajador.getId() + "\n";
			text += "Nombre: " + tTrabajador.getNombre() + "\n";
			text += "DNI: " + tTrabajador.getDNI() + "\n";
			text += "Telefono: " + tTrabajador.getTelefono() + "\n";
			text += "Seccion: " + tTrabajador.getIdSeccion() + "\n";
			text += tTrabajador.getActive() ? "ACTIVO" : "INACTIVO";
			contenedorDatos.setText(text);	
		}
	}

}