package presentación.controlador.comando.imp.ComandoActividad;

import java.util.Collection;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoMostrarTodasActividades implements Comando {

	public Contexto ejecutar(Object data) {
		SAActividad saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		Collection<TActividad> res = saActividad.readAll();
		Contexto contexto;

		if (!res.isEmpty())
			contexto = new Contexto(Evento.RES_MOSTRAR_ACTIVIDAD_OK, res);
		else
			contexto = new Contexto(Evento.RES_MOSTRAR_ACTIVIDAD_KO, null);

		return contexto;
	}

}