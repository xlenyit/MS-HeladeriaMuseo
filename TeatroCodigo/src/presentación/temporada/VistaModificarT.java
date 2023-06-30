
package presentación.temporada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.temporada.TTemporada;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarT extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaModificarT() {
		setTitle("MODIFICAR TEMPORADA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de temporada:");
		final JTextField tid = new JTextField(20);
		JLabel lNum = new JLabel("Numero temporada:");
		final JTextField tNum = new JTextField(20);
		JLabel lCalificacion = new JLabel("Calificación:");
		final JTextField tCalificacion = new JTextField(20);
		JLabel lFechaInicio = new JLabel("Fecha de inicio (YYYY-MM-DD):");
		final JTextField tFechaInicio = new JTextField(20);
		JLabel lFechaFin = new JLabel("Fecha de fin (YYYY-MM-DD):");
		final JTextField tFechaFin = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(lNum);
		panel.add(tNum);
		panel.add(lCalificacion);
		panel.add(tCalificacion);
		panel.add(lFechaInicio);
		panel.add(tFechaInicio);
		panel.add(lFechaFin);
		panel.add(tFechaFin);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idT = Integer.parseInt(tid.getText());
					int numT = Integer.parseInt(tNum.getText());
					double calificacion = Double.parseDouble(tCalificacion.getText());
					Date fechaInicio = Date.valueOf(tFechaInicio.getText());
					Date fechaFin = Date.valueOf(tFechaFin.getText());
					TTemporada tot = new TTemporada(idT, numT, calificacion, fechaInicio, fechaFin, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_TEMPORADA, tot);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MODIFICAR_TEMPORADA_OK)
			JOptionPane.showMessageDialog(this,
					"Se ha modificado correctamente la temporada con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_TEMPORADA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la temporada");
	}
}