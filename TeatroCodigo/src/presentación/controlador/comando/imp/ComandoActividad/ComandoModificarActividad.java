package presentaci�n.controlador.comando.imp.ComandoActividad;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoModificarActividad implements Comando {

	public Contexto ejecutar(Object data) {
		SAActividad saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		int res = saActividad.update((TActividad) data);
		Contexto contexto;

		if (res > 0)
			contexto = new Contexto(Evento.RES_MODIFICAR_ACTIVIDAD_OK, res);
		else
			contexto = new Contexto(Evento.RES_MODIFICAR_ACTIVIDAD_KO, null);

		return contexto;
	}

}