
package negocio.guia;

import java.util.List;

public interface SAGuia {

	public Integer altaGuia(TGuia Guia);

	public Integer bajaGuia(Integer id);

	public Integer modificarGuia(TGuia Guia);

	public List<TGuia> listarGuia();

	public TGuia mostrarGuia(Integer id);

	public Integer anyadirExposicion(TLineaGuia tLineaGuia);

	public Integer eliminarExposicion(TLineaGuia tLineaGuia);

	public List<TGuia> listarPorExposicion(Integer id);
}