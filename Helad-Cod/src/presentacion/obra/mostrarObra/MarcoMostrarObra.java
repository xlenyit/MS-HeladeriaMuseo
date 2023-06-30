package presentacion.obra.mostrarObra;

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

import negocio.obra.TEscultura;
import negocio.obra.TObra;
import negocio.obra.TPintura;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoMostrarObra extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIDObra;
	private JButton botonMostrarObra;
	private JButton botonOcultarObra;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public MarcoMostrarObra() {
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar Obra");
		this.setLocation(420, 400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id de la Obra que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);

		JLabel labelId = new JLabel("Identificador: ");
		textFieldIDObra= new JTextField();
		textFieldIDObra.setPreferredSize(new Dimension(100, 20));

		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIDObra);
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

		botonMostrarObra = new JButton("Mostrar");
		botonMostrarObra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if (textFieldIDObra.getText().isEmpty())
					id = 0;
				else
					id = Integer.valueOf(textFieldIDObra.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_OBRA, id);
				MarcoMostrarObra.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarObra);

		botonOcultarObra= new JButton("Cancelar");
		botonOcultarObra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarObra.this.setVisible(false);
			}

		});
		buttonsPanel.add(botonOcultarObra);

		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarObra.this),
					"El ID no puede ser negativo");
		} else if (datos == null) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarObra.this),
					"El ID no existe en la base de datos");
		} else {
			TObra tObra = (TObra) datos;
			String text = "";
			text += "Id: " + tObra.getId() + "\n";
			text += "Nombre de la Obra: " + tObra.getNombre() + "\n";
			text += "Autor: " + tObra.getAutor() + "\n";
			text += "Coste: " + tObra.getCoste() + "\n";
			if (tObra instanceof TPintura) {
				text += "Pintura\n";
				text += "Tipo de Pintura: " + ((TPintura) tObra).getTipo()+"\n";
			} else {
				text+="Escultura\n";
				text += "Material: " + ((TEscultura) tObra).getMaterial() +"\n";
			}
			text += tObra.getActivo() ? "ACTIVO\n" : "INACTIVO\n";
			contenedorDatos.setText(text);
		}

	}

}
