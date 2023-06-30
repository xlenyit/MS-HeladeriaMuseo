package presentacion.factoriaGUI;


import presentacion.controlador.Evento;
import presentacion.controlador.IGUI;
import presentacion.exposicion.MarcoExposicion;
import presentacion.exposicion.altaExposicion.MarcoAltaExposicion;
import presentacion.exposicion.bajaExposicion.MarcoBajaExposicion;
import presentacion.exposicion.listarExposicion.MarcoListarExposicion;
import presentacion.exposicion.modificarExposicion.MarcoModificarExposicion;
import presentacion.exposicion.mostrarCosteExposicion.MarcoMostrarCosteExposicion;
import presentacion.exposicion.mostrarExposicion.MarcoMostrarExposicion;
import presentacion.guia.MarcoGuia;
import presentacion.guia.altaGuia.MarcoAltaGuia;
import presentacion.guia.bajaGuia.MarcoBajaGuia;
import presentacion.guia.listarGuia.MarcoListarGuia;
import presentacion.guia.modificarGuia.MarcoModificarGuia;
import presentacion.obra.modificarObra.MarcoModificarObra;
import presentacion.obra.mostrarCosteObra.MarcoMostrarCosteObra;
import presentacion.guia.mostrarGuia.MarcoMostrarGuia;
import presentacion.guia.vincularGuia.MarcoVincularGuia;
import presentacion.ingrediente.MarcoIngrediente;
import presentacion.ingrediente.actualizarIngrediente.MarcoActualizarIngrediente;
import presentacion.ingrediente.altaIngrediente.MarcoAltaIngrediente;
import presentacion.ingrediente.bajaIngrediente.MarcoBajaIngrediente;
import presentacion.ingrediente.listarIngredientes.MarcoListarIngredientes;
import presentacion.ingrediente.mostrarIngrediente.MarcoMostrarIngrediente;
import presentacion.jPrincipal.MenuEleccion;
import presentacion.jPrincipal.VistaGeneral;
import presentacion.jPrincipal.VistaGeneralJPA;
import presentacion.obra.MarcoObra;
import presentacion.obra.altaObra.MarcoAltaObra;
import presentacion.obra.bajaObra.MarcoBajaObra;
import presentacion.obra.listarObra.MarcoListarObra;
import presentacion.obra.mostrarObra.MarcoMostrarObra;
import presentacion.producto.MarcoProducto;
import presentacion.producto.actualizarProducto.MarcoActualizarProducto;
import presentacion.producto.altaProducto.MarcoAltaProducto;
import presentacion.producto.bajaProducto.MarcoBajaProducto;
import presentacion.producto.listarProductos.MarcoListarProductos;
import presentacion.producto.mostrarProducto.MarcoMostrarProducto;
import presentacion.producto.productoMasVendido.MarcoMostrarProductoMasVendido;
import presentacion.proveedor.MarcoProveedor;
import presentacion.proveedor.actualizarProveedor.MarcoActualizarProveedor;
import presentacion.proveedor.altaProveedor.MarcoAltaProveedor;
import presentacion.proveedor.bajaProveedor.MarcoBajaProveedor;
import presentacion.proveedor.listarProveedores.MarcoListarProveedores;
import presentacion.proveedor.mostrarProveedor.MarcoMostrarProveedor;
import presentacion.seccion.MarcoSeccion;
import presentacion.seccion.actualizarSeccion.MarcoActualizarSeccion;
import presentacion.seccion.altaSeccion.MarcoAltaSeccion;
import presentacion.seccion.bajaSeccion.MarcoBajaSeccion;
import presentacion.seccion.listarSecciones.MarcoListarSecciones;
import presentacion.seccion.mostrarSeccion.MarcoMostrarSeccion;
import presentacion.trabajador.MarcoTrabajador;
import presentacion.trabajador.actualizarTrabajador.MarcoActualizarTrabajador;
import presentacion.trabajador.altaTrabajador.MarcoAltaTrabajador;
import presentacion.trabajador.bajaTrabajador.MarcoBajaTrabajador;
import presentacion.trabajador.listarTrabajador.MarcoListarTrabajador;
import presentacion.trabajador.mostrarTrabajador.MarcoMostrarTrabajador;
import presentacion.trabajador.trabajadorConMasVentas.MarcoMostrarTrabajadorConMasVentas;
import presentacion.usuario.MarcoUsuario;
import presentacion.usuario.altaUsuario.MarcoAltaUsuario;
import presentacion.usuario.bajaUsuario.MarcoBajaUsuario;
import presentacion.usuario.listarUsuario.MarcoListarUsuario;
import presentacion.usuario.modificarUsuario.MarcoModificarUsuario;
import presentacion.usuario.mostrarUsuario.MarcoMostrarUsuario;
import presentacion.venta.MarcoVenta;
import presentacion.venta.altaVenta.MarcoAltaVenta;
import presentacion.venta.devolucionVenta.MarcoDevolucionVenta;
import presentacion.venta.mostrarVenta.MarcoMostrarVenta;
import presentacion.actividad.MarcoActividad;
import presentacion.actividad.altaActividad.MarcoAltaActividad;
import presentacion.actividad.bajaActividad.MarcoBajaActividad;
import presentacion.actividad.listarActividad.MarcoListarActividad;
import presentacion.actividad.modificarActividad.MarcoModificarActividad;
import presentacion.actividad.mostrarActividad.MarcoMostrarActividad;
import presentacion.actividad.vincularActividad.MarcoVincularActividad;

public class FactoriaGUIImp extends FactoriaGUI{
	@Override
	public IGUI createVista(int id) {
		switch(id){
		
		case Evento.ELECCION_INICIO_PROGRAMA:
			return new MenuEleccion();
		case Evento.INICIO_PROGRAMA:
			return new VistaGeneral();
		case Evento.INICIO_PROGRAMA_JPA:
			return new VistaGeneralJPA();
		
		case Evento.VENTA:
			return new MarcoVenta();
		case Evento.MOSTRAR_ALTA_VENTA:
			return new MarcoAltaVenta();
		case Evento.MOSTRAR_BAJA_VENTA:
			return new MarcoDevolucionVenta();
		case Evento.MOSTRAR_MOSTRAR_VENTA:
			return new MarcoMostrarVenta();
			
			
		case Evento.PRODUCTO:
			return new MarcoProducto();
		case Evento.MOSTRAR_ACTUALIZAR_PRODUCTO:
			return new MarcoActualizarProducto();
		case Evento.MOSTRAR_ALTA_PRODUCTO:
			return new MarcoAltaProducto();
		case Evento.MOSTRAR_BAJA_PRODUCTO:
			return new MarcoBajaProducto();
		case Evento.MOSTRAR_MOSTRAR_PRODUCTO:
			return new MarcoMostrarProducto();
		case Evento.MOSTRAR_LISTAR_PRODUCTO:
			return new MarcoListarProductos();
		case Evento.MOSTRAR_MOSTRAR_PRODUCTO_MAS_VENDIDO:
			return new MarcoMostrarProductoMasVendido();
			
		case Evento.INGREDIENTE:
			return new MarcoIngrediente();
		case Evento.MOSTRAR_ACTUALIZAR_INGREDIENTE:
			return new MarcoActualizarIngrediente();
		case Evento.MOSTRAR_ALTA_INGREDIENTE:
			return new MarcoAltaIngrediente();
		case Evento.MOSTRAR_BAJA_INGREDIENTE:
			return new MarcoBajaIngrediente();
		case Evento.MOSTRAR_MOSTRAR_INGREDIENTE:
			return new MarcoMostrarIngrediente();
		case Evento.MOSTRAR_LISTAR_INGREDIENTE:
			return new MarcoListarIngredientes();
			
		
			
		case Evento.PROVEEDOR:
			return new MarcoProveedor();
		case Evento.MOSTRAR_ACTUALIZAR_PROVEEDOR:
			return new MarcoActualizarProveedor();
		case Evento.MOSTRAR_ALTA_PROVEEDOR:
			return new MarcoAltaProveedor();
		case Evento.MOSTRAR_BAJA_PROVEEDOR:
			return new MarcoBajaProveedor();
		case Evento.MOSTRAR_MOSTRAR_PROVEEDOR:
			return new MarcoMostrarProveedor();
		case Evento.MOSTRAR_LISTAR_PROVEEDOR:
			return new MarcoListarProveedores();
			
			
		case Evento.TRABAJADOR:
			return new MarcoTrabajador();
		case Evento.MOSTRAR_ACTUALIZAR_TRABAJADOR:
			return new MarcoActualizarTrabajador();
		case Evento.MOSTRAR_ALTA_TRABAJADOR:
			return new MarcoAltaTrabajador();
		case Evento.MOSTRAR_BAJA_TRABAJADOR:
			return new MarcoBajaTrabajador();
		case Evento.MOSTRAR_MOSTRAR_TRABAJADOR:
			return new MarcoMostrarTrabajador();
		case Evento.MOSTRAR_LISTAR_TRABAJADOR:
			return new MarcoListarTrabajador();
		case Evento.MOSTRAR_MOSTRAR_TRABAJADOR_CON_MAS_VENTAS:
			return new MarcoMostrarTrabajadorConMasVentas();
		
			
		case Evento.SECCION:
			return new MarcoSeccion();
		case Evento.MOSTRAR_ACTUALIZAR_SECCION:
			return new MarcoActualizarSeccion();
		case Evento.MOSTRAR_ALTA_SECCION:
			return new MarcoAltaSeccion();
		case Evento.MOSTRAR_BAJA_SECCION:
			return new MarcoBajaSeccion();
		case Evento.MOSTRAR_MOSTRAR_SECCION:
			return new MarcoMostrarSeccion();
		case Evento.MOSTRAR_LISTAR_SECCION:
			return new MarcoListarSecciones();
			
		case Evento.OBRA:
			return new MarcoObra();
		case Evento.MOSTRAR_ACTUALIZAR_OBRA:
			return new MarcoModificarObra();
		case Evento.MOSTRAR_ALTA_OBRA:
			return new MarcoAltaObra();
		case Evento.MOSTRAR_BAJA_OBRA:
			return new MarcoBajaObra();
		case Evento.MOSTRAR_MOSTRAR_OBRA:
			return new MarcoMostrarObra();
		case Evento.MOSTRAR_LISTAR_OBRA:
			return new MarcoListarObra();
		case Evento.MOSTRAR_MOSTRAR_COSTE_OBRA:
			return new MarcoMostrarCosteObra();
		
		case Evento.EXPOSICION:
			return new MarcoExposicion();
		case Evento.MOSTRAR_ACTUALIZAR_EXPOSICION:
			return new MarcoModificarExposicion();
		case Evento.MOSTRAR_ALTA_EXPOSICION:
			return new MarcoAltaExposicion();
		case Evento.MOSTRAR_BAJA_EXPOSICION:
			return new MarcoBajaExposicion();
		case Evento.MOSTRAR_MOSTRAR_EXPOSICION:
			return new MarcoMostrarExposicion();
		case Evento.MOSTRAR_LISTAR_EXPOSICION:
			return new MarcoListarExposicion();
		case Evento.MOSTRAR_MOSTRAR_COSTE_EXPOSICION:
			return new MarcoMostrarCosteExposicion();
			
		case Evento.GUIA:
			return new MarcoGuia();
		case Evento.MOSTRAR_ACTUALIZAR_GUIA:
			return new MarcoModificarGuia();
		case Evento.MOSTRAR_ALTA_GUIA:
			return new MarcoAltaGuia();
		case Evento.MOSTRAR_BAJA_GUIA:
			return new MarcoBajaGuia();
		case Evento.MOSTRAR_MOSTRAR_GUIA:
			return new MarcoMostrarGuia();
		case Evento.MOSTRAR_LISTAR_GUIA:
			return new MarcoListarGuia();	
		case Evento.MOSTRAR_VINCULAR_GUIA:
				return new MarcoVincularGuia();
		
		case Evento.USUARIO:
			return new MarcoUsuario();
		case Evento.MOSTRAR_ACTUALIZAR_USUARIO:
			return new MarcoModificarUsuario();
		case Evento.MOSTRAR_ALTA_USUARIO:
			return new MarcoAltaUsuario();
		case Evento.MOSTRAR_BAJA_USUARIO:
			return new MarcoBajaUsuario();
		case Evento.MOSTRAR_MOSTRAR_USUARIO:
			return new MarcoMostrarUsuario();
		case Evento.MOSTRAR_LISTAR_USUARIO:
			return new MarcoListarUsuario();
		
		case Evento.ACTIVIDAD:
			return new MarcoActividad();
		case Evento.MOSTRAR_ACTUALIZAR_ACTIVIDAD:
			return new MarcoModificarActividad();
		case Evento.MOSTRAR_ALTA_ACTIVIDAD:
			return new MarcoAltaActividad();
		case Evento.MOSTRAR_BAJA_ACTIVIDAD:
			return new MarcoBajaActividad();
		case Evento.MOSTRAR_MOSTRAR_ACTIVIDAD:
			return new MarcoMostrarActividad();
		case Evento.MOSTRAR_LISTAR_ACTIVIDAD:
			return new MarcoListarActividad();
		case Evento.MOSTRAR_VINCULAR_ACTIVIDAD:
			return new MarcoVincularActividad();
		}
		
		return null;
	}
}