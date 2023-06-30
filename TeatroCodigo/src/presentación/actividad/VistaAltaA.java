package presentación.actividad;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.actividad.TActCultural;
import negocio.actividad.TActividad;
import negocio.actividad.TRepresentacion;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;


public class VistaAltaA extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;


	public VistaAltaA() {

		// COMUN
		setTitle("AÑADIR ACTIVIDAD");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel lDuracion = new JLabel("Duracion:");
		final JTextField tDuracion = new JTextField(20);
		JLabel lEntradasDisponibles = new JLabel("Entradas disponibles:");
		final JTextField tEntradasDisponibles = new JTextField(20);
		JLabel lFechaInicio = new JLabel("Fecha de inicio (YYYY-MM-DD):");
		final JTextField tFechaInicio = new JTextField(20);
		JLabel lFechaFin = new JLabel("Fecha de fin (YYYY-MM-DD):");
		final JTextField tFechaFin = new JTextField(20);
		JLabel lHora = new JLabel("Hora (HH:MM:SS):");
		final JTextField tHora = new JTextField(20);
		JLabel lPrecio = new JLabel("Precio:");
		final JTextField tPrecio = new JTextField(20);
		ButtonGroup bg = new ButtonGroup();
		JRadioButton cultural = new JRadioButton("Actividad cultural", true);
		JRadioButton representacion = new JRadioButton("Representacion");
		bg.add(cultural);
		bg.add(representacion);

		// CULTURAL
		JPanel panelCultural = new JPanel();
		panelCultural.setLayout(new BoxLayout(panelCultural, BoxLayout.Y_AXIS));

		JLabel lTitulo = new JLabel("Titulo:");
		final JTextField tTitulo = new JTextField(50);

		panelCultural.add(lTitulo);
		panelCultural.add(tTitulo);

		// REPRESENTACIÓN
		JPanel panelRepresentacion = new JPanel();
		panelRepresentacion.setLayout(new BoxLayout(panelRepresentacion, BoxLayout.Y_AXIS));

		JLabel lCompania = new JLabel("Compania:");
		final JTextField tCompania = new JTextField(20);
		JLabel lTemporada = new JLabel("Temporada:");
		final JTextField tTemporada = new JTextField(20);
		JLabel lObra = new JLabel("Obra:");
		final JTextField tObra = new JTextField(20);

		panelRepresentacion.add(lCompania);
		panelRepresentacion.add(tCompania);
		panelRepresentacion.add(lTemporada);
		panelRepresentacion.add(tTemporada);
		panelRepresentacion.add(lObra);
		panelRepresentacion.add(tObra);

		// COMUN
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// COMUN
				setVisible(false);
				try {
					TActividad tac = null;
					int duracion = Integer.parseInt(tDuracion.getText());
					int entradasDisponibles = Integer.parseInt(tEntradasDisponibles.getText());
					Date fechaInicio = Date.valueOf(tFechaInicio.getText());
					Date fechaFin = Date.valueOf(tFechaFin.getText());
					Time hora = Time.valueOf(tHora.getText());
					double precio = Double.parseDouble(tPrecio.getText());
					if (cultural.isSelected()) {
						String titulo = tTitulo.getText();
						tac = new TActCultural(0, titulo, entradasDisponibles, fechaInicio, fechaFin, hora, duracion, precio, true);
					} else if (representacion.isSelected()) {
						int obra = Integer.parseInt(tObra.getText());
						int compania = Integer.parseInt(tCompania.getText());
						int temporada = Integer.parseInt(tTemporada.getText());
						tac = new TRepresentacion(0, entradasDisponibles, precio, fechaInicio, fechaFin, hora, duracion, obra,
								compania, temporada, true);
					}
					Controlador.getInstance().accion(Evento.ALTA_ACTIVIDAD, tac);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});
		
		cancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});

		panel.add(lDuracion);
		panel.add(tDuracion);
		panel.add(lEntradasDisponibles);
		panel.add(tEntradasDisponibles);
		panel.add(lFechaInicio);
		panel.add(tFechaInicio);
		panel.add(lFechaFin);
		panel.add(tFechaFin);
		panel.add(lHora);
		panel.add(tHora);
		panel.add(lPrecio);
		panel.add(tPrecio);
		panel.add(cultural);
		panel.add(representacion);
		// CARDS
		JPanel tipo = new JPanel(new CardLayout());

		tipo.add(panelCultural, "PanelCultural");
		tipo.add(panelRepresentacion, "PanelRepresentacion");

		panel.add(tipo);

		JPanel infPanel = new JPanel();
		infPanel.add(aceptar);
		infPanel.add(cancelar);

		panel.add(infPanel);

		pack();

		getContentPane().add(panel);

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (tipo.getLayout());
				if (e.getSource() == cultural) {
					cl.show(tipo, "PanelCultural");
				} else if (e.getSource() == representacion) {
					cl.show(tipo, "PanelRepresentacion");
				}
			}

		};

		cultural.addActionListener(actionListener);
		representacion.addActionListener(actionListener);

	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_ALTA_ACTIVIDAD_OK) 
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente la actividad con id " + (Integer) datos);
		else if(evento == Evento.RES_ALTA_ACTIVIDAD_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido crear la actividad");
	}
}