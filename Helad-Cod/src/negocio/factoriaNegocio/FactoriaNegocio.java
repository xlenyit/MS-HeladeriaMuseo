package negocio.factoriaNegocio;

import negocio.actividad.SAActividad;
import negocio.exposicion.SAExposicion;
import negocio.guia.SAGuia;
import negocio.ingrediente.SAIngrediente;
import negocio.obra.SAObra;
import negocio.producto.SAProducto;
import negocio.proveedor.SAProveedor;
import negocio.seccion.SASeccion;
import negocio.trabajador.SATrabajador;
import negocio.usuario.SAUsuario;
import negocio.venta.SAVenta;

public abstract class FactoriaNegocio {
	public abstract SAVenta generarSAVenta();
	public abstract SATrabajador generarSATrabajadores();
	public abstract SAProducto generarSAProducto();
	public abstract SASeccion generarSASeccion();
	public abstract SAProveedor generarSAProveedor();
	public abstract SAIngrediente generarSAIngrediente();
	//-----------------------------------------------------------------------------------------
	public abstract SAObra generarSAObra();
	public abstract SAExposicion generarSAExposicion(); 
	public abstract SAActividad generarSAActividad();
	public abstract SAGuia generarSAGuia();
	public abstract SAUsuario generarSAUsuario();
	private static FactoriaNegocio instancia;
	
	public static synchronized FactoriaNegocio getInstance() {
		
		if (instancia ==  null) instancia = new FactoriaNegocioImp();
		
		return instancia;
		
	}
}