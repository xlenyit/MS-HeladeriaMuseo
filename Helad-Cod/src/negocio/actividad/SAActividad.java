package negocio.actividad;

import java.util.List;

public interface SAActividad {

	public Integer altaActividad(TActividad tActividad);
	public Integer bajaActividad(Integer id);
	public Integer modificarActividad(TActividad tActividad);
	public TActividad mostrarActividad(Integer id);
	public List<TActividad> listarActividades();
	public Integer anyadirUsuario(TLineaActividad tLineaAct);
	public Integer eliminarUsuario(TLineaActividad tLineaAct);
	public List<TActividad> listarUsuarios(Integer id);
}