package presentacion.command.commands.usuario;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.usuario.SAUsuario;
import negocio.usuario.TUsuario;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarUsuario implements CommandInterface{
	/*SAUsuario sausuario = FactoriaNegocio.getInstance().generarSAUsuario();
	TUsuario tUsuario;
	int op = -1;
	Contexto contexto;
@Override
public Contexto execute(Object o) {
	int x = (int) o;
	tUsuario = sausuario.mostrarUsuario(x);
	contexto = new Contexto (x, tUsuario);

	return null;
}*/
	SAUsuario sausuario= FactoriaNegocio.getInstance().generarSAUsuario();
	@Override
	public Contexto execute(Object o) {
		Integer i = (Integer) o;
		TUsuario tUsuario= sausuario.mostrarUsuario(i);
		
		Contexto contexto=new Contexto (i,tUsuario);
		

		return contexto;
	}
}
