package presentacion.command.commands.venta;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.SAVenta;
import negocio.venta.TVenta;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class BajaVenta implements CommandInterface {
	SAVenta saventa = FactoriaNegocio.getInstance().generarSAVenta();
    TVenta tVenta;
    int op = -1;
    int id;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		
		id= (int) o;
		op = saventa.devolucionVenta(id);
		
		contexto = new Contexto(op,id);
		return contexto;
	}

}
