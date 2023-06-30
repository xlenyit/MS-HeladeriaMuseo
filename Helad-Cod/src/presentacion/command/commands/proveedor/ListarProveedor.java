package presentacion.command.commands.proveedor;

import java.util.Set;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarProveedor implements CommandInterface {
	private static final int event = Evento.LISTAR_PROVEEDOR;
	SAProveedor saproveedor = FactoriaNegocio.getInstance().generarSAProveedor();
    TProveedor tProveedor;
    int op = -1;
    int id;
    Contexto contexto;
    Set<TProveedor> aux= saproveedor.listarProveedor();
	@Override
	public Contexto execute(Object o) {
		contexto = new Contexto (event,aux);
		return contexto;
	}

}
