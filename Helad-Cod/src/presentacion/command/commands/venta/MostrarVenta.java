package presentacion.command.commands.venta;

import java.util.ArrayList;
import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.trabajador.SATrabajador;
import negocio.venta.SAVenta;
import negocio.venta.TLineaVenta;
import negocio.venta.TVenta;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class MostrarVenta implements CommandInterface {
	private static final int event = Evento.MOSTRAR_VENTA;
	SAVenta saventa = FactoriaNegocio.getInstance().generarSAVenta();
	SATrabajador satrabajador = FactoriaNegocio.getInstance().generarSATrabajadores();
	TVenta tVenta;
	int op = -1;
	int id;
	Contexto contexto;
	List<TVenta> ventas;// que es ventas
	List<TLineaVenta> tLineaVentas;
	ArrayList<Object> objectL;
	ArrayList<List<Integer>> listaAux;

	@Override
	public Contexto execute(Object o) {
		objectL = new ArrayList<Object>();
		id = (int) o;
		tVenta = saventa.mostrarVenta(id);

		if (tVenta != null) {
			objectL.add(tVenta);
		}

		contexto = new Contexto(event, objectL);
		return contexto;
	}

}
