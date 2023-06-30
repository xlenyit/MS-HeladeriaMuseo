package presentaci�n.controlador.comando.imp.ComandoFactura;

import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.TFactura;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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
