package presentacion.proveedor.listarProveedores;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import negocio.proveedor.TProveedor;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MarcoListarProveedores extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private modeloListarProveedores modeloProveedores;
	private JTable tablaListarProveedores;
	private JScrollPane scrollTablaListarProveedores;
	
	public MarcoListarProveedores (){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Secciones");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloProveedores = new modeloListarProveedores();
		tablaListarProveedores = new JTable(modeloProveedores);
		tablaListarProveedores.setShowGrid(true);
		tablaListarProveedores.getTableHeader().setReorderingAllowed(true);
		
		scrollTablaListarProveedores = new JScrollPane(tablaListarProveedores);
		scrollTablaListarProveedores.setPreferredSize(new Dimension(700,300));
		
		selectors.add(scrollTablaListarProveedores);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		buttonsPanel.add(botonCancelar);
		
		JButton botonListar = new JButton("Listar");
		botonListar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().update(Evento.LISTAR_PROVEEDOR, null);
				MarcoListarProveedores.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(botonListar);
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public class SortProveedoresPorId implements Comparator<TProveedor	> {

		@Override
		public int compare(TProveedor t1, TProveedor t2) {
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
		Set<TProveedor> listaS = new HashSet<TProveedor>();
		listaS = (Set<TProveedor>) datos;
		ArrayList<TProveedor> listaS2 = new ArrayList<>(listaS);
		listaS2.sort(new SortProveedoresPorId());
		modeloProveedores.update(listaS2);		
	}
	
}