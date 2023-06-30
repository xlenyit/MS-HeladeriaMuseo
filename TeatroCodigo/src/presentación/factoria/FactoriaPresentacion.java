package presentaci�n.factoria;

import presentaci�n.IGUI;
import presentaci�n.VistaMenu;
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
import presentaci�n.empleado.VistaAltaEmpleado;
import presentaci�n.empleado.VistaBuscarEmpleado;
import presentaci�n.empleado.VistaEliminarEmpleado;
import presentaci�n.empleado.VistaEmpleado;
import presentaci�n.empleado.VistaModificarEmpleado;
import presentaci�n.empleado.VistaMostrarEmpleadoPorTurno;
import presentaci�n.empleado.VistaMostrarTodosEmpleado;
import presentaci�n.factura.VistaBuscarF;
import presentaci�n.factura.VistaCerrarVenta;
import presentaci�n.factura.VistaEliminarF;
import presentaci�n.factura.VistaModificarF;
import presentaci�n.factura.VistaMostrarFacturasPorCliente;
import presentaci�n.factura.VistaMostrarTodasF;
import presentaci�n.facturaTienda.VistaAbrirVentaTienda;
import presentaci�n.facturaTienda.VistaA�adirOEliminarProducto;
import presentaci�n.facturaTienda.VistaBuscarFacturaTienda;
import presentaci�n.facturaTienda.VistaCerrarVentaTienda;
import presentaci�n.facturaTienda.VistaDevolverProducto;
import presentaci�n.facturaTienda.VistaFacturaTienda;
import presentaci�n.facturaTienda.VistaModificarFacturaTienda;
import presentaci�n.facturaTienda.VistaMostrarPorEmpleado;
import presentaci�n.facturaTienda.VistaMostrarTodasFacturasTienda;
import presentaci�n.marca.VistaAltaMarca;
import presentaci�n.marca.VistaBuscarMarca;
import presentaci�n.marca.VistaEliminarMarca;
import presentaci�n.marca.VistaMarca;
import presentaci�n.marca.VistaModificarMarca;
import presentaci�n.marca.VistaMostrarMarcas;
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
import presentaci�n.producto.VistaAltaProducto;
import presentaci�n.producto.VistaBajaProducto;
import presentaci�n.producto.VistaBuscarProducto;
import presentaci�n.producto.VistaModificarProducto;
import presentaci�n.producto.VistaMostrarProductos;
import presentaci�n.producto.VistaMostrarProductosPorProveedor;
import presentaci�n.producto.VistaProducto;
import presentaci�n.proveedor.VistaAnadirProveedor;
import presentaci�n.proveedor.VistaAsignarProveedorAProducto;
import presentaci�n.proveedor.VistaBuscarProveedor;
import presentaci�n.proveedor.VistaDesasignarProveedorAProducto;
import presentaci�n.proveedor.VistaElminarProveedor;
import presentaci�n.proveedor.VistaModificarProveedor;
import presentaci�n.proveedor.VistaMostrarProveedorPorProducto;
import presentaci�n.proveedor.VistaMostrarProveedores;
import presentaci�n.proveedor.VistaProveedor;
import presentaci�n.temporada.VistaAltaTemporada;
import presentaci�n.temporada.VistaBuscarT;
import presentaci�n.temporada.VistaEliminarT;
import presentaci�n.temporada.VistaModificarT;
import presentaci�n.temporada.VistaMostrarT;
import presentaci�n.turno.VistaAltaTurno;
import presentaci�n.turno.VistaBuscarTurno;
import presentaci�n.turno.VistaCalcularNominaTurno;
import presentaci�n.turno.VistaEliminarTurno;
import presentaci�n.turno.VistaModificarTurno;
import presentaci�n.turno.VistaMostrarTurnos;
import presentaci�n.tienda.VistaTienda;
import presentaci�n.turno.VistaTurno;

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
		case Evento.MOSTRAR_POR_COMPA�IA: {
			return new VistaMostrarPorCompa�ia();
		}
		case Evento.MOSTRAR_POR_OBRA: {
			return new VistaMostrarPorObra();
		}
		case Evento.MOSTRAR_POR_TEMPORADA: {
			return new VistaMostrarPorTemporada();
		}

		// MIEMBRO_COMPA�IA
		case Evento.ALTA_MIEMBRO_COMPA�IA: {
			return new VistaA�adirMComp();
		}
		case Evento.BUSCAR_MIEMBRO_COMPA�IA: {
			return new VistaBuscarMComp();
		}
		case Evento.ELIMINAR_MIEMBRO_COMPA�IA: {
			return new VistaEliminarMComp();
		}
		case Evento.MODIFICAR_MIEMBRO_COMPA�IA: {
			return new VistaModificarMComp();
		}
		case Evento.MOSTRAR_MIEMBRO_COMPA�IA: {
			return new VistaMostrarMComp();
		}
		case Evento.ASIGNAR_MIEMBRO_A_COMPA�IA: {
			return new VistaAsignarACompa�ia();
		}
		case Evento.ELIMINAR_MIEMBRO_DE_COMPA�IA: {
			return new VistaEliminarDeCompa�ia();
		}
		case Evento.MODIFICAR_NUMERO_MESES: {
			return new VistaModificarMeses();
		}

		//COMPANIA
		case Evento.ALTA_COMPA�IA: {
			return new VistaAltaCompa�ia();
		}
		case Evento.BUSCAR_COMPA�IA: {
			return new VistaBuscarComp();
		}
		case Evento.ELIMINAR_COMPA�IA: {
			return new VistaEliminarComp();
		}
		case Evento.MODIFICAR_COMPA�IA: {
			return new VistaModificarComp();
		}
		case Evento.MOSTRAR_COMPA�IA: {
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
		case Evento.VISTA_A�ADIR_O_ELIMINAR: {
			return new VistaA�adirOEliminarProducto();
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
