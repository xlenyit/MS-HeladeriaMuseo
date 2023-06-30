package presentacion.command.commands.seccion;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.seccion.SASeccion;
import negocio.seccion.TSeccion;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarSeccion implements CommandInterface {
	SASeccion saseccion = FactoriaNegocio.getInstance().generarSASeccion();
	TSeccion tSeccion;
	int op = -1;
	int id;
	Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		tSeccion = saseccion.mostrarSeccion(id);
		
		contexto=new Contexto (id,tSeccion);
		
		return contexto;
	}

}