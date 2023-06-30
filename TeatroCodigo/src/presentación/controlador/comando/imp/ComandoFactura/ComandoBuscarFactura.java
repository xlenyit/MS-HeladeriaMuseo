package presentación.controlador.comando.imp.ComandoFactura;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TFactura;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBuscarFactura implements Comando {

	@Override
	public Contexto ejecutar(Object data) {
		int id = (int) data;
		TFactura res = FactoriaAbstractaNegocio.getInstance().createSAFactura().read(id);
		Contexto contexto;
		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_FACTURA_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_FACTURA_KO, res);
		return contexto;
	}

}
