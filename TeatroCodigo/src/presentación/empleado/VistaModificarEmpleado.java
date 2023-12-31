
package presentación.empleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import negocio.empleado.TEmpleado;
import negocio.empleado.TEmpleadoTiempoCompleto;
import negocio.empleado.TEmpleadoTiempoParcial;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarEmpleado extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VistaModificarEmpleado() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MODIFICAR EMPLEADO");
		JButton tiempoParcial = new JButton("Tiempo Parcial");
        JButton tiempoCompleto = new JButton("Tiempo Completo");
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        
        JTextField idE = new JTextField(9);
        JTextField nif = new JTextField(9);
        JTextField idTurno = new JTextField(9);
        JTextField base = new JTextField(9);
        JTextField complemento = new JTextField(9);
        JTextField horas = new JTextField(3);
        JTextField sueldo = new JTextField(9);
        JLabel lid = new JLabel("Id:");
        JLabel lNif = new JLabel("NIF:");
        JLabel lIdTurno = new JLabel("Id turno:");
        JLabel lBase = new JLabel("Base:");
        JLabel lComplemento = new JLabel("Complemento:");
        JLabel lHoras = new JLabel("Horas:");
        JLabel lSueldo = new JLabel("Sueldo:");
        JPanel cards = new JPanel();
        
        JPanel vista = new JPanel(new BorderLayout(10,20));
        vista.setBorder(new TitledBorder("Seleccion de Tipo de Empleado para su alta"));
        cards.setBorder(new TitledBorder("Datos"));
        vista.add(cards);

        JPanel lineStart = new JPanel(new GridLayout(0, 1, 5, 5));
        lineStart.setBorder(new TitledBorder("Tipo de Empleado"));
        vista.add(lineStart, BorderLayout.LINE_START);
        lineStart.add(tiempoParcial);
        lineStart.add(tiempoCompleto);
        lineStart.add(cancelar);
        setContentPane(vista);
        
        pack();
        setMinimumSize(getSize());
        setSize(900, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        JFrame aux = this;
        tiempoParcial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.removeAll();
				cards.add(lid);
				cards.add(idE);
				cards.add(lNif);
				cards.add(nif);
				cards.add(lIdTurno);
				cards.add(idTurno);
				cards.add(lHoras);
				cards.add(horas);
				cards.add(lSueldo);
				cards.add(sueldo);
				cards.add(aceptar);
				
				SwingUtilities.updateComponentTreeUI(aux);
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					try {
						int id = Integer.parseInt(idE.getText());
						String nif_t = nif.getText();
						int idTurno_t = Integer.parseInt(idTurno.getText());
						double horas_t = Double.parseDouble(horas.getText());
						double sueldo_t = Double.parseDouble(sueldo.getText());
						TEmpleado tParticular = new TEmpleadoTiempoParcial(id, true, nif_t, idTurno_t,horas_t,sueldo_t);
						Controlador.getInstance().accion(Evento.MODIFICAR_EMPLEADO, tParticular);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						setVisible(true);
					}
					}
				});
				
				
			}
        });
        
        tiempoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.removeAll();
				cards.add(lid);
				cards.add(idE);
				cards.add(lNif);
				cards.add(nif);
				cards.add(lIdTurno);
				cards.add(idTurno);
				cards.add(lBase);
				cards.add(base);
				cards.add(lComplemento);
				cards.add(complemento);
				cards.add(aceptar);
				
				SwingUtilities.updateComponentTreeUI(aux);
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					try {
						int id = Integer.parseInt(idE.getText());
						String nif_t = nif.getText();
						int idTurno_t = Integer.parseInt(idTurno.getText());
						double base_t = Double.parseDouble(base.getText());
						double complemento_t = Double.parseDouble(complemento.getText());
						TEmpleado tParticular = new TEmpleadoTiempoCompleto(id, true, nif_t, idTurno_t,base_t, complemento_t);
						Controlador.getInstance().accion(Evento.MODIFICAR_EMPLEADO, tParticular);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						setVisible(true);
					}
					}
				});
			}
        });
        cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstance().accion(Evento.EMPLEADO, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
				}
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MODIFICAR_EMPLEADO_OK)
			JOptionPane.showMessageDialog(this,
					"Se ha modificado correctamente el empleado con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_EMPLEADO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el empleado");
	}
}