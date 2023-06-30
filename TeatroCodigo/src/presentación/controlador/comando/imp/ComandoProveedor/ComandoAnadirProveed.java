/**
 * 
 */
package presentación.controlador.comando.imp.ComandoProveedor;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoAnadirProveed implements Comando {

	public Contexto ejecutar(Object data) {
		SAProveedor saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		int res = saProveedor.alta((TProveedor) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ALTA_PROVEEDOR_OK, res);
		else
			contexto = new Contexto(Evento.RES_ALTA_PROVEEDOR_KO, null);
		return contexto;
	}
}