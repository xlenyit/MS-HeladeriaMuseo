package negocio.actividad;

import java.sql.Date;
import java.sql.Time;

public class TActCultural extends TActividad {

	private String titulo;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public TActCultural(int id, String titulo, int EntradasDisponibles, Date FechaInicio, Date FechaFin, Time hora,
			int duracion, double precio, boolean activo) {
		super(id, "cultural", EntradasDisponibles, precio, FechaInicio, FechaFin, hora, duracion, activo);
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return super.toString() + "Titulo: " + titulo + '\n' + '\n';
	}
}