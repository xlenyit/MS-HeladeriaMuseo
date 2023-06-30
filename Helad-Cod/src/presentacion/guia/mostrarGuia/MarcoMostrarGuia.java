package presentacion.guia.mostrarGuia;

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

import negocio.guia.TGuia;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;

public class MarcoMostrarGuia extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIDGuia;
	private JButton botonMostrarGuia;
	private JButton botonOcultarGuia;
	private JTextArea contenedorDatos;
	private JScrollPane scrollDatos;

	public MarcoMostrarGuia() {
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Mostrar Guia");
		this.setLocation(420, 400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);

		JPanel toptextPanel = new JPanel();
		JLabel toptext = new JLabel("Introduce el Id del Guia que desee Mostrar");
		toptextPanel.add(toptext);
		emergent.add(toptextPanel);

		JLabel labelId = new JLabel("Identificador: ");
		textFieldIDGuia= new JTextField();
		textFieldIDGuia.setPreferredSize(new Dimension(100, 20));

		JPanel selectors = new JPanel();
		selectors.add(labelId);
		selectors.add(textFieldIDGuia);
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

		botonMostrarGuia = new JButton("Mostrar");
		botonMostrarGuia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id;
				if (textFieldIDGuia.getText().isEmpty())
					id = 0;
				else
					id = Integer.valueOf(textFieldIDGuia.getText());
				Controlador.getInstance().update(Evento.MOSTRAR_GUIA, id);
				MarcoMostrarGuia.this.setVisible(false);
			}
		});
		buttonsPanel.add(botonMostrarGuia);

		botonOcultarGuia= new JButton("Cancelar");
		botonOcultarGuia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MarcoMostrarGuia.this.setVisible(false);
			}

		});
		buttonsPanel.add(botonOcultarGuia);

		emergent.add(buttonsPanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void update(Integer evento, Object datos) {
		if (evento < 0) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarGuia.this),
					"El ID no puede ser negativo");
		} else if (datos == null) {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(MarcoMostrarGuia.this),
					"El ID no existe en la base de datos");
		} else {
			TGuia tGuia = (TGuia) datos;
			String text = "";
			text += "Id: " + tGuia.getId() + "\n";
			text += "Nombre del Guia: " + tGuia.getNombre() + "\n";
			if(tGuia.getActivo()) text += "ACTIVO";
			else text += "INACTIVO";
			contenedorDatos.setText(text);
		}

	}

}
