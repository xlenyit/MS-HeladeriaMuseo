package presentaci�n.miembrosdecompa�ia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentaci�n.IGUI;
import presentaci�n.controlador.Controlador;
import presentaci�n.controlador.Evento;

public class VistaBuscarMComp extends JFrame implements IGUI {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void vistaBuscarMiembroDeCompa�ia() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	private static final long serialVersionUID = 1L;

	public VistaBuscarMComp() {
		setTitle("BUSCAR MIEMBRO DE COMPA�IA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de miembro:");
		final JTextField tid = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idM = Integer.parseInt(tid.getText());
					Controlador.getInstance().accion(Evento.BUSCAR_MIEMBRO_COMPA�IA, idM);
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
		if (evento == Evento.RES_BUSCAR_MIEMBRO_COMPA�IA_OK) {
			JOptionPane.showMessageDialog(this, datos.toString());
		} else if (evento == Evento.RES_BUSCAR_MIEMBRO_COMPA�IA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido encontrar el miembro");
	}
}