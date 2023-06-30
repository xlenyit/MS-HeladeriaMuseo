package presentacion.ingrediente.mostrarIngrediente;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import negocio.ingrediente.TIngrediente;
import negocio.ingrediente.TLiquido;
import negocio.ingrediente.TSolido;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoMostrarIngrediente extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIDIngrediente;
	private JButton botonMostrarIngrediente;
	private JButton botonOcultarIngrediente;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public MarcoMostrarIngrediente() {
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar Ingrediente");
		this.setLocation(420, 400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Ingrediente que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);

		JLabel labelId = new JLabel("Identificador: ");
		textFieldIDIngrediente= new JTextField();
		textFieldIDIngrediente.setPreferredSize(new Dimension(100, 20));

		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIDIngrediente);
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

		botonMostrarIngrediente = new JButton("Mostrar");
		botonMostrarIngrediente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if (textFieldIDIngrediente.getText().isEmpty())
					id = 0;
				else
					id = Integer.valueOf(textFieldIDIngrediente.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_INGREDIENTE, id);
				MarcoMostrarIngrediente.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarIngrediente);

		botonOcultarIngrediente= new JButton("Cancelar");
		botonOcultarIngrediente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarIngrediente.this.setVisible(false);
			}

		});
		buttonsPanel.add(botonOcultarIngrediente);

		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarIngrediente.this),
					"El ID no puede ser negativo");
		} else if (datos == null) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarIngrediente.this),
					"El ID no existe en la base de datos");
		} else {
			TIngrediente tIngrediente = (TIngrediente) datos;
			String text = "";
			text += "Id: " + tIngrediente.getId() + "\n";
			text += "Nombre del Producto: " + tIngrediente.getNombre() + "\n";
			text += "Codigo: " + tIngrediente.getCodigo() + "\n";
			text += "Tipo: " + tIngrediente.getTipo() + "\n";
			if (tIngrediente.getTipo().equalsIgnoreCase("liquido")) {
				text += "Espesor: " + ((TLiquido) tIngrediente).getEspesor()+"\n";
			} else {
				text += "Envase: " + ((TSolido) tIngrediente).getTamanio() +"\n";
			}
			text += tIngrediente.getActivo() ? "ACTIVO\n" : "INACTIVO\n";
			contenedorDatos.setText(text);
		}

	}

}
