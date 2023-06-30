package Launcher;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import presentaci�n.actividad.VistaAltaA;
import presentaci�n.actividad.VistaBuscarA;
import presentaci�n.actividad.VistaEliminarA;
import presentaci�n.actividad.VistaModificarA;
import presentaci�n.actividad.VistaMostrarA;
import presentaci�n.actividad.VistaMostrarPorCompa�ia;
import presentaci�n.actividad.VistaMostrarPorObra;
import presentaci�n.actividad.VistaMostrarPorTemporada;
import presentaci�n.cliente.VistaAltaCl;
import presentaci�n.cliente.VistaBuscarCl;
import presentaci�n.cliente.VistaClienteConMasFacturacion;
import presentaci�n.cliente.VistaEliminarCl;
import presentaci�n.cliente.VistaModificarCl;
import presentaci�n.cliente.VistaMostrarCl;
import presentaci�n.compa�ia.VistaAltaCompa�ia;
import presentaci�n.compa�ia.VistaBuscarComp;
import presentaci�n.compa�ia.VistaEliminarComp;
import presentaci�n.compa�ia.VistaModificarComp;
import presentaci�n.compa�ia.VistaMostrarComp;
import presentaci�n.controlador.Evento;
import presentaci�n.factoria.FactoriaAbstractaPresentacion;
import presentaci�n.factura.VistaAbrirVenta;
import presentaci�n.factura.VistaA�adirOEliminarActividad;
import presentaci�n.factura.VistaBuscarF;
import presentaci�n.factura.VistaCerrarVenta;
import presentaci�n.factura.VistaEliminarF;
import presentaci�n.factura.VistaModificarF;
import presentaci�n.factura.VistaMostrarFacturasPorCliente;
import presentaci�n.factura.VistaMostrarTodasF;
import presentaci�n.miembrosdecompa�ia.VistaAsignarACompa�ia;
import presentaci�n.miembrosdecompa�ia.VistaA�adirMComp;
import presentaci�n.miembrosdecompa�ia.VistaBuscarMComp;
import presentaci�n.miembrosdecompa�ia.VistaEliminarDeCompa�ia;
import presentaci�n.miembrosdecompa�ia.VistaEliminarMComp;
import presentaci�n.miembrosdecompa�ia.VistaModificarMComp;
import presentaci�n.miembrosdecompa�ia.VistaModificarMeses;
import presentaci�n.miembrosdecompa�ia.VistaMostrarMComp;
import presentaci�n.obra.VistaAltaOb;
import presentaci�n.obra.VistaBuscarOb;
import presentaci�n.obra.VistaEliminarOb;
import presentaci�n.obra.VistaModificarOb;
import presentaci�n.obra.VistaMostrarOb;
import presentaci�n.obra.VistaObraConRepresentacionMasVista;
import presentaci�n.temporada.VistaAltaTemporada;
import presentaci�n.temporada.VistaBuscarT;
import presentaci�n.temporada.VistaEliminarT;
import presentaci�n.temporada.VistaModificarT;
import presentaci�n.temporada.VistaMostrarT;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDialog temporada;
	private JDialog actividad;
	private JDialog compa�ia;
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
		JButton compania = new JButton("COMPA�IA");
		compania.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				compa�ia();
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

			JButton a�adir = new JButton("A�ADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");

			a�adir.addActionListener(new ActionListener() {

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

			temporada.add(a�adir);
			temporada.add(buscar);
			temporada.add(eliminar);
			temporada.add(modificar);
			temporada.add(mostrar);
		}
		temporada.setLocationRelativeTo(null);
		temporada.setVisible(true);
	}

	private void compa�ia() {
		if (compa�ia == null) {
			compa�ia = new JDialog();
			compa�ia.setPreferredSize(new Dimension(500, 200));
			compa�ia.setMinimumSize(new Dimension(500, 200));
			compa�ia.setMaximumSize(new Dimension(500, 200));
			compa�ia.setLayout(new GridLayout(0, 2));

			JButton a�adir = new JButton("A�ADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");

			a�adir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAltaCompa�ia vista = (VistaAltaCompa�ia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_COMPA�IA);
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
							.createVista(Evento.BUSCAR_COMPA�IA);
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
							.createVista(Evento.ELIMINAR_COMPA�IA);
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
							.createVista(Evento.MODIFICAR_COMPA�IA);
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
							.createVista(Evento.MOSTRAR_COMPA�IA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			compa�ia.add(a�adir);
			compa�ia.add(buscar);
			compa�ia.add(eliminar);
			compa�ia.add(modificar);
			compa�ia.add(mostrar);
		}
		compa�ia.setLocationRelativeTo(null);
		compa�ia.setVisible(true);
	}

	private void miembros() {
		if (miembros == null) {
			miembros = new JDialog();
			miembros.setPreferredSize(new Dimension(700, 200));
			miembros.setMinimumSize(new Dimension(700, 200));
			miembros.setMaximumSize(new Dimension(700, 200));
			miembros.setLayout(new GridLayout(0, 2));

			JButton a�adir = new JButton("A�ADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton asignar = new JButton("ASIGNAR MIEMBRO A COMPA�IA");
			JButton desasignar = new JButton("DESASIGNAR MIEMBRO DE COMPA�IA");
			JButton modificarM = new JButton("ACTUALIZAR N�MERO DE MESES");

			a�adir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaA�adirMComp vista = (VistaA�adirMComp) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ALTA_MIEMBRO_COMPA�IA);
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
							.createVista(Evento.BUSCAR_MIEMBRO_COMPA�IA);
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
							.createVista(Evento.ELIMINAR_MIEMBRO_COMPA�IA);
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
							.createVista(Evento.MODIFICAR_MIEMBRO_COMPA�IA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			asignar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaAsignarACompa�ia vista = (VistaAsignarACompa�ia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ASIGNAR_MIEMBRO_A_COMPA�IA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			desasignar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaEliminarDeCompa�ia vista = (VistaEliminarDeCompa�ia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.ELIMINAR_MIEMBRO_DE_COMPA�IA);
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
							.createVista(Evento.MOSTRAR_MIEMBRO_COMPA�IA);
					vista.setLayout(new GridLayout(0, 2));
					vista.setPreferredSize(new Dimension(500, 500));
					vista.setVisible(true);
					vista.pack();
				}

			});

			miembros.add(a�adir);
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

			JButton a�adir = new JButton("A�ADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton cliente_mas_facturacion=new JButton("CLIENTE CON M�S REPRESENTACI�N");

			a�adir.addActionListener(new ActionListener() {

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

			cliente.add(a�adir);
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

			JButton a�adir = new JButton("A�ADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton mostrarPorCompania = new JButton("MOSTRAR POR COMPA�IA");
			JButton mostrarPorObra = new JButton("MOSTRAR POR OBRA");
			JButton mostrarPorTemporada = new JButton("MOSTRAR POR TEMPORADA");

			a�adir.addActionListener(new ActionListener() {

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
					VistaMostrarPorCompa�ia vista = (VistaMostrarPorCompa�ia) FactoriaAbstractaPresentacion.getInstance()
							.createVista(Evento.MOSTRAR_POR_COMPA�IA);
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

			actividad.add(a�adir);
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

			JButton a�adir = new JButton("A�ADIR");
			JButton buscar = new JButton("BUSCAR");
			JButton eliminar = new JButton("ELIMINAR");
			JButton modificar = new JButton("MODIFICAR");
			JButton mostrar = new JButton("MOSTRAR");
			JButton obra_representacion_mas_vista = new JButton("OBRA CON REPRESENTACI�N M�S VISTA");

			a�adir.addActionListener(new ActionListener() {

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

			obra.add(a�adir);
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
			JButton a�adirOEliminar = new JButton("A�ADIR O ELIMINAR ACTIVIDAD");
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

			a�adirOEliminar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VistaA�adirOEliminarActividad vista = new VistaA�adirOEliminarActividad();
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
			factura.add(a�adirOEliminar);
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
