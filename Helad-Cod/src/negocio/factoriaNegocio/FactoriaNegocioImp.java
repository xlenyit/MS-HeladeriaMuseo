package negocio.factoriaNegocio;

import negocio.actividad.SAActividad;
import negocio.actividad.SAActividadImp;
import negocio.exposicion.SAExposicion;
import negocio.exposicion.SAExposicionImp;
import negocio.guia.SAGuia;
import negocio.guia.SAGuiaImp;
import negocio.ingrediente.SAIngrediente;
import negocio.ingrediente.SAIngredienteImp;
import negocio.obra.SAObra;
import negocio.obra.SAObraImp;
import negocio.producto.SAProducto;
import negocio.producto.SAProductoImp;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.SAProveedorImp;
import negocio.seccion.SASeccion;
import negocio.seccion.SASeccionImp;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.SATrabajadorImp;
import negocio.usuario.SAUsuario;
import negocio.usuario.SAUsuarioImp;
import negocio.venta.SAVenta;
import negocio.venta.SAVentaImp;
public class FactoriaNegocioImp extends FactoriaNegocio {
	
	public SAVenta generarSAVenta() {
		SAVentaImp imp = new SAVentaImp();
		return imp;
	}
	public SATrabajador generarSATrabajadores() {
		return new SATrabajadorImp();
	}
	public SAProducto generarSAProducto() {
		return new SAProductoImp();
	}
	public SASeccion generarSASeccion() {
		return new SASeccionImp();
	}
	public SAProveedor generarSAProveedor() {
		return new SAProveedorImp();
	}
	public SAIngrediente generarSAIngrediente() {
		return new SAIngredienteImp();
	}
	//-----------------------------------------------------------------------------------------
	public SAObra generarSAObra() { 
		return new SAObraImp();
	}
	public SAExposicion generarSAExposicion() {
		return new SAExposicionImp();
	}
	public SAGuia generarSAGuia() { 
		return new SAGuiaImp();
	}
	public SAUsuario generarSAUsuario() {
		return new SAUsuarioImp();
	}
	public SAActividad generarSAActividad() {
		return new SAActividadImp();
	}
}