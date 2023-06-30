package presentacion.command.commands.producto;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class ActualizarProducto implements CommandInterface {
	SAProducto saproducto = FactoriaNegocio.getInstance().generarSAProducto();
    TProducto tProducto;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		
		tProducto = (TProducto) o;
		op = saproducto.actualizarProducto(tProducto);
		
		contexto = new Contexto(op, tProducto);
		
		return contexto;
	}

}
