package presentacion.command.commands.proveedor;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarProveedor implements CommandInterface {
	SAProveedor saproveedor = FactoriaNegocio.getInstance().generarSAProveedor();
    TProveedor tProveedor;
    int op = -1;
    int id;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		id= (int) o;
		tProveedor = saproveedor.mostrarProveedor(id);
		
		contexto= new Contexto(id,tProveedor);
		return contexto;
	}

}
