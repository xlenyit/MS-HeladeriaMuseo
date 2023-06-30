package presentacion.command.commands.usuario;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.usuario.SAUsuario;
import negocio.usuario.TUsuario;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class ModificarUsuario implements CommandInterface {
	SAUsuario sausuario = FactoriaNegocio.getInstance().generarSAUsuario();

	@Override
	public Contexto execute(Object o) {
		TUsuario tUsuario = (TUsuario) o;
		int op = sausuario.modificarUsuario(tUsuario);

		Contexto contexto = new Contexto(op, tUsuario);

		return contexto;
	}
}
