package presentación.obra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.obra.TObra;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaAltaOb extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	public VistaAltaOb() {
		setTitle("AÑADIR OBRA");
		JPanel panel = new JPanel();
		JLabel lTitulo = new JLabel("Titulo:");
		final JTextField tTitulo= new JTextField(20);
		JLabel lGenero = new JLabel("Genero:");
		final JTextField tGenero = new JTextField(20);
		JLabel lSinopsis = new JLabel("Sinopsis:");
		final JTextField tSinopsis = new JTextField(20);
		JLabel lAutor = new JLabel("Autor:");
		final JTextField tAutor = new JTextField(20);
		JLabel lAnio = new JLabel("Año (YYYY):");
		final JTextField tAnio = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lTitulo);
		panel.add(tTitulo);
		panel.add(lGenero);
		panel.add(tGenero);
		panel.add(lSinopsis);
		panel.add(tSinopsis);
		panel.add(lAutor);
		panel.add(tAutor);
		panel.add(lAnio);
		panel.add(tAnio);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					String titulo = tTitulo.getText();
					String genero = tGenero.getText();
					String sinopsis  = tSinopsis.getText();
					String autor = tAutor.getText();
					int anio  = Integer.parseInt(tAnio.getText());
					TObra toc = new TObra(0, genero, sinopsis, titulo, autor, anio,true);
					Controlador.getInstance().accion(Evento.ALTA_OBRA, toc);
				}
				catch (Exception ex) {
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
		if (evento == Evento.RES_ALTA_OBRA_OK) 
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente la obra con id " + (Integer) datos);
		else if(evento == Evento.RES_ALTA_OBRA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido crear la obra");
	}

}