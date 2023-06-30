package presentaci�n.controlador.comando.imp.ComandoActividad;

import negocio.actividad.SAActividad;
import negocio.actividad.TActividad;
import negocio.factoria.FactoriaAbstractaNegocio;
import presentaci�n.controlador.Evento;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;

public class ComandoMostrarActividad implements Comando {

	public Contexto ejecutar(Object data) {
		SAActividad saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		TActividad res = saActividad.read((Integer) data);
		Contexto contexto;

		if (res != null)
			contexto = new Contexto(Evento.RES_BUSCAR_ACTIVIDAD_OK, res);
		else
			contexto = new Contexto(Evento.RES_BUSCAR_ACTIVIDAD_KO, null);

		return contexto;
	}

}