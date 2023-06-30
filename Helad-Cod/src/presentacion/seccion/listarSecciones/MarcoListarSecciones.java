package presentacion.seccion.listarSecciones;

import javax.swing.JFrame;
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

import negocio.seccion.TSeccion;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoListarSecciones extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private modeloListarSecciones modeloTabla;
	private JTable tablaListar;
	private JScrollPane scrollTabla;

	public MarcoListarSecciones(){
		initGUI();
	}
	private void initGUI() {

		setTitle("Lista de Secciones");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloTabla = new modeloListarSecciones();
		tablaListar = new JTable(modeloTabla);
		tablaListar.setShowGrid(true);
		tablaListar.getTableHeader().setReorderingAllowed(true);
		
		scrollTabla = new JScrollPane(tablaListar);
		scrollTabla.setPreferredSize(new Dimension(700,300));
		
		selectors.add(scrollTabla);
		
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
				Controlador.getInstance().update(Evento.LISTAR_SECCION, null);
				MarcoListarSecciones.this.setVisible(false);
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
	
	public class SortSeccionesPorId implements Comparator<TSeccion> {

		@Override
		public int compare(TSeccion t1, TSeccion t2) {
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
		Set<TSeccion> listaS = new HashSet<TSeccion>();
		listaS = (Set<TSeccion>) datos;
		ArrayList<TSeccion> listaS2 = new ArrayList<>(listaS);
		listaS2.sort(new SortSeccionesPorId());
		modeloTabla.update(listaS2);
	}
}
