package negocio.actividad;

public class TLineaActividad {

	private int actividadId;
	private int usuarioId;
	
	public TLineaActividad() {
	}
	
	
	public TLineaActividad(int actividadId, int usuarioId) {
		this.usuarioId = usuarioId;
		this.actividadId = actividadId;
	}
	
	public int getActividad() {
		return actividadId;
	}
	
	public void setActividad(int actividadId) {
		this.actividadId = actividadId;
	}
	
	public int getUsuario() {
		return usuarioId;
	}
	
	public void setUsuario(int usuarioId) {
		this.usuarioId = usuarioId;
	}
}
