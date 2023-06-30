package negocio.actividad;

import java.sql.Date;
import java.sql.Time;

public class TRepresentacion extends TActividad {

	private int idObra;

	private int idCompañia;

	private int idTemporada;

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public int getIdCompañia() {
		return idCompañia;
	}

	public void setIdCompañia(int idCompañia) {
		this.idCompañia = idCompañia;
	}

	public int getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}

	public TRepresentacion(int id, int entradasDisponibles, double precio, Date fechaInicio, Date fechaFin, Time hora,
			int duracion, int idObra, int idCompañia, int idTemporada, boolean activo) {
		super(id, "representacion", entradasDisponibles, precio, fechaInicio, fechaFin, hora, duracion, activo);
		this.idObra = idObra;
		this.idCompañia = idCompañia;
		this.idTemporada = idTemporada;
	}

	@Override
	public String toString() {
		return super.toString() + "ID de compañia: " + idCompañia + '\n' + "ID de obra: " + idObra + '\n'
				+ "ID de temporada: " + idTemporada + '\n' + '\n';
	}

}