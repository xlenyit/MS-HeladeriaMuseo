package presentacion.command.commands.proveedor;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaProveedor implements CommandInterface {
	SAProveedor saproveedor = FactoriaNegocio.getInstance().generarSAProveedor();
    TProveedor tProveedor;
    int op = -1;
    int id;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		id= (int) o;
		op = saproveedor.bajaProveedor(id);		
		contexto = new Contexto(op, id);
		return contexto;
	}

}
