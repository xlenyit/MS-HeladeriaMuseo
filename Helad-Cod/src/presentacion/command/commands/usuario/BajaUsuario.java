package presentacion.command.commands.usuario;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.usuario.SAUsuario;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaUsuario implements CommandInterface {
	SAUsuario sausuario = FactoriaNegocio.getInstance().generarSAUsuario();

	@Override
	public Contexto execute(Object o) {
		Integer i = (Integer) o;
		int op = sausuario.bajaUsuario(i);

		Contexto contexto = new Contexto(op, i);

		return contexto;
	}
}
