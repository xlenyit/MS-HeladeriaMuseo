package presentación.turno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaTurno extends JFrame implements IGUI {
	public VistaTurno() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("TURNO");
		JPanel panel = new JPanel();
		JButton alta = new JButton("ALTA");
		JButton buscar = new JButton("BUSCAR");
		JButton baja = new JButton("BAJA");
		JButton modificar = new JButton("MODIFICAR");
		JButton listar = new JButton("LISTAR");
		JButton nomina = new JButton("CALCULAR NOMINA");
		JButton cancelar = new JButton("Cancelar");

		panel.add(alta);
		panel.add(buscar);
		panel.add(baja);
		panel.add(modificar);
		panel.add(listar);
		panel.add(nomina);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);

		alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_ALTA_TURNO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_BUSCAR_TURNO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MODIFICAR_TURNO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_ELIMINAR_TURNO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_MOSTRAR_TURNOS, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		nomina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.VISTA_NOMINA_TURNO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.MENU_TIENDA, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
	}
}
