package presentacion.command.commands.trabajador;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.TTrabajador;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class ActualizarTrabajador implements CommandInterface {
	
	SATrabajador satrabajador = FactoriaNegocio.getInstance().generarSATrabajadores();
	 TTrabajador tTrabajador;
	 Contexto contexto;
	 int op = -1;
	 int id;

	@Override
	public Contexto execute(Object o) {
		tTrabajador = (TTrabajador) o;
		op=satrabajador.actualizarTrabajador(tTrabajador);
		return new Contexto (op,tTrabajador);
	}

}
