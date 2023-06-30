package presentacion.producto.productoMasVendido;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import negocio.producto.TBatido;
import negocio.producto.THelado;
import negocio.producto.TProducto;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MarcoMostrarProductoMasVendido extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldFechaIni;
	private JTextField textFieldFechaFin;
	private JButton botonMostrar;
	private JButton botonOcultar;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;
	private JLabel labelFechaIni;
	private JLabel labelFechaFin;

	public MarcoMostrarProductoMasVendido() {
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar Producto Mas Vendido");
		this.setLocation(420, 400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce la fecha(YYYY/MM/DD HH:mm:ss.sss) para buscar el producto mas vendido");
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
				
				Controlador.getInstance().update(Evento.MOSTRAR_PRODUCTO_MAS_VENDIDO, lista);
				MarcoMostrarProductoMasVendido.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrar);
		
		botonOcultar = new JButton("Cancelar");
		botonOcultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarProductoMasVendido.this.setVisible(false);
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
		 if (datos != null) {
		
			 ArrayList<TProducto> listaProductos = (ArrayList<TProducto>) datos;
				String text = "";
			 
			 for(TProducto tProducto:listaProductos){
				
					text += "Id: " + tProducto.getId() + "\n";
					text += "Nombre del Producto: " + tProducto.getNombre() + "\n";
					text += "Sabor: " + tProducto.getSabor() + "\n";
					text += "Tipo: " + tProducto.getTipo() + "\n";
					if (tProducto.getTipo().equalsIgnoreCase("batido")) {
						text += "Tamanio: " + ((TBatido) tProducto).getTamanio()+"\n";
					} else {
						text += "Envase: " + ((THelado) tProducto).getEnvase() +"\n";
					}
					text += "Id del proveedor: " + tProducto.getIdProveedor() + "\n";
					text += tProducto.getActivo() ? "ACTIVO\n" : "INACTIVO\n";
					text += "\n";
			 }
		
			contenedorDatos.setText(text);
		}

	}

}