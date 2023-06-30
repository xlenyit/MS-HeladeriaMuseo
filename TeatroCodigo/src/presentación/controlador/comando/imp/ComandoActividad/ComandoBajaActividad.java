package presentación.controlador.comando.imp.ComandoActividad;

import negocio.actividad.SAActividad;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentación.controlador.Evento;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;

public class ComandoBajaActividad implements Comando {

	public Contexto ejecutar(Object data) {
		SAActividad saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		int res = saActividad.delete((Integer) data);
		Contexto contexto;

		if (res > 0)
			contexto = new Contexto(Evento.RES_ELIMINAR_ACTIVIDAD_OK, res);
		else
			contexto = new Contexto(Evento.RES_ELIMINAR_ACTIVIDAD_KO, null);

		return contexto;
	}

}