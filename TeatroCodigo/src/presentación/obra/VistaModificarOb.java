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

public class VistaModificarOb extends JFrame implements IGUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void VistaModificarObra() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	private static final long serialVersionUID = 1L;

	public VistaModificarOb() {
		setTitle("MODIFICAR OBRA");
		JPanel panel = new JPanel();
		JLabel lid = new JLabel("Id de obra:");
		final JTextField tid = new JTextField(20);
		JLabel lTit = new JLabel("Titulo:");
		final JTextField tTit = new JTextField(20);
		JLabel lGen = new JLabel("Genero:");
		final JTextField tGen = new JTextField(20);
		JLabel lAut = new JLabel("Autor:");
		final JTextField tAut = new JTextField(20);
		JLabel lSinopsis = new JLabel("Sinopsis:");
		final JTextField tSinopsis = new JTextField(50);
		JLabel lAnio = new JLabel("Año (YYYY):");
		final JTextField tAnio = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lid);
		panel.add(tid);
		panel.add(lTit);
		panel.add(tTit);
		panel.add(lGen);
		panel.add(tGen);
		panel.add(lAut);
		panel.add(tAut);
		panel.add(lSinopsis);
		panel.add(tSinopsis);
		panel.add(lAnio);
		panel.add(tAnio);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);

		pack();

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int idT = Integer.parseInt(tid.getText());
					String tit = tTit.getText();
					String gen = tGen.getText();
					String aut = tAut.getText();
					String sin = tSinopsis.getText();
					int anio = Integer.parseInt(tAnio.getText());
					TObra tot = new TObra(idT, gen, sin, tit, aut, anio, true);
					Controlador.getInstance().accion(Evento.MODIFICAR_OBRA, tot);
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
		if (evento == Evento.RES_MODIFICAR_OBRA_OK)
			JOptionPane.showMessageDialog(this, "Se ha modificado correctamente la obra con id " + (Integer) datos);
		else if (evento == Evento.RES_MODIFICAR_OBRA_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido modificar la obra");
	}
}