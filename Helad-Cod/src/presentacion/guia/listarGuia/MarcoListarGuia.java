package presentacion.guia.listarGuia;

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

import negocio.guia.TGuia;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;


public class MarcoListarGuia extends JFrame implements IGUI  {
	private static final long serialVersionUID = 1L;
	private modeloListarGuia modeloProd;
	private JTable tablaListarGuia;
	private JScrollPane scrollListarGuia;
	private JTextField textFieldIDExpo;
	private JLabel labelIDExpo;
	
	
	
	
	public MarcoListarGuia(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Guias");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloProd = new modeloListarGuia();
		tablaListarGuia= new JTable(modeloProd);
		tablaListarGuia.setShowGrid(true);
		tablaListarGuia.getTableHeader().setReorderingAllowed(true);
		
		scrollListarGuia = new JScrollPane(tablaListarGuia);
		scrollListarGuia.setPreferredSize(new Dimension(700,300));
		
		selectors.add(scrollListarGuia);
		
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
				if(!textFieldIDExpo.getText().isEmpty()) 
					Controlador.getInstance().update(Evento.LISTAR_GUIA_POR_EXPOSICION, Integer.parseInt(textFieldIDExpo.getText()));
				else Controlador.getInstance().update(Evento.LISTAR_GUIA, null);
	
				MarcoListarGuia.this.setVisible(false);
			}		
		});
		labelIDExpo = new JLabel("Id de exposicion: ");
		textFieldIDExpo= new JTextField();
		textFieldIDExpo.setPreferredSize(new Dimension(40, 20));
		
		buttonsPanel.add(labelIDExpo);
		buttonsPanel.add(textFieldIDExpo);
		buttonsPanel.add(botonCancelar);
		buttonsPanel.add(botonListar);
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public class SortObrasPorId implements Comparator<TGuia> {

		@Override
		public int compare(TGuia g1, TGuia g2) {
			if (g1.getId() == g2.getId())
				return 0;
			else if (g1.getId() < g2.getId())
				return -1;
			else
				return 1;
		}
	}

	@Override
	public void update(Integer evento, Object datos) {
		@SuppressWarnings("unchecked")
		ArrayList<TGuia> listaS2 = (ArrayList<TGuia>) datos;
		listaS2.sort(new SortObrasPorId());
		modeloProd.update(listaS2);
		
	}
}
