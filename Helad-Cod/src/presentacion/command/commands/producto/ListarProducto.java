package presentacion.command.commands.producto;

import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarProducto implements CommandInterface {
	private static final int event = Evento.LISTAR_PRODUCTO;
	SAProducto saproducto = FactoriaNegocio.getInstance().generarSAProducto();
    TProducto tProducto;
    int op = -1;
    Contexto contexto;
    int id;
    List<TProducto> aux=saproducto.listarProductos();
	@Override
	public Contexto execute(Object o) {
		if((Integer)o!=0) aux=saproducto.listarProductosPorProveedor((Integer)o);
		contexto = new Contexto (event,aux);
		
		return contexto;
	}

}
