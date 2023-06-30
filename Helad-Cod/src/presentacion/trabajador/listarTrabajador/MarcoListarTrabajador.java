package presentacion.trabajador.listarTrabajador;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.trabajador.TTrabajador;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.JScrollPane;

public class MarcoListarTrabajador extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JButton botonListar;
	private JButton botonCerrar;
	private modeloListarTrabajador tablaListarTrabajadores;
	private JScrollPane scrollTablaListarTrabajadores;
	private JTable tablaTrabajadores;
	private JTextField textFieldIdSeccion;
	private JLabel labelIdSeccion;
	
	public MarcoListarTrabajador(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Trabajadores");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		tablaListarTrabajadores = new modeloListarTrabajador();
		tablaTrabajadores = new JTable(tablaListarTrabajadores);
		tablaTrabajadores.setShowGrid(true);
		tablaTrabajadores.getTableHeader().setReorderingAllowed(true);
		scrollTablaListarTrabajadores = new JScrollPane(tablaTrabajadores);
		scrollTablaListarTrabajadores.setPreferredSize(new Dimension(700,300));
		
		
				
		
		selectors.add(scrollTablaListarTrabajadores);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		botonListar = new JButton("Listar");
		botonListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;
				if(textFieldIdSeccion.getText().isEmpty()) id = 0;
				else id = Integer.parseInt(textFieldIdSeccion.getText());
				Controlador.getInstance().update(Evento.LISTAR_TRABAJADOR, id);
				MarcoListarTrabajador.this.setVisible(false);
			}
			
		});
		botonCerrar = new JButton("Cancelar");
		botonCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		labelIdSeccion = new JLabel("Id de la seccion: ");
		textFieldIdSeccion = new JTextField();
		textFieldIdSeccion.setPreferredSize(new Dimension(100, 20));
		
		buttonsPanel.add(labelIdSeccion);
		buttonsPanel.add(textFieldIdSeccion);
		buttonsPanel.add(botonCerrar);
		buttonsPanel.add(botonListar);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	
	public class SortTrabajadoresPorId implements Comparator<TTrabajador> {

		@Override
		public int compare(TTrabajador t1, TTrabajador t2) {
			if (t1.getId() == t2.getId())
				return 0;
			else if (t1.getId() < t2.getId())
				return -1;
			else
				return 1;
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public void update(Integer evento, Object datos) {
		Set<TTrabajador> listaS = new HashSet<TTrabajador>();
		listaS = (Set<TTrabajador>) datos;
		ArrayList<TTrabajador> listaS2 = new ArrayList<>(listaS);
		listaS2.sort(new SortTrabajadoresPorId());
		tablaListarTrabajadores.update(listaS2);
	}
	
}