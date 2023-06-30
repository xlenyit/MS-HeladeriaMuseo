/**
 * 
 */
package negocio.usuario;

import java.util.List;

public interface SAUsuario {

	public Integer altaUsuario(TUsuario tUsuario);
	public Integer bajaUsuario(Integer id);
	public Integer modificarUsuario(TUsuario tUsuario);
	public TUsuario mostrarUsuario(Integer id);
	public List<TUsuario> listarUsuario();
	public List<TUsuario> listarUsuarioPorGuia(Integer idGuia);
}