package presentación.factoria;

import presentación.IGUI;
import presentación.VistaMenu;
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
import presentación.empleado.VistaAltaEmpleado;
import presentación.empleado.VistaBuscarEmpleado;
import presentación.empleado.VistaEliminarEmpleado;
import presentación.empleado.VistaEmpleado;
import presentación.empleado.VistaModificarEmpleado;
import presentación.empleado.VistaMostrarEmpleadoPorTurno;
import presentación.empleado.VistaMostrarTodosEmpleado;
import presentación.factura.VistaBuscarF;
import presentación.factura.VistaCerrarVenta;
import presentación.factura.VistaEliminarF;
import presentación.factura.VistaModificarF;
import presentación.factura.VistaMostrarFacturasPorCliente;
import presentación.factura.VistaMostrarTodasF;
import presentación.facturaTienda.VistaAbrirVentaTienda;
import presentación.facturaTienda.VistaAñadirOEliminarProducto;
import presentación.facturaTienda.VistaBuscarFacturaTienda;
import presentación.facturaTienda.VistaCerrarVentaTienda;
import presentación.facturaTienda.VistaDevolverProducto;
import presentación.facturaTienda.VistaFacturaTienda;
import presentación.facturaTienda.VistaModificarFacturaTienda;
import presentación.facturaTienda.VistaMostrarPorEmpleado;
import presentación.facturaTienda.VistaMostrarTodasFacturasTienda;
import presentación.marca.VistaAltaMarca;
import presentación.marca.VistaBuscarMarca;
import presentación.marca.VistaEliminarMarca;
import presentación.marca.VistaMarca;
import presentación.marca.VistaModificarMarca;
import presentación.marca.VistaMostrarMarcas;
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
import presentación.producto.VistaAltaProducto;
import presentación.producto.VistaBajaProducto;
import presentación.producto.VistaBuscarProducto;
import presentación.producto.VistaModificarProducto;
import presentación.producto.VistaMostrarProductos;
import presentación.producto.VistaMostrarProductosPorProveedor;
import presentación.producto.VistaProducto;
import presentación.proveedor.VistaAnadirProveedor;
import presentación.proveedor.VistaAsignarProveedorAProducto;
import presentación.proveedor.VistaBuscarProveedor;
import presentación.proveedor.VistaDesasignarProveedorAProducto;
import presentación.proveedor.VistaElminarProveedor;
import presentación.proveedor.VistaModificarProveedor;
import presentación.proveedor.VistaMostrarProveedorPorProducto;
import presentación.proveedor.VistaMostrarProveedores;
import presentación.proveedor.VistaProveedor;
import presentación.temporada.VistaAltaTemporada;
import presentación.temporada.VistaBuscarT;
import presentación.temporada.VistaEliminarT;
import presentación.temporada.VistaModificarT;
import presentación.temporada.VistaMostrarT;
import presentación.turno.VistaAltaTurno;
import presentación.turno.VistaBuscarTurno;
import presentación.turno.VistaCalcularNominaTurno;
import presentación.turno.VistaEliminarTurno;
import presentación.turno.VistaModificarTurno;
import presentación.turno.VistaMostrarTurnos;
import presentación.tienda.VistaTienda;
import presentación.turno.VistaTurno;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion {

	@Override
	public IGUI createVista(int id) {
		switch (id) {
		//MENUS
		case Evento.MENU: {
			return new VistaMenu();
		}
		case Evento.MENU_TIENDA: {
			return new VistaTienda();
		}

		case Evento.ALTA_TEMPORADA: {
			return new VistaAltaTemporada();
		}
		case Evento.BUSCAR_TEMPORADA: {
			return new VistaBuscarT();
		}
		case Evento.ELIMINAR_TEMPORADA: {
			return new VistaEliminarT();
		}
		case Evento.MODIFICAR_TEMPORADA: {
			return new VistaModificarT();
		}
		case Evento.MOSTRAR_TEMPORADA: {
			return new VistaMostrarT();
		}

		// CLIENTE
		case Evento.ALTA_CLIENTE: {
			return new VistaAltaCl();
		}
		case Evento.BUSCAR_CLIENTE: {
			return new VistaBuscarCl();
		}
		case Evento.ELIMINAR_CLIENTE: {
			return new VistaEliminarCl();
		}
		case Evento.MODIFICAR_CLIENTE: {
			return new VistaModificarCl();
		}
		case Evento.MOSTRAR_CLIENTE: {
			return new VistaMostrarCl();
		}
		case Evento.CLIENTE_CON_MAS_FACTURACION: {
			return new VistaClienteConMasFacturacion();
		}

		// ACTIVIDAD
		case Evento.ALTA_ACTIVIDAD: {
			return new VistaAltaA();
		}
		case Evento.BUSCAR_ACTIVIDAD: {
			return new VistaBuscarA();
		}
		case Evento.ELIMINAR_ACTIVIDAD: {
			return new VistaEliminarA();
		}
		case Evento.MODIFICAR_ACTIVIDAD: {
			return new VistaModificarA();
		}
		case Evento.MOSTRAR_ACTIVIDAD: {
			return new VistaMostrarA();
		}
		case Evento.MOSTRAR_POR_COMPAÑIA: {
			return new VistaMostrarPorCompañia();
		}
		case Evento.MOSTRAR_POR_OBRA: {
			return new VistaMostrarPorObra();
		}
		case Evento.MOSTRAR_POR_TEMPORADA: {
			return new VistaMostrarPorTemporada();
		}

		// MIEMBRO_COMPAÑIA
		case Evento.ALTA_MIEMBRO_COMPAÑIA: {
			return new VistaAñadirMComp();
		}
		case Evento.BUSCAR_MIEMBRO_COMPAÑIA: {
			return new VistaBuscarMComp();
		}
		case Evento.ELIMINAR_MIEMBRO_COMPAÑIA: {
			return new VistaEliminarMComp();
		}
		case Evento.MODIFICAR_MIEMBRO_COMPAÑIA: {
			return new VistaModificarMComp();
		}
		case Evento.MOSTRAR_MIEMBRO_COMPAÑIA: {
			return new VistaMostrarMComp();
		}
		case Evento.ASIGNAR_MIEMBRO_A_COMPAÑIA: {
			return new VistaAsignarACompañia();
		}
		case Evento.ELIMINAR_MIEMBRO_DE_COMPAÑIA: {
			return new VistaEliminarDeCompañia();
		}
		case Evento.MODIFICAR_NUMERO_MESES: {
			return new VistaModificarMeses();
		}

		//COMPANIA
		case Evento.ALTA_COMPAÑIA: {
			return new VistaAltaCompañia();
		}
		case Evento.BUSCAR_COMPAÑIA: {
			return new VistaBuscarComp();
		}
		case Evento.ELIMINAR_COMPAÑIA: {
			return new VistaEliminarComp();
		}
		case Evento.MODIFICAR_COMPAÑIA: {
			return new VistaModificarComp();
		}
		case Evento.MOSTRAR_COMPAÑIA: {
			return new VistaMostrarComp();
		}

		// OBRA
		case Evento.ALTA_OBRA: {
			return new VistaAltaOb();
		}
		case Evento.BUSCAR_OBRA: {
			return new VistaBuscarOb();
		}
		case Evento.ELIMINAR_OBRA: {
			return new VistaEliminarOb();
		}
		case Evento.MODIFICAR_OBRA: {
			return new VistaModificarOb();
		}
		case Evento.MOSTRAR_OBRA: {
			return new VistaMostrarOb();
		}
		case Evento.OBRA_CON_REPRESENTACION_MAS_VISTA: {
			return new VistaObraConRepresentacionMasVista();
		}

		//FACTURA
		case Evento.ALTA_FACTURA: {
			return new VistaCerrarVenta();
		}
		case Evento.BUSCAR_FACTURA: {
			return new VistaBuscarF();
		}
		case Evento.BUSCAR_FACTURA_CL: {
			return new VistaMostrarFacturasPorCliente();
		}
		case Evento.MODIFICAR_FACTURA: {
			return new VistaModificarF();
		}
		case Evento.MOSTRAR_FACTURA: {
			return new VistaMostrarTodasF();
		}
		case Evento.ELIMINAR_FACTURA: {
			return new VistaEliminarF();
		}

		//TURNO
		case Evento.TURNO: {
			return new VistaTurno();
		}
		case Evento.VISTA_ALTA_TURNO:
		case Evento.ALTA_TURNO: {
			return new VistaAltaTurno();
		}
		case Evento.VISTA_BUSCAR_TURNO:
		case Evento.BUSCAR_TURNO: {
			return new VistaBuscarTurno();
		}
		case Evento.VISTA_MODIFICAR_TURNO:
		case Evento.MODIFICAR_TURNO: {
			return new VistaModificarTurno();
		}
		case Evento.VISTA_MOSTRAR_TURNOS:
		case Evento.MOSTRAR_TURNOS: {
			return new VistaMostrarTurnos();
		}
		case Evento.VISTA_ELIMINAR_TURNO:
		case Evento.ELIMINAR_TURNO: {
			return new VistaEliminarTurno();
		}
		case Evento.VISTA_NOMINA_TURNO:
		case Evento.NOMINA_TURNO: {
			return new VistaCalcularNominaTurno();
		}

		// MARCA
		case Evento.MARCA: {
			return new VistaMarca();
		}
		case Evento.VISTA_ALTA_MARCA:
		case Evento.ALTA_MARCA: {
			return new VistaAltaMarca();
		}
		case Evento.VISTA_BUSCAR_MARCA:
		case Evento.BUSCAR_MARCA: {
			return new VistaBuscarMarca();
		}
		case Evento.VISTA_MODIFICAR_MARCA:
		case Evento.MODIFICAR_MARCA: {
			return new VistaModificarMarca();
		}
		case Evento.VISTA_MOSTRAR_MARCAS:
		case Evento.MOSTRAR_MARCAS: {
			return new VistaMostrarMarcas();
		}
		case Evento.VISTA_ELIMINAR_MARCA:
		case Evento.ELIMINAR_MARCA: {
			return new VistaEliminarMarca();
		}

		//EMPLEADO
		case Evento.EMPLEADO: {
			return new VistaEmpleado();
		}
		case Evento.VISTA_ALTA_EMPLEADO:
		case Evento.ALTA_EMPLEADO: {
			return new VistaAltaEmpleado();
		}
		case Evento.VISTA_BUSCAR_EMPLEADO:
		case Evento.BUSCAR_EMPLEADO: {
			return new VistaBuscarEmpleado();
		}
		case Evento.VISTA_MODIFICAR_EMPLEADO:
		case Evento.MODIFICAR_EMPLEADO: {
			return new VistaModificarEmpleado();
		}
		case Evento.VISTA_MOSTRAR_EMPLEADO:
		case Evento.MOSTRAR_EMPLEADOS: {
			return new VistaMostrarTodosEmpleado();
		}
		case Evento.VISTA_ELIMINAR_EMPLEADO:
		case Evento.ELIMINAR_EMPLEADO: {
			return new VistaEliminarEmpleado();
		}
		case Evento.VISTA_MOSTRAR_POR_TURNO_EMPLEADO:
		case Evento.MOSTRAR_POR_TURNO_EMPLEADO: {
			return new VistaMostrarEmpleadoPorTurno();
		}

		// PRODUCTO
		case Evento.PRODUCTO: {
			return new VistaProducto();
		}
		case Evento.VISTA_ALTA_PRODUCTO:
		case Evento.ALTA_PRODUCTO: {
			return new VistaAltaProducto();
		}
		case Evento.VISTA_BUSCAR_PRODUCTO:
		case Evento.BUSCAR_PRODUCTO: {
			return new VistaBuscarProducto();
		}
		case Evento.VISTA_MODIFICAR_PRODUCTO:
		case Evento.MODIFICAR_PRODUCTO: {
			return new VistaModificarProducto();
		}
		case Evento.VISTA_MOSTRAR_PRODUCTO:
		case Evento.MOSTRAR_PRODUCTO: {
			return new VistaMostrarProductos();
		}
		case Evento.VISTA_ELIMINAR_PRODUCTO:
		case Evento.ELIMINAR_PRODUCTO: {
			return new VistaBajaProducto();
		}
		case Evento.VISTA_MOSTRAR_PRODUCTO_PROVEEDOR:
		case Evento.MOSTRAR_PRODUCTO_PROVEEDOR: {
			return new VistaMostrarProductosPorProveedor();
		}

		// FACTURA TIENDA
		case Evento.FACTURA_TIENDA: {
			return new VistaFacturaTienda();
		}
		case Evento.VISTA_ABRIR_VENTA_TIENDA: {
			return new VistaAbrirVentaTienda();
		}
		case Evento.VISTA_AÑADIR_O_ELIMINAR: {
			return new VistaAñadirOEliminarProducto();
		}
		case Evento.VISTA_CERRAR_VENTA_TIENDA: 
		case Evento.ALTA_FACTURA_TIENDA: {
			return new VistaCerrarVentaTienda();
		}
		case Evento.VISTA_BUSCAR_FACTURA_TIENDA:
		case Evento.BUSCAR_FACTURA_TIENDA: {
			return new VistaBuscarFacturaTienda();
		}
		case Evento.VISTA_DEVOLVER_PRODUCTO_FACTURA_TIENDA:
		case Evento.DEVOLVER_PRODUCTO_FACTURA_TIENDA: {
			return new VistaDevolverProducto();
		}
		case Evento.VISTA_MODIFICAR_FACTURA_TIENDA:
		case Evento.MODIFICAR_FACTURA_TIENDA: {
			return new VistaModificarFacturaTienda();
		}
		case Evento.VISTA_MOSTRAR_FACTURA_TIENDA:
		case Evento.MOSTRAR_FACTURA_TIENDA: {
			return new VistaMostrarTodasFacturasTienda();
		}
		case Evento.VISTA_BUSCAR_FACTURA_EMPLEADO:
		case Evento.BUSCAR_FACTURA_EMPLEADO: {
			return new VistaMostrarPorEmpleado();
		}
		
		case Evento.PROVEEDOR:
		{
			return new VistaProveedor();
		}
		
		// PROVEEDOR
		case Evento.VISTA_ALTA_PROVEEDOR:
		case Evento.ALTA_PROVEEDOR:
		{
			return new VistaAnadirProveedor();
		}
		
		case Evento.VISTA_BUSCAR_PROVEEDOR:
		case Evento.BUSCAR_PROVEEDOR:
		{
			return new VistaBuscarProveedor();
		}
		
		case Evento.VISTA_ASIGNAR_PROVEEDOR_A_PRODUCTO:
		case Evento.ASIGNAR_PROVEEDOR_A_PRODUCTO:
		{
			return new VistaAsignarProveedorAProducto();
		}
		
		case Evento.VISTA_DESASIGNAR_PROVEEDOR_A_PRODUCTO: 
		case Evento.DESASIGNAR_PROVEEDOR_A_PRODUCTO:{
			return new VistaDesasignarProveedorAProducto();
		}
		
		case Evento.VISTA_ELIMINAR_PROVEEDOR: 
		case Evento.ELIMINAR_PROVEEDOR:{
			return new VistaElminarProveedor();
		}
		
		case Evento.VISTA_MODIFICAR_PROVEEDOR: 
		case Evento.MODIFICAR_PROVEEDOR:{
			return new VistaModificarProveedor();
		}
		
		case Evento.VISTA_MOSTRAR_PROVEEDOR: 
		case Evento.MOSTRAR_PROVEEDOR:{
			return new VistaMostrarProveedores();
		}
		
		case Evento.VISTA_MOSTRAR_PROVEEDOR_PRODUCTO: 
		case Evento.MOSTRAR_PROVEEDOR_PRODUCTO:{
			return new VistaMostrarProveedorPorProducto();
		}	
		
	}
		
		return null;	
	}
}
