package presentacion.command.commands.usuario;

import java.util.List;
import java.util.ArrayList;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.usuario.SAUsuario;
import negocio.usuario.TUsuario;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class ListarUsuario implements CommandInterface{
	SAUsuario sausuario= FactoriaNegocio.getInstance().generarSAUsuario();
	List<TUsuario> lista = new ArrayList<TUsuario>();
	@Override
	public Contexto execute(Object o) {
		
		Integer i = (Integer) o;
		
		if(i!=0) lista=sausuario.listarUsuarioPorGuia((Integer)o);
		else lista=sausuario.listarUsuario();
		
		Contexto contexto = new Contexto (i,lista);
		

		return contexto;
	}
	
}
