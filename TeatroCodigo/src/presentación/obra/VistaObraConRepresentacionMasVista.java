package presentación.obra;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaObraConRepresentacionMasVista extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaObraConRepresentacionMasVista() {
		setTitle("VER OBRA CON REPRESENTACIÓN MÁS VISTA");
		JPanel panel = new JPanel();
		JLabel mostrar = new JLabel("¿Está seguro de ver la obra con la representación más vista?");
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(mostrar);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.OBRA_CON_REPRESENTACION_MAS_VISTA, null);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "No se ha podido encontrar la obra");
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_OBRA_CON_REPRESENTACION_MAS_VISTA_OK) {
			JTextArea textArea = new JTextArea(datos.toString());
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(this, scrollPane, "OBRAS", JOptionPane.DEFAULT_OPTION);
		} else if (evento == Evento.RES_OBRA_CON_REPRESENTACION_MAS_VISTA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar ninguna obra");
	}

}
