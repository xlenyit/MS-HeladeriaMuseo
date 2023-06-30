package negocio.actividad;

import java.util.Date;

public class TTaller extends TActividad {

	private String utensilios;
	
	public TTaller(String nombre, Integer codigo, Date fecha, String utensilios){
		super(-1,nombre,codigo,true,fecha);
		this.utensilios = utensilios;
	}
	
	public TTaller(Integer id,  String nombre, Integer codigo, boolean activo, Date fecha, String utensilios) {
		super(id, nombre, codigo, activo, fecha);
		this.utensilios = utensilios;
	}

	public TTaller(Integer id, String nombre, Integer codigo, boolean activo, String utensilios) {
		super(id, nombre, codigo, activo);
		this.utensilios = utensilios;
	}
	public String getUtensilios() {
		return this.utensilios;
	}

	public void setUtensilios(String utensilios) {
		this.utensilios = utensilios;
	}
}
