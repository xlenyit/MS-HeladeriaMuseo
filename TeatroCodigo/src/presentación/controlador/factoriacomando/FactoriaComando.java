package presentación.controlador.factoriacomando;

import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.imp.ComandoTurno.ComandoAltaTurno;
import presentación.controlador.comando.imp.ComandoTurno.ComandoBuscarTurno;
import presentación.controlador.comando.imp.ComandoTurno.ComandoCalcularNominaTurno;
import presentación.controlador.comando.imp.ComandoTurno.ComandoEliminarTurno;
import presentación.controlador.comando.imp.ComandoTurno.ComandoModificarTurno;
import presentación.controlador.comando.imp.ComandoTurno.ComandoMostrarTurnos;
import presentación.controlador.comando.imp.ComandoActividad.ComandoAltaActividad;
import presentación.controlador.comando.imp.ComandoActividad.ComandoBajaActividad;
import presentación.controlador.comando.imp.ComandoActividad.ComandoModificarActividad;
import presentación.controlador.comando.imp.ComandoActividad.ComandoMostrarActividad;
import presentación.controlador.comando.imp.ComandoActividad.ComandoMostrarTodasActividades;
import presentación.controlador.comando.imp.ComandoActividad.ComandoVerRepresentacionesCompañia;
import presentación.controlador.comando.imp.ComandoActividad.ComandoVerRepresentacionesObra;
import presentación.controlador.comando.imp.ComandoActividad.ComandoVerRepresentacionesTemporada;
import presentación.controlador.comando.imp.ComandoCliente.ComandoActualizarCliente;
import presentación.controlador.comando.imp.ComandoCliente.ComandoAltaCliente;
import presentación.controlador.comando.imp.ComandoCliente.ComandoBajaCliente;
import presentación.controlador.comando.imp.ComandoCliente.ComandoBuscarCliente;
import presentación.controlador.comando.imp.ComandoCliente.ComandoMostrarClientes;
import presentación.controlador.comando.imp.ComandoCompañia.ComandoAltaCompañia;
import presentación.controlador.comando.imp.ComandoCompañia.ComandoBajaCompañia;
import presentación.controlador.comando.imp.ComandoCompañia.ComandoBuscarCompañia;
import presentación.controlador.comando.imp.ComandoCompañia.ComandoModificarCompañia;
import presentación.controlador.comando.imp.ComandoCompañia.ComandoMostrarTodasCompañia;
import presentación.controlador.comando.imp.ComandoEmpleado.ComandoActualizarEmpleado;
import presentación.controlador.comando.imp.ComandoEmpleado.ComandoAltaEmpleado;
import presentación.controlador.comando.imp.ComandoEmpleado.ComandoBuscarEmpleado;
import presentación.controlador.comando.imp.ComandoEmpleado.ComandoEliminarEmpleado;
import presentación.controlador.comando.imp.ComandoEmpleado.ComandoMostrarEmpleado;
import presentación.controlador.comando.imp.ComandoEmpleado.ComandoMostrarPorTurno;
import presentación.controlador.comando.imp.ComandoFactura.ComandoBajaFactura;
import presentación.controlador.comando.imp.ComandoFactura.ComandoBuscarFactura;
import presentación.controlador.comando.imp.ComandoFactura.ComandoCerrarFactura;
import presentación.controlador.comando.imp.ComandoFactura.ComandoListarFacturas;
import presentación.controlador.comando.imp.ComandoFactura.ComandoModificarFactura;
import presentación.controlador.comando.imp.ComandoFactura.ComandoMostrarFacturasPorCliente;
import presentación.controlador.comando.imp.ComandoFacturaTienda.ComandoBuscarFacturaTienda;
import presentación.controlador.comando.imp.ComandoFacturaTienda.ComandoCerrarVentaTienda;
import presentación.controlador.comando.imp.ComandoFacturaTienda.ComandoDevolverProducto;
import presentación.controlador.comando.imp.ComandoFacturaTienda.ComandoModificarFacturaTienda;
import presentación.controlador.comando.imp.ComandoFacturaTienda.ComandoMostrarFacturaPorEmpleado;
import presentación.controlador.comando.imp.ComandoFacturaTienda.ComandoMostrarFacturaTienda;
import presentación.controlador.comando.imp.ComandoMarca.ComandoAltaMarca;
import presentación.controlador.comando.imp.ComandoMarca.ComandoBuscarMarca;
import presentación.controlador.comando.imp.ComandoMarca.ComandoEliminarMarca;
import presentación.controlador.comando.imp.ComandoMarca.ComandoModificarMarca;
import presentación.controlador.comando.imp.ComandoMarca.ComandoMostrarMarcas;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoActualizarMiembro;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoAltaMiembro;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoAsignarMiembroACompañia;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoBajaMiembro;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoBajaMiembroDeCompañia;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoBuscarMiembro;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoModificarMeses;
import presentación.controlador.comando.imp.ComandoMiembrosDeCompañia.ComandoMostrarMiembrosCompañia;
import presentación.controlador.comando.imp.ComandoObra.ComandoActualizarObra;
import presentación.controlador.comando.imp.ComandoObra.ComandoAltaObra;
import presentación.controlador.comando.imp.ComandoObra.ComandoBuscarObra;
import presentación.controlador.comando.imp.ComandoObra.ComandoEliminarObra;
import presentación.controlador.comando.imp.ComandoObra.ComandoVerTodasLasObras;
import presentación.controlador.comando.imp.ComandoProducto.ComandoAltaProducto;
import presentación.controlador.comando.imp.ComandoProducto.ComandoBuscarProducto;
import presentación.controlador.comando.imp.ComandoProducto.ComandoEliminarProducto;
import presentación.controlador.comando.imp.ComandoProducto.ComandoModificarProducto;
import presentación.controlador.comando.imp.ComandoProducto.ComandoMostrarPorProveedor;
import presentación.controlador.comando.imp.ComandoProducto.ComandoMostrarProductos;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoAnadirProveed;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoAsignarProveedorAProducto;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoBuscarProveed;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoDesasignarProveedorAProducto;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoEliminarProveed;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoModificarProveed;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoMostrarProveedPorProducto;
import presentación.controlador.comando.imp.ComandoProveedor.ComandoReadAllProveed;
import presentación.controlador.comando.imp.ComandoTemporada.ComandoAltaTemporada;
import presentación.controlador.comando.imp.ComandoTemporada.ComandoBuscarTemporada;
import presentación.controlador.comando.imp.ComandoTemporada.ComandoEliminarTemporada;
import presentación.controlador.comando.imp.ComandoTemporada.ComandoModificarTemporada;
import presentación.controlador.comando.imp.ComandoTemporada.ComandoMostrarTemporadas;
import presentación.controlador.comando.imp.queries.CommandClienteMasFacturacion;
import presentación.controlador.comando.imp.queries.CommandObraRepresentacionMasVista;

public class FactoriaComando extends FactoriaAbstractaComando {

	@Override
	public Comando createComando(int evento) {

		switch (evento) {
			// MIEMBRO_COMPAÑIA
			case Evento.ALTA_MIEMBRO_COMPAÑIA: {
				return new ComandoAltaMiembro();
			}
			case Evento.BUSCAR_MIEMBRO_COMPAÑIA: {
				return new ComandoBuscarMiembro();
			}
			case Evento.ELIMINAR_MIEMBRO_COMPAÑIA: {
				return new ComandoBajaMiembro();
			}
			case Evento.MODIFICAR_MIEMBRO_COMPAÑIA: {
				return new ComandoActualizarMiembro();
			}
			case Evento.MOSTRAR_MIEMBRO_COMPAÑIA: {
				return new ComandoMostrarMiembrosCompañia();
			}
			case Evento.ASIGNAR_MIEMBRO_A_COMPAÑIA: {
				return new ComandoAsignarMiembroACompañia();
			}
			case Evento.ELIMINAR_MIEMBRO_DE_COMPAÑIA: {
				return new ComandoBajaMiembroDeCompañia();
			}
			case Evento.MODIFICAR_NUMERO_MESES: {
				return new ComandoModificarMeses();
			}
	
			// FACTURA
			case Evento.ALTA_FACTURA: {
				return new ComandoCerrarFactura();
			}
			case Evento.BUSCAR_FACTURA: {
				return new ComandoBuscarFactura();
			}
			case Evento.ELIMINAR_FACTURA: {
				return new ComandoBajaFactura();
			}
			case Evento.MODIFICAR_FACTURA: {
				return new ComandoModificarFactura();
			}
			case Evento.MOSTRAR_FACTURA: {
				return new ComandoListarFacturas();
			}
			case Evento.BUSCAR_FACTURA_CL: {
				return new ComandoMostrarFacturasPorCliente();
			}
	
			// TEMPORADA
			case Evento.ALTA_TEMPORADA: {
				return new ComandoAltaTemporada();
			}
			case Evento.BUSCAR_TEMPORADA: {
				return new ComandoBuscarTemporada();
			}
			case Evento.ELIMINAR_TEMPORADA: {
				return new ComandoEliminarTemporada();
			}
			case Evento.MODIFICAR_TEMPORADA: {
				return new ComandoModificarTemporada();
			}
			case Evento.MOSTRAR_TEMPORADA: {
				return new ComandoMostrarTemporadas();
			}
	
			//OBRA
			case Evento.ALTA_OBRA: {
				return new ComandoAltaObra();
			}
			case Evento.BUSCAR_OBRA: {
				return new ComandoBuscarObra();
			}
			case Evento.ELIMINAR_OBRA: {
				return new ComandoEliminarObra();
			}
			case Evento.MODIFICAR_OBRA: {
				return new ComandoActualizarObra();
			}
			case Evento.MOSTRAR_OBRA: {
				return new ComandoVerTodasLasObras();
			}
			case Evento.OBRA_CON_REPRESENTACION_MAS_VISTA: {
				return new CommandObraRepresentacionMasVista();
			}
	
			// ACTIVIDAD
			case Evento.ALTA_ACTIVIDAD: {
				return new ComandoAltaActividad();
			}
			case Evento.ELIMINAR_ACTIVIDAD: {
				return new ComandoBajaActividad();
			}
			case Evento.MODIFICAR_ACTIVIDAD: {
				return new ComandoModificarActividad();
			}
			case Evento.BUSCAR_ACTIVIDAD: {
				return new ComandoMostrarActividad();
			}
			case Evento.MOSTRAR_ACTIVIDAD: {
				return new ComandoMostrarTodasActividades();
			}
			case Evento.MOSTRAR_POR_COMPAÑIA: {
				return new ComandoVerRepresentacionesCompañia();
			}
			case Evento.MOSTRAR_POR_OBRA: {
				return new ComandoVerRepresentacionesObra();
			}
			case Evento.MOSTRAR_POR_TEMPORADA: {
				return new ComandoVerRepresentacionesTemporada();
			}
	
			//Cliente
			case Evento.ALTA_CLIENTE: {
				return new ComandoAltaCliente();
			}
			case Evento.ELIMINAR_CLIENTE: {
				return new ComandoBajaCliente();
			}
			case Evento.MODIFICAR_CLIENTE: {
				return new ComandoActualizarCliente();
			}
			case Evento.BUSCAR_CLIENTE: {
				return new ComandoBuscarCliente();
			}
			case Evento.MOSTRAR_CLIENTE: {
				return new ComandoMostrarClientes();
			}
			case Evento.CLIENTE_CON_MAS_FACTURACION: {
				return new CommandClienteMasFacturacion();
			}
	
			//Compania
			case Evento.ALTA_COMPAÑIA: {
				return new ComandoAltaCompañia();
			}
			case Evento.ELIMINAR_COMPAÑIA: {
				return new ComandoBajaCompañia();
			}
			case Evento.MODIFICAR_COMPAÑIA: {
				return new ComandoModificarCompañia();
			}
			case Evento.BUSCAR_COMPAÑIA: {
				return new ComandoBuscarCompañia();
			}
			case Evento.MOSTRAR_COMPAÑIA: {
				return new ComandoMostrarTodasCompañia();
			}
	
			//Turno
			case Evento.ALTA_TURNO: {
				return new ComandoAltaTurno();
			}
			case Evento.ELIMINAR_TURNO: {
				return new ComandoEliminarTurno();
			}
			case Evento.MODIFICAR_TURNO: {
				return new ComandoModificarTurno();
			}
			case Evento.BUSCAR_TURNO: {
				return new ComandoBuscarTurno();
			}
			case Evento.MOSTRAR_TURNOS: {
				return new ComandoMostrarTurnos();
			}
			case Evento.NOMINA_TURNO: {
				return new ComandoCalcularNominaTurno();
			}
			
			// MARCA
			case Evento.ALTA_MARCA: {
				return new ComandoAltaMarca();
			}
			case Evento.ELIMINAR_MARCA: {
				return new ComandoEliminarMarca();
			}
			case Evento.MODIFICAR_MARCA: {
				return new ComandoModificarMarca();
			}
			case Evento.BUSCAR_MARCA: {
				return new ComandoBuscarMarca();
			}
			case Evento.MOSTRAR_MARCAS: {
				return new ComandoMostrarMarcas();
			}
			
			// EMPLEADO
			case Evento.ALTA_EMPLEADO: {
				return new ComandoAltaEmpleado();
			}
			case Evento.ELIMINAR_EMPLEADO: {
				return new ComandoEliminarEmpleado();
			}
			case Evento.MODIFICAR_EMPLEADO: {
				return new ComandoActualizarEmpleado();
			}
			case Evento.BUSCAR_EMPLEADO: {
				return new ComandoBuscarEmpleado();
			}
			case Evento.MOSTRAR_EMPLEADOS: {
				return new ComandoMostrarEmpleado();
			}
			case Evento.MOSTRAR_POR_TURNO_EMPLEADO: {
				return new ComandoMostrarPorTurno();
			}
			
			// PRODUCTO
			case Evento.ALTA_PRODUCTO: {
				return new ComandoAltaProducto();
			}
			case Evento.ELIMINAR_PRODUCTO: {
				return new ComandoEliminarProducto();
			}
			case Evento.MODIFICAR_PRODUCTO: {
				return new ComandoModificarProducto();
			}
			case Evento.BUSCAR_PRODUCTO: {
				return new ComandoBuscarProducto();
			}
			case Evento.MOSTRAR_PRODUCTO: {
				return new ComandoMostrarProductos();
			}
			case Evento.MOSTRAR_PRODUCTO_PROVEEDOR: {
				return new ComandoMostrarPorProveedor();
			}
			
			
			// PROVEEDOR
			case Evento.ALTA_PROVEEDOR: {
				return new ComandoAnadirProveed();
			}
			
			case Evento.ELIMINAR_PROVEEDOR: {
				return new ComandoEliminarProveed();
			}
			
			case Evento.MODIFICAR_PROVEEDOR: {
				return new ComandoModificarProveed();
			}
			case Evento.BUSCAR_PROVEEDOR: {
				return new ComandoBuscarProveed();
			}
			case Evento.MOSTRAR_PROVEEDOR: {
				return new ComandoReadAllProveed();
			}
			case Evento.MOSTRAR_PROVEEDOR_PRODUCTO: {
				return new ComandoMostrarProveedPorProducto();
			}
			case Evento.ASIGNAR_PROVEEDOR_A_PRODUCTO: {
				return new ComandoAsignarProveedorAProducto();
			}
			case Evento.DESASIGNAR_PROVEEDOR_A_PRODUCTO: {
				return new ComandoDesasignarProveedorAProducto();
			}
			
			
			// FACTURA
			case Evento.ALTA_FACTURA_TIENDA:{
				return new ComandoCerrarVentaTienda();
			}
			case Evento.DEVOLVER_PRODUCTO_FACTURA_TIENDA:{
				return new ComandoDevolverProducto();
			}
			case Evento.MOSTRAR_FACTURA_TIENDA:{
				return new ComandoMostrarFacturaTienda();
			}
			case Evento.MODIFICAR_FACTURA_TIENDA:{
				return new ComandoModificarFacturaTienda();
			}
			case Evento.BUSCAR_FACTURA_TIENDA:{
				return new ComandoBuscarFacturaTienda();
			}
			case Evento.BUSCAR_FACTURA_EMPLEADO:{
				return new ComandoMostrarFacturaPorEmpleado();
			}

		}
		return null;
	}

}