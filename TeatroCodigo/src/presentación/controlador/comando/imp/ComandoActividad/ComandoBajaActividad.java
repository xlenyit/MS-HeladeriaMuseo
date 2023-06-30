package presentaci�n.controlador.comando.imp.ComandoActividad;

import negocio.actividad.SAActividad;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

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