package presentacion.actividad.listarActividad;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.actividad.TActividad;
import negocio.seccion.TSeccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.obra.listarObra.MarcoListarObra;

public class MarcoListarActividad extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private modeloListarActividad modeloTabla;
	private JTable tablaListar;
	private JScrollPane scrollTabla;
	private JLabel labelUsuario;
	private JTextField textFieldUsuario;

	public MarcoListarActividad(){
		initGUI();
	}
	private void initGUI() {

		setTitle("Lista de Actividades");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloTabla = new modeloListarActividad();
		tablaListar = new JTable(modeloTabla);
		tablaListar.setShowGrid(true);
		tablaListar.getTableHeader().setReorderingAllowed(true);
		
		scrollTabla = new JScrollPane(tablaListar);
		scrollTabla.setPreferredSize(new Dimension(700,300));
		
		selectors.add(scrollTabla);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		labelUsuario = new JLabel("Id del Usuario: ");
		textFieldUsuario = new JTextField();
		textFieldUsuario.setPreferredSize(new Dimension(100, 20));
		buttonsPanel.add(labelUsuario);
		buttonsPanel.add(textFieldUsuario);
		

		JButton botonListar = new JButton("Listar");
		botonListar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFieldUsuario.getText().isEmpty()) Controlador.getInstance().update(Evento.LISTAR_ACTIVIDAD, null);
				else if(!textFieldUsuario.getText().isEmpty()) Controlador.getInstance().update(Evento.LISTAR_ACTIVIDAD_POR_USUARIO, Integer.parseInt(textFieldUsuario.getText()));
				
				MarcoListarActividad.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonListar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
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
	
	public class SortActividadesPorId implements Comparator<TActividad> {

		@Override
		public int compare(TActividad t1, TActividad t2) {
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
		ArrayList<TActividad> listaS = new ArrayList<TActividad>();
		listaS = (ArrayList<TActividad>) datos;
		listaS.sort(new SortActividadesPorId());
		modeloTabla.update(listaS);
	}
}
