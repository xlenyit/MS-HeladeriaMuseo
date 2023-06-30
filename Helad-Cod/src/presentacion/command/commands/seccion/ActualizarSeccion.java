package presentacion.command.commands.seccion;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.SASeccion;
import negocio.seccion.TSeccion;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class ActualizarSeccion implements CommandInterface {
	SASeccion saseccion = FactoriaNegocio.getInstance().generarSASeccion();
	TSeccion tSeccion;
	int op = -1;
	Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		tSeccion = (TSeccion) o;
		op = saseccion.actualizarSeccion(tSeccion);
		
		contexto=new Contexto (op,tSeccion);
		
		return contexto;
	}

}