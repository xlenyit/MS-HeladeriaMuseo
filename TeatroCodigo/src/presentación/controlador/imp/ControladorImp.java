
package presentación.controlador.imp;

import presentación.controlador.Controlador;
import presentación.controlador.comando.Comando;
import presentación.controlador.comando.Contexto;
import presentación.controlador.factoriacomando.FactoriaAbstractaComando;
import presentación.factoria.FactoriaPresentacion;

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