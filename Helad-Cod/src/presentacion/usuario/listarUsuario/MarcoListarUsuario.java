package presentacion.usuario.listarUsuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import negocio.usuario.TUsuario;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;


public class MarcoListarUsuario extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JButton botonListar;
	private JButton botonCerrar;
	private modeloListarUsuario tablaListarUsuarios;
	private JScrollPane scrollTablaListarUsuarios;
	private JTable tablaUsuarios;
	private JTextField textFieldIdGuia;
	private JLabel labelIdGuia;
	
	public MarcoListarUsuario(){
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Usuarios");
		setLocation(420,400);
		JPanel emergent = new JPanel();
		emergent.setLayout(new BoxLayout(emergent, BoxLayout.Y_AXIS));
		setContentPane(emergent);
		
		JPanel selectors = new JPanel();
	
		
		tablaListarUsuarios = new modeloListarUsuario();
		tablaUsuarios = new JTable(tablaListarUsuarios);
		tablaUsuarios.setShowGrid(true);
		tablaUsuarios.getTableHeader().setReorderingAllowed(true);
		scrollTablaListarUsuarios = new JScrollPane(tablaUsuarios);
		scrollTablaListarUsuarios.setPreferredSize(new Dimension(700,300));
		
		
				
		
		selectors.add(scrollTablaListarUsuarios);
		
		emergent.add(selectors);
		
		JPanel buttonsPanel = new JPanel();
		botonListar = new JButton("Listar");
		botonListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;
				if(textFieldIdGuia.getText().isEmpty()) id = 0;
				else id = Integer.parseInt(textFieldIdGuia.getText());
				Controlador.getInstance().update(Evento.LISTAR_USUARIO, id);
				MarcoListarUsuario.this.setVisible(false);
			}
			
		});
		botonCerrar = new JButton("Cancelar");
		botonCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		labelIdGuia = new JLabel("Id de guia: ");
		textFieldIdGuia = new JTextField();
		textFieldIdGuia.setPreferredSize(new Dimension(100, 20));
		
		buttonsPanel.add(labelIdGuia);
		buttonsPanel.add(textFieldIdGuia);
		buttonsPanel.add(botonCerrar);
		buttonsPanel.add(botonListar);
		
		emergent.add(buttonsPanel);
		
		selectors.setBackground(Color.white);
		buttonsPanel.setBackground(Color.white);
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	
	public class SortUsuariosPorId implements Comparator<TUsuario> {

		@Override
		public int compare(TUsuario u1, TUsuario u2) {
			if (u1.getId() == u2.getId())
				return 0;
			else if (u1.getId() < u2.getId())
				return -1;
			else
				return 1;
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public void update(Integer evento, Object datos) {
		List<TUsuario> listaS = new ArrayList<TUsuario>();
		listaS = (List<TUsuario>) datos;
		ArrayList<TUsuario> listaS2 = new ArrayList<>(listaS);
		listaS2.sort(new SortUsuariosPorId());
		tablaListarUsuarios.update(listaS2);
	}
	
}
