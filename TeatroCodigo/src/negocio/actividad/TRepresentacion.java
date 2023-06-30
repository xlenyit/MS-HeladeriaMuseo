package negocio.actividad;

import java.sql.Date;
import java.sql.Time;

public class TRepresentacion extends TActividad {

	private int idObra;

	private int idCompa�ia;

	private int idTemporada;

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public int getIdCompa�ia() {
		return idCompa�ia;
	}

	public void setIdCompa�ia(int idCompa�ia) {
		this.idCompa�ia = idCompa�ia;
	}

	public int getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}

	public TRepresentacion(int id, int entradasDisponibles, double precio, Date fechaInicio, Date fechaFin, Time hora,
			int duracion, int idObra, int idCompa�ia, int idTemporada, boolean activo) {
		super(id, "representacion", entradasDisponibles, precio, fechaInicio, fechaFin, hora, duracion, activo);
		this.idObra = idObra;
		this.idCompa�ia = idCompa�ia;
		this.idTemporada = idTemporada;
	}

	@Override
	public String toString() {
		return super.toString() + "ID de compa�ia: " + idCompa�ia + '\n' + "ID de obra: " + idObra + '\n'
				+ "ID de temporada: " + idTemporada + '\n' + '\n';
	}

}