package negocio.guia;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class LineaGuiaID implements Serializable {
	
	private static final long serialVersionUID = 0;
	private Integer idExposicion;
	private Integer idGuia;
	private Integer horaIni;
	
	public LineaGuiaID(){
		
	}
	
	
	public LineaGuiaID(Integer idExposicion, Integer idGuia, Integer horaIni){
		this.idExposicion = idExposicion;
		this.idGuia = idGuia;
		this.horaIni=horaIni;
	}
	
	public Integer getIdExposicion() {
		return idExposicion;
	}
	public void setIdExposicion(Integer idExposicion) {
		this.idExposicion = idExposicion;
	}
	public Integer getIdGuia() {
		return idGuia;
	}
	public void setIdGuia(Integer idGuia) {
		this.idGuia = idGuia;
	}
	
	public Integer getHoraIni() {
		return horaIni;
	}


	public void setHoraIni(Integer horaIni) {
		this.horaIni = horaIni;
	}


	public boolean equals(Object obj) {
		return true;
	}
	
	public int hashCode() {
		return 0;
	}
}
