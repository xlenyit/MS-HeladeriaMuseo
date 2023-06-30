
package presentación.controlador.comando.imp.ComandoProveedor;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.proveedor.SAProveedor;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoEliminarProveed implements Comando {

	public Contexto ejecutar(Object data) {
		SAProveedor saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		int res = saProveedor.eliminar((int) data);
		Contexto contexto;
		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_PROVEEDOR_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_PROVEEDOR_KO, null);
		return contexto;
	}
}