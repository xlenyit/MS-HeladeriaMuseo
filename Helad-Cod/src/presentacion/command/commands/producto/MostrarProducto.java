package presentacion.command.commands.producto;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class MostrarProducto implements CommandInterface {
	SAProducto saproducto = FactoriaNegocio.getInstance().generarSAProducto();
    TProducto tProducto;
    int op = -1;
    Contexto contexto;
    int id;
	@Override
	public Contexto execute(Object o) {
		id = (int) o;
		tProducto = saproducto.mostrarProducto(id);
		contexto = new Contexto(id, tProducto);
		
		return contexto;
	}

}
