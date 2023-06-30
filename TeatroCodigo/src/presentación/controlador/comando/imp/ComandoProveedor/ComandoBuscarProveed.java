
package presentación.controlador.comando.imp.ComandoProveedor;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarProveed implements Comando {

	public Contexto ejecutar(Object data) {
		SAProveedor saProveedor = FactoriaAbstractaNegocio.getInstance().createSAProveedor();
		TProveedor res = saProveedor.buscar((int) data);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_PROVEEDOR_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_PROVEEDOR_KO, null);
		return contexto;
	}
}