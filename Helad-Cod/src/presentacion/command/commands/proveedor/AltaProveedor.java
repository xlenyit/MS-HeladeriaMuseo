package presentacion.command.commands.proveedor;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.TProveedor;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class AltaProveedor implements CommandInterface {
	SAProveedor saproveedor = FactoriaNegocio.getInstance().generarSAProveedor();
    TProveedor tProveedor;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		tProveedor= (TProveedor) o;
		op = saproveedor.altaProveedor(tProveedor);
		contexto = new Contexto(op,tProveedor);
		return contexto;
	}

}
