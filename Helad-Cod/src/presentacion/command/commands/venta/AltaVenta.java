package presentacion.command.commands.venta;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.venta.SAVenta;
import negocio.venta.TVenta;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;


public class AltaVenta implements CommandInterface {
	SAVenta saventa = FactoriaNegocio.getInstance().generarSAVenta();
    TVenta tVenta;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		
		tVenta = (TVenta) o;
		op = saventa.altaVenta(tVenta);
		
		contexto = new Contexto(op, tVenta);
		return contexto;
	}

}
