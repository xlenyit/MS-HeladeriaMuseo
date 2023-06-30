package presentaci�n.controlador.comando.imp.ComandoActividad;

import java.util.Collection;

import negocio.actividad.SAActividad;
import negocio.actividad.TRepresentacion;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoVerRepresentacionesCompa�ia implements Comando {

	public Contexto ejecutar(Object data) {
		SAActividad saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		Collection<TRepresentacion> res = saActividad.readByCompa�ia((Integer) data);
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_POR_COMPA�IA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_POR_COMPA�IA_KO, null);

		return contexto;
	}

}