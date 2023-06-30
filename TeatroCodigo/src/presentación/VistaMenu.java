package presentación;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaMenu extends JFrame implements IGUI {
	public VistaMenu() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("MENU");
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		panel.setPreferredSize(new Dimension(400, 60));
		panel.setLayout(new GridLayout(1, 2));
		JButton tienda = new JButton("TIENDA");
		JButton teatro = new JButton("TEATRO");
		panel.add(tienda);
		panel.add(teatro);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		tienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MENU_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		teatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MENU_TEATRO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
	}

}
