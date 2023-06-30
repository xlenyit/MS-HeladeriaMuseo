
package negocio.guia;

import java.util.ArrayList;
import java.util.List;

public class TGuia {

	private Integer id;
	private String nombre;
	private List<Integer> idExposicion;
	private boolean activo;
	
	
	public TGuia(){
		 id=-1;
		 activo = true;
		 idExposicion = new ArrayList<Integer>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Integer> getIdExposicion() {
		return idExposicion;
	}

	public void setIdExposicion(List<Integer> idExposicion) {
		this.idExposicion = idExposicion;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String idExpoToString(){
		String aux = "[ ";
		for(Integer i: idExposicion){
			aux += i + ", ";
		}
		aux += "]";
		return aux;
	}
	

}