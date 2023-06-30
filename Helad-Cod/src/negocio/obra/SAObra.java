/**
 * 
 */
package negocio.obra;

import java.util.List;

public interface SAObra {
	public Integer AltaObra(TObra tObra);
	public Integer BajaObra(Integer Id);
	public Integer ModificarObra(TObra tObra);
	public TObra MostrarObra(Integer Id);
	public List<TObra> ListarObra();
	public List<TObra> ListarObraPorExposicion(Integer idExpo);
}