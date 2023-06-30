package presentacion.producto.listarProductos;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import negocio.producto.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcoListarProductos extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private modeloListarProductos modeloProd;
	private JTable tablaListarProductos;
	private JLabel labelIdProveedor;
	private JTextField textFieldIdProveedor;
	private JScrollPane scrollListarProductos;
	
	
	public MarcoListarProductos(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Productos");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		modeloProd = new modeloListarProductos();
		tablaListarProductos = new JTable(modeloProd);
		tablaListarProductos.setShowGrid(true);
		tablaListarProductos.getTableHeader().setReorderingAllowed(true);
		
		scrollListarProductos = new JScrollPane(tablaListarProductos);
		scrollListarProductos.setPreferredSize(new Dimension(700,300));
		
		labelIdProveedor = new JLabel("Id del proveedor: ");
		textFieldIdProveedor = new JTextField();
		textFieldIdProveedor.setPreferredSize(new Dimension(100, 20));

		
		selectors.add(scrollListarProductos);
		
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
				int id;
				if(textFieldIdProveedor.getText().isEmpty()) id = 0;
				else id = Integer.parseInt(textFieldIdProveedor.getText());
				Controlador.getInstance().update(Evento.LISTAR_PRODUCTO, id);
				MarcoListarProductos.this.setVisible(false);
			}
			
		});
		buttonsPanel.add(labelIdProveedor);
		buttonsPanel.add(textFieldIdProveedor);
		buttonsPanel.add(botonCancelar);
		buttonsPanel.add(botonListar);
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public class SortProductosPorId implements Comparator<TProducto> {

		@Override
		public int compare(TProducto t1, TProducto t2) {
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
		ArrayList<TProducto> listaS2 = (ArrayList<TProducto>) datos;
		listaS2.sort(new SortProductosPorId());
		modeloProd.update(listaS2);
		
	}
}
