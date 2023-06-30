package presentación.controlador.comando.imp.ComandoActividad;

import java.util.Collection;

import negocio.actividad.SAActividad;
import negocio.actividad.TRepresentacion;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoVerRepresentacionesObra implements Comando {

	public Contexto ejecutar(Object data) {
		SAActividad saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		Collection<TRepresentacion> res = saActividad.readByObra((Integer) data);
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_POR_OBRA_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_POR_OBRA_KO, null);

		return contexto;
	}

}