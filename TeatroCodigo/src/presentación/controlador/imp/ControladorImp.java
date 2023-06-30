
package presentaci�n.controlador.imp;

import presentaci�n.controlador.Controlador;
import presentaci�n.controlador.comando.Comando;
import presentaci�n.controlador.comando.Contexto;
import presentaci�n.controlador.factoriacomando.FactoriaAbstractaComando;
import presentaci�n.factoria.FactoriaPresentacion;

public class ControladorImp extends Controlador {

	public void accion(int evento, Object datos) {
		// Obtenemos el comando
		Comando comando = FactoriaAbstractaComando.getInstance().createComando(evento);
		if (comando != null) {
			Contexto contexto = comando.ejecutar(datos);
			FactoriaPresentacion.getInstance().createVista(evento).actualizar(contexto.getEvento(),
					contexto.getDatos());
		} else {
			FactoriaPresentacion.getInstance().createVista(evento);
		}
	}
}