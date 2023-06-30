package presentaci�n.controlador.factoriacomando;

import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.imp.ComandoTurno.ComandoAltaTurno;
import presentaci�n.controlador.comando.imp.ComandoTurno.ComandoBuscarTurno;
import presentaci�n.controlador.comando.imp.ComandoTurno.ComandoCalcularNominaTurno;
import presentaci�n.controlador.comando.imp.ComandoTurno.ComandoEliminarTurno;
import presentaci�n.controlador.comando.imp.ComandoTurno.ComandoModificarTurno;
import presentaci�n.controlador.comando.imp.ComandoTurno.ComandoMostrarTurnos;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoAltaActividad;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoBajaActividad;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoModificarActividad;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoMostrarActividad;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoMostrarTodasActividades;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoVerRepresentacionesCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoVerRepresentacionesObra;
import presentaci�n.controlador.comando.imp.ComandoActividad.ComandoVerRepresentacionesTemporada;
import presentaci�n.controlador.comando.imp.ComandoCliente.ComandoActualizarCliente;
import presentaci�n.controlador.comando.imp.ComandoCliente.ComandoAltaCliente;
import presentaci�n.controlador.comando.imp.ComandoCliente.ComandoBajaCliente;
import presentaci�n.controlador.comando.imp.ComandoCliente.ComandoBuscarCliente;
import presentaci�n.controlador.comando.imp.ComandoCliente.ComandoMostrarClientes;
import presentaci�n.controlador.comando.imp.ComandoCompa�ia.ComandoAltaCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoCompa�ia.ComandoBajaCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoCompa�ia.ComandoBuscarCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoCompa�ia.ComandoModificarCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoCompa�ia.ComandoMostrarTodasCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoEmpleado.ComandoActualizarEmpleado;
import presentaci�n.controlador.comando.imp.ComandoEmpleado.ComandoAltaEmpleado;
import presentaci�n.controlador.comando.imp.ComandoEmpleado.ComandoBuscarEmpleado;
import presentaci�n.controlador.comando.imp.ComandoEmpleado.ComandoEliminarEmpleado;
import presentaci�n.controlador.comando.imp.ComandoEmpleado.ComandoMostrarEmpleado;
import presentaci�n.controlador.comando.imp.ComandoEmpleado.ComandoMostrarPorTurno;
import presentaci�n.controlador.comando.imp.ComandoFactura.ComandoBajaFactura;
import presentaci�n.controlador.comando.imp.ComandoFactura.ComandoBuscarFactura;
import presentaci�n.controlador.comando.imp.ComandoFactura.ComandoCerrarFactura;
import presentaci�n.controlador.comando.imp.ComandoFactura.ComandoListarFacturas;
import presentaci�n.controlador.comando.imp.ComandoFactura.ComandoModificarFactura;
import presentaci�n.controlador.comando.imp.ComandoFactura.ComandoMostrarFacturasPorCliente;
import presentaci�n.controlador.comando.imp.ComandoFacturaTienda.ComandoBuscarFacturaTienda;
import presentaci�n.controlador.comando.imp.ComandoFacturaTienda.ComandoCerrarVentaTienda;
import presentaci�n.controlador.comando.imp.ComandoFacturaTienda.ComandoDevolverProducto;
import presentaci�n.controlador.comando.imp.ComandoFacturaTienda.ComandoModificarFacturaTienda;
import presentaci�n.controlador.comando.imp.ComandoFacturaTienda.ComandoMostrarFacturaPorEmpleado;
import presentaci�n.controlador.comando.imp.ComandoFacturaTienda.ComandoMostrarFacturaTienda;
import presentaci�n.controlador.comando.imp.ComandoMarca.ComandoAltaMarca;
import presentaci�n.controlador.comando.imp.ComandoMarca.ComandoBuscarMarca;
import presentaci�n.controlador.comando.imp.ComandoMarca.ComandoEliminarMarca;
import presentaci�n.controlador.comando.imp.ComandoMarca.ComandoModificarMarca;
import presentaci�n.controlador.comando.imp.ComandoMarca.ComandoMostrarMarcas;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoActualizarMiembro;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoAltaMiembro;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoAsignarMiembroACompa�ia;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoBajaMiembro;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoBajaMiembroDeCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoBuscarMiembro;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoModificarMeses;
import presentaci�n.controlador.comando.imp.ComandoMiembrosDeCompa�ia.ComandoMostrarMiembrosCompa�ia;
import presentaci�n.controlador.comando.imp.ComandoObra.ComandoActualizarObra;
import presentaci�n.controlador.comando.imp.ComandoObra.ComandoAltaObra;
import presentaci�n.controlador.comando.imp.ComandoObra.ComandoBuscarObra;
import presentaci�n.controlador.comando.imp.ComandoObra.ComandoEliminarObra;
import presentaci�n.controlador.comando.imp.ComandoObra.ComandoVerTodasLasObras;
import presentaci�n.controlador.comando.imp.ComandoProducto.ComandoAltaProducto;
import presentaci�n.controlador.comando.imp.ComandoProducto.ComandoBuscarProducto;
import presentaci�n.controlador.comando.imp.ComandoProducto.ComandoEliminarProducto;
import presentaci�n.controlador.comando.imp.ComandoProducto.ComandoModificarProducto;
import presentaci�n.controlador.comando.imp.ComandoProducto.ComandoMostrarPorProveedor;
import presentaci�n.controlador.comando.imp.ComandoProducto.ComandoMostrarProductos;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoAnadirProveed;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoAsignarProveedorAProducto;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoBuscarProveed;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoDesasignarProveedorAProducto;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoEliminarProveed;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoModificarProveed;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoMostrarProveedPorProducto;
import presentaci�n.controlador.comando.imp.ComandoProveedor.ComandoReadAllProveed;
import presentaci�n.controlador.comando.imp.ComandoTemporada.ComandoAltaTemporada;
import presentaci�n.controlador.comando.imp.ComandoTemporada.ComandoBuscarTemporada;
import presentaci�n.controlador.comando.imp.ComandoTemporada.ComandoEliminarTemporada;
import presentaci�n.controlador.comando.imp.ComandoTemporada.ComandoModificarTemporada;
import presentaci�n.controlador.comando.imp.ComandoTemporada.ComandoMostrarTemporadas;
import presentaci�n.controlador.comando.imp.queries.CommandClienteMasFacturacion;
import presentaci�n.controlador.comando.imp.queries.CommandObraRepresentacionMasVista;

public class FactoriaComando extends FactoriaAbstractaComando {

	@Override
	public Comando createComando(int evento) {

		switch (evento) {
			// MIEMBRO_COMPA�IA
			case Evento.ALTA_MIEMBRO_COMPA�IA: {
				return new ComandoAltaMiembro();
			}
			case Evento.BUSCAR_MIEMBRO_COMPA�IA: {
				return new ComandoBuscarMiembro();
			}
			case Evento.ELIMINAR_MIEMBRO_COMPA�IA: {
				return new ComandoBajaMiembro();
			}
			case Evento.MODIFICAR_MIEMBRO_COMPA�IA: {
				return new ComandoActualizarMiembro();
			}
			case Evento.MOSTRAR_MIEMBRO_COMPA�IA: {
				return new ComandoMostrarMiembrosCompa�ia();
			}
			case Evento.ASIGNAR_MIEMBRO_A_COMPA�IA: {
				return new ComandoAsignarMiembroACompa�ia();
			}
			case Evento.ELIMINAR_MIEMBRO_DE_COMPA�IA: {
				return new ComandoBajaMiembroDeCompa�ia();
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
			case Evento.MOSTRAR_POR_COMPA�IA: {
				return new ComandoVerRepresentacionesCompa�ia();
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
			case Evento.ALTA_COMPA�IA: {
				return new ComandoAltaCompa�ia();
			}
			case Evento.ELIMINAR_COMPA�IA: {
				return new ComandoBajaCompa�ia();
			}
			case Evento.MODIFICAR_COMPA�IA: {
				return new ComandoModificarCompa�ia();
			}
			case Evento.BUSCAR_COMPA�IA: {
				return new ComandoBuscarCompa�ia();
			}
			case Evento.MOSTRAR_COMPA�IA: {
				return new ComandoMostrarTodasCompa�ia();
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