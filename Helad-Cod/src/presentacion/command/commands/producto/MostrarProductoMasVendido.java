package presentacion.command.commands.producto;

import java.util.ArrayList;
import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.producto.TProducto;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.factoriaGUI.FactoriaGUI;

public class MostrarProductoMasVendido  implements CommandInterface {
	FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstance();
	 FactoriaGUI factoriaGUI = FactoriaGUI.getInstance();
	 ArrayList<TProducto> listaProductos=new ArrayList<>();
	 int op = -1;
	 int id;

	@Override
	public Contexto execute(Object o) {
		
		List<String> lista = (ArrayList<String>) o;
		
		
		listaProductos = factoriaNegocio.generarSAProducto().productoMasVendido(lista.get(0),lista.get(1));

   	 	Contexto contexto = new Contexto (id,listaProductos);
   	 
   	 	return contexto;
	}
	 
	    
}
