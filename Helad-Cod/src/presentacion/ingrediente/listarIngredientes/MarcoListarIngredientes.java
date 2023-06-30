package presentacion.ingrediente.listarIngredientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import negocio.ingrediente.TIngrediente;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;


public class MarcoListarIngredientes extends JFrame implements IGUI  {
	private static final long serialVersionUID = 1L;
	private modeloListarIngredientes modeloProd;
	private JTable tablaListarIngredientes;
	private JScrollPane scrollListarIngredientes;
	
	
	public MarcoListarIngredientes(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Ingredientes");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloProd = new modeloListarIngredientes();
		tablaListarIngredientes= new JTable(modeloProd);
		tablaListarIngredientes.setShowGrid(true);
		tablaListarIngredientes.getTableHeader().setReorderingAllowed(true);
		
		scrollListarIngredientes = new JScrollPane(tablaListarIngredientes);
		scrollListarIngredientes.setPreferredSize(new Dimension(700,300));
		
		selectors.add(scrollListarIngredientes);
		
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
				Controlador.getInstance().update(Evento.LISTAR_INGREDIENTE, null);
				MarcoListarIngredientes.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelar);
		buttonsPanel.add(botonListar);
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public class SortIngredientesPorId implements Comparator<TIngrediente> {

		@Override
		public int compare(TIngrediente t1, TIngrediente t2) {
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
		ArrayList<TIngrediente> listaS2 = (ArrayList<TIngrediente>) datos;
		listaS2.sort(new SortIngredientesPorId());
		modeloProd.update(listaS2);
		
	}
}
