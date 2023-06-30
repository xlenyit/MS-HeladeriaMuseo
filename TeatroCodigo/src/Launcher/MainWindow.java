package Launcher;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentación.actividad.VistaAltaA;
import presentación.actividad.VistaBuscarA;
import presentación.actividad.VistaEliminarA;
import presentación.actividad.VistaModificarA;
import presentación.actividad.VistaMostrarA;
import presentación.actividad.VistaMostrarPorCompañia;
import presentación.actividad.VistaMostrarPorObra;
import presentación.actividad.VistaMostrarPorTemporada;
import presentación.cliente.VistaAltaCl;
import presentación.cliente.VistaBuscarCl;
import presentación.cliente.VistaClienteConMasFacturacion;
import presentación.cliente.VistaEliminarCl;
import presentación.cliente.VistaModificarCl;
import presentación.cliente.VistaMostrarCl;
import presentación.compañia.VistaAltaCompañia;
import presentación.compañia.VistaBuscarComp;
import presentación.compañia.VistaEliminarComp;
import presentación.compañia.VistaModificarComp;
import presentación.compañia.VistaMostrarComp;
import presentación.controlador.Evento;
import presentación.factoria.FactoriaAbstractaPresentacion;
import presentación.factura.VistaAbrirVenta;
import presentación.factura.VistaAñadirOEliminarActividad;
import presentación.factura.VistaBuscarF;
import presentación.factura.VistaCerrarVenta;
import presentación.factura.VistaEliminarF;
import presentación.factura.VistaModificarF;
import presentación.factura.VistaMostrarFacturasPorCliente;
import presentación.factura.VistaMostrarTodasF;
import presentación.miembrosdecompañia.VistaAsignarACompañia;
import presentación.miembrosdecompañia.VistaAñadirMComp;
import presentación.miembrosdecompañia.VistaBuscarMComp;
import presentación.miembrosdecompañia.VistaEliminarDeCompañia;
import presentación.miembrosdecompañia.VistaEliminarMComp;
import presentación.miembrosdecompañia.VistaModificarMComp;
import presentación.miembrosdecompañia.VistaModificarMeses;
import presentación.miembrosdecompañia.VistaMostrarMComp;
import presentación.obra.VistaAltaOb;
import presentación.obra.VistaBuscarOb;
import presentación.obra.VistaEliminarOb;
import presentación.obra.VistaModificarOb;
import presentación.obra.VistaMostrarOb;
import presentación.obra.VistaObraConRepresentacionMasVista;
import presentación.temporada.VistaAltaTemporada;
import presentación.temporada.VistaBuscarT;
import presentación.temporada.VistaEliminarT;
import presentación.temporada.VistaModificarT;
import presentación.temporada.VistaMostrarT;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDialog temporada;
	private JDialog actividad;
	private JDialog compañia;
	private JDialog cliente;
	private JDialog miembros;
	private JDialog obra;
	private JDialog factura;

	public MainWindow() {
		super("Main window");
		initGUI();
	}

	private void initGUI() {
		setPreferredSize(new Dimension(500, 200));
		setMinimumSize(new Dimension(500, 200));
		setMaximumSize(new Dimension(500, 200));
		setLayout(new GridLayout(0, 2));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JButton actividad = new JButton("ACTIVIDAD");
		actividad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actividad();
			}

		});
		JButton cliente = new JButton("CLIENTE");
		cliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cliente();
			}

		});
		JButton compania = new JButton("COMPAÑIA");
		compania.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				compañia();
			}

		});
		JButton factura = new JButton("FACTURA");
		factura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				factura();
			}

		});
		JButton miembros = new JButton("MIEMBROS");
		miembros.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				miembros();
			}

		});
		JButton obra = new JButton("OBRA");
		obra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				obra();
			}

		});
		JButton temporada = new JButton("TEMPORADA");
		temporada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				temporada();
			}

		});
		
		

		add(actividad);
		add(cliente);
		add(compania);
		add(factura);
		add(miembros);
		add(obra);
		add(temporada);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void temporada() {
		if (temporada == null) {
			temporada = new JDialog();
			temporada.setPreferredSize(new Dimension(500, 200));
			temporada.setMinimumSize(new Dimension(500, 200));
			temporada.setMaximumSize(new Dimension(500, 200));
			temporada.setLayout(new GridLayout(0, 2));

			JButton añadir = new JButton("AÑADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");

			añadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAltaTemporada vista = (VistaAltaTemporada) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_TEMPORADA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarT vista = (VistaBuscarT) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_TEMPORADA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarT vista = (VistaEliminarT) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_TEMPORADA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarT vista = (VistaModificarT) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_TEMPORADA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarT vista = (VistaMostrarT) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_TEMPORADA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			temporada.add(añadir);
			temporada.add(buscar);
			temporada.add(eliminar);
			temporada.add(modificar);
			temporada.add(mostrar);
		}
		temporada.setLocationRelativeTo(null);
		temporada.setVisible(true);
	}

	private void compañia() {
		if (compañia == null) {
			compañia = new JDialog();
			compañia.setPreferredSize(new Dimension(500, 200));
			compañia.setMinimumSize(new Dimension(500, 200));
			compañia.setMaximumSize(new Dimension(500, 200));
			compañia.setLayout(new GridLayout(0, 2));

			JButton añadir = new JButton("AÑADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");

			añadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAltaCompañia vista = (VistaAltaCompañia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarComp vista = (VistaBuscarComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarComp vista = (VistaEliminarComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarComp vista = (VistaModificarComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarComp vista = (VistaMostrarComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			compañia.add(añadir);
			compañia.add(buscar);
			compañia.add(eliminar);
			compañia.add(modificar);
			compañia.add(mostrar);
		}
		compañia.setLocationRelativeTo(null);
		compañia.setVisible(true);
	}

	private void miembros() {
		if (miembros == null) {
			miembros = new JDialog();
			miembros.setPreferredSize(new Dimension(700, 200));
			miembros.setMinimumSize(new Dimension(700, 200));
			miembros.setMaximumSize(new Dimension(700, 200));
			miembros.setLayout(new GridLayout(0, 2));

			JButton añadir = new JButton("AÑADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton asignar = new JButton("ASIGNAR MIEMBRO A COMPAÑIA");
			JButton desasignar = new JButton("DESASIGNAR MIEMBRO DE COMPAÑIA");
			JButton modificarM = new JButton("ACTUALIZAR NÚMERO DE MESES");

			añadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAñadirMComp vista = (VistaAñadirMComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_MIEMBRO_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarMComp vista = (VistaBuscarMComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_MIEMBRO_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarMComp vista = (VistaEliminarMComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_MIEMBRO_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarMComp vista = (VistaModificarMComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_MIEMBRO_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			asignar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAsignarACompañia vista = (VistaAsignarACompañia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ASIGNAR_MIEMBRO_A_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			desasignar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarDeCompañia vista = (VistaEliminarDeCompañia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_MIEMBRO_DE_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificarM.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarMeses vista = (VistaModificarMeses) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_NUMERO_MESES);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarMComp vista = (VistaMostrarMComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_MIEMBRO_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			miembros.add(añadir);
			miembros.add(buscar);
			miembros.add(eliminar);
			miembros.add(modificar);
			miembros.add(asignar);
			miembros.add(desasignar);
			miembros.add(modificarM);
			miembros.add(mostrar);
		}
		miembros.setLocationRelativeTo(null);
		miembros.setVisible(true);
	}

	private void cliente() {
		if (cliente == null) {
			cliente = new JDialog();
			cliente.setPreferredSize(new Dimension(500, 200));
			cliente.setMinimumSize(new Dimension(500, 200));
			cliente.setMaximumSize(new Dimension(500, 200));
			cliente.setLayout(new GridLayout(0, 2));

			JButton añadir = new JButton("AÑADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton cliente_mas_facturacion=new JButton("CLIENTE CON MÁS REPRESENTACIÓN");

			añadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAltaCl vista = (VistaAltaCl) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_CLIENTE);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarCl vista = (VistaBuscarCl) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_CLIENTE);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarCl vista = (VistaEliminarCl) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_CLIENTE);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarCl vista = (VistaModificarCl) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_CLIENTE);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarCl vista = (VistaMostrarCl) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_CLIENTE);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});
			
			cliente_mas_facturacion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaClienteConMasFacturacion vista=
							(VistaClienteConMasFacturacion) FactoriaAbstractaPresentacion.
							getInstance().createVista(Evento.CLIENTE_CON_MAS_FACTURACION);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}
				
			});

			cliente.add(añadir);
			cliente.add(buscar);
			cliente.add(eliminar);
			cliente.add(modificar);
			cliente.add(mostrar);
			cliente.add(cliente_mas_facturacion);
		}
		cliente.setLocationRelativeTo(null);
		cliente.setVisible(true);
	}

	private void actividad() {
		if (actividad == null) {
			actividad = new JDialog();
			actividad.setTitle("actividad");
			actividad.setPreferredSize(new Dimension(500, 200));
			actividad.setMinimumSize(new Dimension(500, 200));
			actividad.setMaximumSize(new Dimension(500, 200));
			actividad.setLayout(new GridLayout(0, 2));

			JButton añadir = new JButton("AÑADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton mostrarPorCompania = new JButton("MOSTRAR POR COMPAÑIA");
			JButton mostrarPorObra = new JButton("MOSTRAR POR OBRA");
			JButton mostrarPorTemporada = new JButton("MOSTRAR POR TEMPORADA");

			añadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAltaA vista = (VistaAltaA) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_ACTIVIDAD);
					vista.setMinimumSize(new Dimension(500, 500));
					vista.setMaximumSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarA vista = (VistaBuscarA) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_ACTIVIDAD);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarA vista = (VistaEliminarA) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_ACTIVIDAD);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarA vista = (VistaModificarA) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_ACTIVIDAD);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarA vista = (VistaMostrarA) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_ACTIVIDAD);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrarPorCompania.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarPorCompañia vista = (VistaMostrarPorCompañia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_POR_COMPAÑIA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrarPorObra.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarPorObra vista = (VistaMostrarPorObra) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_POR_OBRA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrarPorTemporada.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarPorTemporada vista = (VistaMostrarPorTemporada) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_POR_TEMPORADA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			actividad.add(añadir);
			actividad.add(buscar);
			actividad.add(eliminar);
			actividad.add(modificar);
			actividad.add(mostrar);
			actividad.add(mostrarPorCompania);
			actividad.add(mostrarPorObra);
			actividad.add(mostrarPorTemporada);
		}
		actividad.setLocationRelativeTo(null);
		actividad.setVisible(true);
	}

	private void obra() {
		if (obra == null) {
			obra = new JDialog();
			obra.setPreferredSize(new Dimension(500, 200));
			obra.setMinimumSize(new Dimension(500, 200));
			obra.setMaximumSize(new Dimension(500, 200));
			obra.setLayout(new GridLayout(0, 2));

			JButton añadir = new JButton("AÑADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton obra_representacion_mas_vista = new JButton("OBRA CON REPRESENTACIÓN MÁS VISTA");

			añadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAltaOb vista = (VistaAltaOb) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_OBRA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarOb vista = (VistaBuscarOb) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_OBRA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarOb vista = (VistaEliminarOb) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_OBRA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarOb vista = (VistaModificarOb) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_OBRA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarOb vista = (VistaMostrarOb) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_OBRA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});
			
			obra_representacion_mas_vista.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaObraConRepresentacionMasVista vista=
							(VistaObraConRepresentacionMasVista) FactoriaAbstractaPresentacion.
							getInstance().createVista(Evento.OBRA_CON_REPRESENTACION_MAS_VISTA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}
				
			});

			obra.add(añadir);
			obra.add(buscar);
			obra.add(eliminar);
			obra.add(modificar);
			obra.add(mostrar);
			obra.add(obra_representacion_mas_vista);
		}
		obra.setLocationRelativeTo(null);
		obra.setVisible(true);
	}

	private void factura() {
		if (factura == null) {
			factura = new JDialog();
			factura.setPreferredSize(new Dimension(700, 200));
			factura.setMinimumSize(new Dimension(700, 200));
			factura.setMaximumSize(new Dimension(700, 200));
			factura.setLayout(new GridLayout(0, 2));

			JButton abrir = new JButton("ABRIR VENTA");
			JButton añadirOEliminar = new JButton("AÑADIR O ELIMINAR ACTIVIDAD");
			JButton cerrar = new JButton("CERRAR VENTA");
			JButton buscar = new JButton("BUSCAR FACTURA");
			JButton eliminar = new JButton("ELIMINAR FACTURA");
			JButton modificar = new JButton("MODIFICAR FACTURA");
			JButton mostrar = new JButton("MOSTRAR FACTURAS");
			JButton mostrarCl = new JButton("MOSTRAR FACTURAS ASOCIADAS A UN CLIENTE");

			abrir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAbrirVenta vista = new VistaAbrirVenta();
					vista.pack();
				}

			});

			añadirOEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAñadirOEliminarActividad vista = new VistaAñadirOEliminarActividad();
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			cerrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaCerrarVenta vista = (VistaCerrarVenta) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_FACTURA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			buscar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaBuscarF vista = (VistaBuscarF) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_FACTURA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			eliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarF vista = (VistaEliminarF) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_FACTURA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			modificar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaModificarF vista = (VistaModificarF) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MODIFICAR_FACTURA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarTodasF vista = (VistaMostrarTodasF) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_FACTURA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			mostrarCl.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaMostrarFacturasPorCliente vista = (VistaMostrarFacturasPorCliente) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.BUSCAR_FACTURA_CL);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			factura.add(abrir);
			factura.add(añadirOEliminar);
			factura.add(cerrar);
			factura.add(buscar);
			factura.add(eliminar);
			factura.add(modificar);
			factura.add(mostrar);
			factura.add(mostrarCl);
		}
		factura.setLocationRelativeTo(null);
		factura.setVisible(true);
	}
}
