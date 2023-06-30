package presentacion.producto.mostrarProducto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcoMostrarProducto extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIDProducto;
	private JButton botonMostrarProducto;
	private JButton botonOcultarProducto;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public MarcoMostrarProducto() {
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar Producto");
		this.setLocation(420, 400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Producto que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);

		JLabel labelId = new JLabel("Identificador: ");
		textFieldIDProducto = new JTextField();
		textFieldIDProducto.setPreferredSize(new Dimension(100, 20));

		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIDProducto);
		emergent.add(selectors);

		contenedorDatos = new JTextArea();
		contenedorDatos.setEditable(false);
		contenedorDatos.setBounds(25, 75, 500, 300);
		scrollDatos = new JScrollPane(contenedorDatos);
		scrollDatos.setPreferredSize(new Dimension(300, 200));

		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scrollDatos);
		emergent.add(scrollPanel);

		JPanel buttonsPanel = new JPanel();

		botonMostrarProducto = new JButton("Mostrar");
		botonMostrarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if (textFieldIDProducto.getText().isEmpty())
					id = 0;
				else
					id = Integer.valueOf(textFieldIDProducto.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_PRODUCTO, id);
				MarcoMostrarProducto.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarProducto);

		botonOcultarProducto = new JButton("Cancelar");
		botonOcultarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarProducto.this.setVisible(false);
			}

		});
		buttonsPanel.add(botonOcultarProducto);

		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarProducto.this),
					"El ID no puede ser negativo");
		} else if (datos == null) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarProducto.this),
					"El ID no existe en la base de datos");
		} else {
			TProducto tProducto = (TProducto) datos;
			String text = "";
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
			contenedorDatos.setText(text);
		}

	}

}