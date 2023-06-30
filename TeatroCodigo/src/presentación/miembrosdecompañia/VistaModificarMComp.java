package presentación.miembrosdecompañia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import negocio.miembrosdecompañia.TMiembrosDeCompañia;
import presentación.IGUI;
import presentación.controlador.Controlador;
import presentación.controlador.Evento;

public class VistaModificarMComp extends JFrame implements IGUI {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void VistaModificarMiembrosDeCompañia() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	private static final long serialVersionUID = 1L;

	public VistaModificarMComp() {
		setTitle("MODIFICAR MIEMBRO");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de miembro:");
		final JTextField tid = new JTextField(20);
		JLabel lNombre = new JLabel("Nombre:");
		final JTextField tNombre = new JTextField(20);
		JLabel lApellidos = new JLabel("Apellidos:");
		final JTextField tApellidos = new JTextField(20);
		JLabel lDNI = new JLabel("DNI:");
		final JTextField tDNI = new JTextField(20);
		JLabel lTipo = new JLabel("Tipo:");
		ButtonGroup bgTipo = new ButtonGroup();
		JRadioButton radio1, radio2;
		radio1 = new JRadioButton("Actor", true);
		radio2 = new JRadioButton("Tecnico");

		bgTipo.add(radio1);
		bgTipo.add(radio2);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(lNombre);
		panel.add(tNombre);
		panel.add(lApellidos);
		panel.add(tApellidos);
		panel.add(lDNI);
		panel.add(tDNI);
		panel.add(lTipo);
		panel.add(radio1);
		panel.add(radio2);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int id = Integer.parseInt(tid.getText());
					String nombre = tNombre.getText();
					String apellidos = tApellidos.getText();
					String dni = tDNI.getText();
					String tipo = null;
					if (radio1.isSelected())
						tipo = radio1.getText();
					else if (radio2.isSelected())
						tipo = radio2.getText();
					TMiembrosDeCompañia tom = new TMiembrosDeCompañia(id, nombre, apellidos, tipo, dni, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_MIEMBRO_COMPAÑIA, tom);
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
		if (evento == Evento.RES_MODIFICAR_MIEMBRO_COMPAÑIA_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente el miembro con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_MIEMBRO_COMPAÑIA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar el miembro");
	}
}