package presentacion.command;


import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.commands.exposicion.AltaExposicion;
import presentacion.command.commands.exposicion.BajaExposicion;
import presentacion.command.commands.exposicion.ListarExposicion;
import presentacion.command.commands.exposicion.ModificarExposicion;
import presentacion.command.commands.exposicion.MostrarCosteExposicion;
import presentacion.command.commands.exposicion.MostrarExposicion;
import presentacion.command.commands.guia.AltaGuia;
import presentacion.command.commands.guia.BajaGuia;
import presentacion.command.commands.guia.ListarGuia;
import presentacion.command.commands.guia.ModificarGuia;
import presentacion.command.commands.guia.MostrarGuia;
import presentacion.command.commands.ingrediente.*;
import presentacion.command.commands.lineaGuia.AltaLineaGuia;
import presentacion.command.commands.lineaGuia.BajaLineaGuia;
import presentacion.command.commands.lineaGuia.ListarGuiaPorExposicion;
import presentacion.command.commands.actividad.*;
import presentacion.command.commands.obra.ActualizarObra;
import presentacion.command.commands.obra.AltaObra;
import presentacion.command.commands.obra.BajaObra;
import presentacion.command.commands.obra.ListarObra;
import presentacion.command.commands.obra.ListarObraPorExposicion;
import presentacion.command.commands.obra.MostrarObra;
import presentacion.command.commands.producto.*;
import presentacion.command.commands.proveedor.*;
import presentacion.command.commands.seccion.*;
import presentacion.command.commands.trabajador.*;
import presentacion.command.commands.usuario.AltaUsuario;
import presentacion.command.commands.usuario.BajaUsuario;
import presentacion.command.commands.usuario.ListarUsuario;
import presentacion.command.commands.usuario.ModificarUsuario;
import presentacion.command.commands.usuario.MostrarUsuario;
import presentacion.command.commands.venta.*;
import presentacion.controlador.Evento;

public class CommandFactoryImp extends CommandFactory {

	private int event;
	
	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public CommandInterface getCommand(int e) {
		switch(e){
		case Evento.ALTA_VENTA:
			this.setEvent(Evento.MOSTRAR_ALTA_VENTA);
			return new AltaVenta();
		case Evento.BAJA_VENTA:
			this.setEvent(Evento.MOSTRAR_BAJA_VENTA);
			return new BajaVenta();		
		case Evento.MOSTRAR_VENTA:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_VENTA);
			return new MostrarVenta();		
		case Evento.ACTUALIZAR_PRODUCTO:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_PRODUCTO);
			return new ActualizarProducto();
		case Evento.ALTA_PRODUCTO:
			this.setEvent(Evento.MOSTRAR_ALTA_PRODUCTO);
			return new AltaProducto();
		case Evento.BAJA_PRODUCTO:
			this.setEvent(Evento.MOSTRAR_BAJA_PRODUCTO);
			return new BajaProducto();
		case Evento.MOSTRAR_PRODUCTO:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_PRODUCTO);
			return new MostrarProducto();
		case Evento.LISTAR_PRODUCTO:
			this.setEvent(Evento.MOSTRAR_LISTAR_PRODUCTO);
			return new ListarProducto();
		case Evento.MOSTRAR_PRODUCTO_MAS_VENDIDO:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_PRODUCTO_MAS_VENDIDO);
			return new MostrarProductoMasVendido();	
		case Evento.ACTUALIZAR_PROVEEDOR:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_PROVEEDOR);
			return new ActualizarProveedor();
		case Evento.ALTA_PROVEEDOR:
			this.setEvent(Evento.MOSTRAR_ALTA_PROVEEDOR);
			return new AltaProveedor();
		case Evento.BAJA_PROVEEDOR:
			this.setEvent(Evento.MOSTRAR_BAJA_PROVEEDOR);
			return new BajaProveedor();
		case Evento.MOSTRAR_PROVEEDOR:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_PROVEEDOR);
			return new MostrarProveedor();
		case Evento.LISTAR_PROVEEDOR:
			this.setEvent(Evento.MOSTRAR_LISTAR_PROVEEDOR);
			return new ListarProveedor();
		case Evento.ACTUALIZAR_SECCION:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_SECCION);
			return new ActualizarSeccion();
		case Evento.ALTA_SECCION:			
			this.setEvent(Evento.MOSTRAR_ALTA_SECCION);
			return new AltaSeccion();
		case Evento.BAJA_SECCION:
			this.setEvent(Evento.MOSTRAR_BAJA_SECCION);
			return new BajaSeccion();
		case Evento.MOSTRAR_SECCION:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_SECCION);
			return new MostrarSeccion();
		case Evento.LISTAR_SECCION:
			this.setEvent(Evento.MOSTRAR_LISTAR_SECCION);
			return new ListarSeccion();
		case Evento.ACTUALIZAR_TRABAJADOR:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_TRABAJADOR);
			return new ActualizarTrabajador();
		case Evento.ALTA_TRABAJADOR:
			this.setEvent(Evento.MOSTRAR_ALTA_TRABAJADOR);
			return new AltaTrabajador();	
		case Evento.BAJA_TRABAJADOR:
			this.setEvent(Evento.MOSTRAR_BAJA_TRABAJADOR);
			return new BajaTrabajador();
		case Evento.MOSTRAR_TRABAJADOR:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_TRABAJADOR);
			return new MostrarTrabajador();
		case Evento.LISTAR_TRABAJADOR:
			this.setEvent(Evento.MOSTRAR_LISTAR_TRABAJADOR);
			return new ListarTrabajador();
		case Evento.MOSTRAR_TRABAJADOR_CON_MAS_VENTAS:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_TRABAJADOR_CON_MAS_VENTAS);
			return new MostrarTrabajadorConMasVentas();
		case Evento.ACTUALIZAR_INGREDIENTE:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_INGREDIENTE);
			return new ActualizarIngrediente();
		case Evento.ALTA_INGREDIENTE:
			this.setEvent(Evento.MOSTRAR_ALTA_INGREDIENTE);
			return new AltaIngrediente();	
		case Evento.BAJA_INGREDIENTE:
			this.setEvent(Evento.MOSTRAR_BAJA_INGREDIENTE);
			return new BajaIngrediente();
		case Evento.MOSTRAR_INGREDIENTE:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_INGREDIENTE);
			return new MostrarIngrediente();
		case Evento.LISTAR_INGREDIENTE:
			this.setEvent(Evento.MOSTRAR_LISTAR_INGREDIENTE);
			return new ListarIngrediente();
		
		case Evento.ALTA_OBRA:
			this.setEvent(Evento.MOSTRAR_ALTA_OBRA);
			return new AltaObra();
		case Evento.BAJA_OBRA:
			this.setEvent(Evento.MOSTRAR_BAJA_OBRA);
			return new BajaObra();
		case Evento.ACTUALIZAR_OBRA:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_OBRA);
			return new ActualizarObra();
		case Evento.LISTAR_OBRA:
			this.setEvent(Evento.MOSTRAR_LISTAR_OBRA);
			return new ListarObra();
		case Evento.LISTAR_OBRA_POR_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_LISTAR_OBRA);
			return new ListarObraPorExposicion();
		case Evento.MOSTRAR_OBRA:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_OBRA);
			return new MostrarObra();
		case Evento.MOSTRAR_COSTE_OBRA:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_COSTE_OBRA);
			return new MostrarObra();
		
		case Evento.ACTUALIZAR_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_EXPOSICION);
			return new ModificarExposicion();
		case Evento.ALTA_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_ALTA_EXPOSICION);
			return new AltaExposicion();
		case Evento.BAJA_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_BAJA_EXPOSICION);
			return new BajaExposicion();
		case Evento.MOSTRAR_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_EXPOSICION);
			return new MostrarExposicion();
		case Evento.LISTAR_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_LISTAR_EXPOSICION);
			return new ListarExposicion();
		case Evento.MOSTRAR_COSTE_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_COSTE_EXPOSICION);
			return new MostrarCosteExposicion();
			
		case Evento.ACTUALIZAR_GUIA:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_GUIA);
			return new ModificarGuia();
		case Evento.ALTA_GUIA:
			this.setEvent(Evento.MOSTRAR_ALTA_GUIA);
			return new AltaGuia();
		case Evento.BAJA_GUIA:
			this.setEvent(Evento.MOSTRAR_BAJA_GUIA);
			return new BajaGuia();
		case Evento.MOSTRAR_GUIA:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_GUIA);
			return new MostrarGuia();
		case Evento.LISTAR_GUIA:
			this.setEvent(Evento.MOSTRAR_LISTAR_GUIA);
			return new ListarGuia();
		case Evento.LISTAR_GUIA_POR_EXPOSICION:
			this.setEvent(Evento.MOSTRAR_LISTAR_GUIA);
			return new ListarGuiaPorExposicion();
		case Evento.ALTA_LINEA_GUIA:
			this.setEvent(Evento.MOSTRAR_VINCULAR_GUIA);
			return new AltaLineaGuia();
		case Evento.BAJA_LINEA_GUIA:
			this.setEvent(Evento.MOSTRAR_VINCULAR_GUIA);
			return new BajaLineaGuia();
			
		case Evento.ACTUALIZAR_USUARIO:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_USUARIO);
			return new ModificarUsuario();
		case Evento.ALTA_USUARIO:
			this.setEvent(Evento.MOSTRAR_ALTA_USUARIO);
			return new AltaUsuario();
		case Evento.BAJA_USUARIO:
			this.setEvent(Evento.MOSTRAR_BAJA_USUARIO);
			return new BajaUsuario();
		case Evento.MOSTRAR_USUARIO:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_USUARIO);
			return new MostrarUsuario();
		case Evento.LISTAR_USUARIO:
			this.setEvent(Evento.MOSTRAR_LISTAR_USUARIO);
			return new ListarUsuario();
		
		case Evento.ACTUALIZAR_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_ACTUALIZAR_ACTIVIDAD);
			return new ModificarActividad();
		case Evento.ALTA_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_ALTA_ACTIVIDAD);
			return new AltaActividad();
		case Evento.BAJA_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_BAJA_ACTIVIDAD);
			return new BajaActividad();
		case Evento.MOSTRAR_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_MOSTRAR_ACTIVIDAD);
			return new MostrarActividad();
		case Evento.LISTAR_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_LISTAR_ACTIVIDAD);
			return new ListarActividad();
		case Evento.ALTA_LINEA_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_VINCULAR_ACTIVIDAD);
			return new VincularActividad();
		case Evento.BAJA_LINEA_ACTIVIDAD:
			this.setEvent(Evento.MOSTRAR_VINCULAR_ACTIVIDAD);
			return new DesvincularActividad();
		case Evento.LISTAR_ACTIVIDAD_POR_USUARIO:
			this.setEvent(Evento.MOSTRAR_LISTAR_ACTIVIDAD);
			return new ListarActividadPorUsuario();
		}
		
		return null;
	}

	@Override
	public int mostrarEvento(int e) {
		e=this.getEvent();
		return e;
	}

}