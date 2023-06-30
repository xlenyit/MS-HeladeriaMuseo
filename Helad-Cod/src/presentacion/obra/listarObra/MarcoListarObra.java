package presentacion.obra.listarObra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import negocio.obra.TObra;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;


public class MarcoListarObra extends JFrame implements IGUI  {
	private static final long serialVersionUID = 1L;
	private modeloListarObra modeloProd;
	private JTable tablaListarObra;
	private JScrollPane scrollListarObra;
	private JTextField textFieldIdExposicion;
	private JLabel labelIdExposicion;
	
	
	public MarcoListarObra(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Obras");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloProd = new modeloListarObra();
		tablaListarObra= new JTable(modeloProd);
		tablaListarObra.setShowGrid(true);
		tablaListarObra.getTableHeader().setReorderingAllowed(true);
		
		scrollListarObra = new JScrollPane(tablaListarObra);
		scrollListarObra.setPreferredSize(new Dimension(700,300));
		
		selectors.add(scrollListarObra);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		
		
		JButton botonListar= new JButton("Listar");
		botonListar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFieldIdExposicion.getText().isEmpty()) Controlador.getInstance().update(Evento.LISTAR_OBRA, null);
				else if(!textFieldIdExposicion.getText().isEmpty()) Controlador.getInstance().update(Evento.LISTAR_OBRA_POR_EXPOSICION, Integer.parseInt(textFieldIdExposicion.getText()));
				MarcoListarObra.this.setVisible(false);
			}
			
		});
		
		labelIdExposicion = new JLabel("Id de la Exposicion: ");
		textFieldIdExposicion = new JTextField();
		textFieldIdExposicion.setPreferredSize(new Dimension(100, 20));
		
		buttonsPanel.add(labelIdExposicion);
		buttonsPanel.add(textFieldIdExposicion);
		buttonsPanel.add(botonCancelar);
		buttonsPanel.add(botonListar);
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public class SortObrasPorId implements Comparator<TObra> {

		@Override
		public int compare(TObra t1, TObra t2) {
			if (t1.getId() == t2.getId())
				return 0;
			else if (t1.getId() < t2.getId())
				return -1;
			else
				return 1;
		}
	}

	@Override
	public void update(Integer evento, Object datos) {
		@SuppressWarnings("unchecked")
		ArrayList<TObra> listaS2 = (ArrayList<TObra>) datos;
		listaS2.sort(new SortObrasPorId());
		modeloProd.update(listaS2);
		
	}
}
